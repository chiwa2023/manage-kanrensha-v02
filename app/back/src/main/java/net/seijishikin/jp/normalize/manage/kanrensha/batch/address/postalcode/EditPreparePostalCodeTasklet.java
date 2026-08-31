package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.postalcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.batch.core.step.StepContribution;
import org.springframework.batch.core.step.StepExecution;
import org.springframework.batch.core.listener.StepExecutionListener;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.infrastructure.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.CreateUserLeastDtoByBatchParamUtil;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.address.postalcode.EditPostalConstants.HenkouRiyu;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblPostalEditEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblPostalEditRepository;

/**
 * 郵便番号編集前準備Tasklet
 */
@Component
public class EditPreparePostalCodeTasklet implements Tasklet, StepExecutionListener {

    /** 郵便番号編集ワークテーブルRepository */
    @Autowired
    private WkTblPostalEditRepository wkTblPostalEditRepository;

    /** テーブル履歴設定Util */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /** バッチ起動条件からユーザ最低限作成Utility */
    @Autowired
    private CreateUserLeastDtoByBatchParamUtil createUserLeastDtoByBatchParamUtil;

    /** ユーザ最低限Dto */
    private LeastUserDto userDto;

    /** データ分割用記号 */
    public static final String DATA_SPLITER = "：";

    /**
     * BeforeStep(ユーザ情報設定)
     *
     * @param stepExecution stepExecution
     */
    @BeforeStep
    @Override
    public void beforeStep(final StepExecution stepExecution) {

        userDto = createUserLeastDtoByBatchParamUtil.practice(stepExecution);
    }

    /**
     * 実行メソッド
     */
    @Override
    @Transactional
    public RepeatStatus execute(final StepContribution contribution, final ChunkContext chunkContext) throws Exception {

        // まず単純な修正部分を片付ける
        Integer userCode = userDto.getUserPersonCode();

        // 変更理由1,2,3は住所(アドレス・ベース・レジストリ)との連動変更なので、ここで自動修正はできない
        List<WkTblPostalEditEntity> listJusho = wkTblPostalEditRepository
                .findByInsertUserCodeAndFlgHenkouRiyuInAndIsLatestTrueAndIsRepairIsNull(userCode,
                        this.getHenkouRiyuAddress());
        for (WkTblPostalEditEntity entity : listJusho) {
            // このデータは生きているが未確定であることのみ確定
            entity.setIsRepair(false);
            // 変更理由はcsvの変更理由を使用するので、確定しない
        }
        wkTblPostalEditRepository.saveAll(listJusho);

        final int pairSize = 1;

        // 変更理由4は住所変更がなく郵便番号内だけでの調整
        List<WkTblPostalEditEntity> listChousei = wkTblPostalEditRepository
                .findByInsertUserCodeAndFlgHenkouRiyuInAndIsLatestTrueAndIsRepairIsNull(userCode,
                        this.getHenkouRiyuChousei());
        List<WkTblPostalEditEntity> listChouseiAll = new ArrayList<>();
        for (WkTblPostalEditEntity entity : listChousei) {
            List<WkTblPostalEditEntity> listDelete = wkTblPostalEditRepository
                    .findByInsertUserCodeAndFlgHenkouRiyuAndIsLatestTrueAndPrefNameAndCityNameAndOrgName(userCode,
                            HenkouRiyu.DELETE, entity.getPrefName(), entity.getCityName(), entity.getOrgName());
            // 同地名の削除が1対1で存在する場合は、郵便番号の1対1の移行
            if (pairSize == listDelete.size()) {
                WkTblPostalEditEntity deleteEntity = listDelete.get(0);
                String works = "郵便番号を" + deleteEntity.getPostalcode7() + "から" + entity.getPostalcode7() + "に移行";
                deleteEntity.setWorksText(works);
                entity.setWorksText(works);
                deleteEntity.setIsRepair(true);
                entity.setIsRepair(true);

                // 削除する郵便番号データを捕まえておく(不規則も存在する可能性がある)

                listChouseiAll.add(deleteEntity);
                listChouseiAll.add(entity);
            }
        }

        wkTblPostalEditRepository.saveAll(listChouseiAll);

        // 変更理由5は訂正
        List<WkTblPostalEditEntity> listTeisei = wkTblPostalEditRepository
                .findByInsertUserCodeAndFlgHenkouRiyuInAndIsLatestTrueAndIsRepairIsNull(userCode,
                        this.getHenkouRiyuTeisei());
        List<WkTblPostalEditEntity> listTeiseiAll = new ArrayList<>();
        for (WkTblPostalEditEntity entity : listTeisei) {

            List<WkTblPostalEditEntity> listDelete = wkTblPostalEditRepository
                    .findByInsertUserCodeAndFlgHenkouRiyuAndIsLatestTrueAndPostalcode7(userCode, HenkouRiyu.DELETE,
                            entity.getPostalcode7());

            if (pairSize == listDelete.size()) {
                WkTblPostalEditEntity deleteEntity = listDelete.get(0);

                String works = this.getDifferName(deleteEntity, entity);
                boolean isNotRepair = true;
                // 名称に違いがある場合は修正対象
                if (!Objects.isNull(works)) {

                    deleteEntity.setWorksText(works);
                    entity.setWorksText(works);
                    deleteEntity.setIsRepair(true);
                    entity.setIsRepair(true);

                    listTeiseiAll.add(deleteEntity);
                    listTeiseiAll.add(entity);
                    isNotRepair = false;
                }

                // カナにしか違いがない場合は登録はするけど履歴にする
                works = this.getDifferKana(deleteEntity, entity);
                if (isNotRepair) {

                    deleteEntity.setWorksText(works);
                    entity.setWorksText(works);
                    deleteEntity.setIsRepair(false);
                    entity.setIsRepair(false);

                    setTableDataHistoryUtil.practiceDelete(userDto, deleteEntity);
                    setTableDataHistoryUtil.practiceDelete(userDto, entity);

                    listTeiseiAll.add(deleteEntity);
                    listTeiseiAll.add(entity);
                }
            }
        }
        wkTblPostalEditRepository.saveAll(listTeiseiAll);

        // 1対1のペアができない残り部分は未確定とする(実際にデータを見れば変更内容が想像可能なことがある)
        List<WkTblPostalEditEntity> listRest = wkTblPostalEditRepository
                .findByInsertUserCodeAndFlgHenkouRiyuInAndIsLatestTrueAndIsRepairIsNull(userCode,
                        this.getHenkouRiyuAll());
        List<WkTblPostalEditEntity> listRestAll = new ArrayList<>();
        String works = "処理未確定";
        for (WkTblPostalEditEntity entity : listRest) {

            entity.setWorksText(works);
            entity.setIsRepair(false); // 自動処理はできない
            listTeiseiAll.add(entity);
        }
        wkTblPostalEditRepository.saveAll(listRestAll);

        // 処理終了
        return RepeatStatus.FINISHED;
    }

    private String getDifferName(final WkTblPostalEditEntity entity0, final WkTblPostalEditEntity entity1) {

        if (!entity0.getCityName().equals(entity1.getCityName())) {
            return "市町村に差異があります" + DATA_SPLITER + entity1.getCityName();
        }

        if (!entity0.getOrgName().equals(entity1.getOrgName())) {
            return "町域に差異があります" + DATA_SPLITER + entity1.getOrgName();
        }

        return null;
    }

    private String getDifferKana(final WkTblPostalEditEntity entity0, final WkTblPostalEditEntity entity1) {

        if (!entity0.getCityNameKana().equals(entity1.getCityNameKana())) {
            return "カナだけに差異があります" + DATA_SPLITER + entity1.getCityNameKana();
        }

        if (!entity0.getOrgNameKana().equals(entity1.getOrgNameKana())) {
            return "カナだけに差異があります" + DATA_SPLITER + entity1.getOrgNameKana();
        }

        if (!entity0.getOrgNameKana().equals(entity1.getOrgNameKana())) {
            return "カナだけに差異があります" + DATA_SPLITER + entity1.getPrefNameKana();
        }

        return null;
    }

    private List<String> getHenkouRiyuAddress() {
        List<String> list = new ArrayList<>();
        list.add(HenkouRiyu.SEIREI_SHITEITOSHI);
        list.add(HenkouRiyu.JUUKYO_HYOUJI);
        list.add(HenkouRiyu.KUKAU_SEIRI);

        return list;
    }

    private List<String> getHenkouRiyuAll() {
        List<String> list = new ArrayList<>();
        list.add(HenkouRiyu.SEIREI_SHITEITOSHI);
        list.add(HenkouRiyu.JUUKYO_HYOUJI);
        list.add(HenkouRiyu.KUKAU_SEIRI);
        list.add(HenkouRiyu.YUUBIN_CHOUSEI);
        list.add(HenkouRiyu.TEISEI);
        list.add(HenkouRiyu.DELETE);
        return list;
    }

    private List<String> getHenkouRiyuChousei() {
        List<String> list = new ArrayList<>();
        list.add(HenkouRiyu.YUUBIN_CHOUSEI);

        return list;
    }

    private List<String> getHenkouRiyuTeisei() {
        List<String> list = new ArrayList<>();
        list.add(HenkouRiyu.TEISEI);

        return list;
    }

}
