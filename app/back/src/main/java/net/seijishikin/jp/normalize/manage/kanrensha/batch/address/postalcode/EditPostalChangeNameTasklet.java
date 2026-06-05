package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.postalcode;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.CreateUserLeastDtoByBatchParamUtil;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.address.postalcode.EditPostalConstants.HenkouRiyu;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalIrregularEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblPostalEditEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.postal.GetPreParenthesesLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.AddressPostalIrregularRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.AddressPostalRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblPostalEditRepository;

/**
 * 郵便番号名称変更Tasklet
 * 
 * <p>
 * NOTE 実際の地名が変更になって修正が必要である場合は、先に地名変更がされていなければならないので
 * 基本的に自動実行ができないので、このTaskletは作ったが基本的に使用しない
 * </p>
 */
@Component
public class EditPostalChangeNameTasklet implements Tasklet, StepExecutionListener {

    /** 正規郵便番号Repository */
    @Autowired
    private AddressPostalRepository addressPostalRepository;

    /** 正規郵便番号Repository */
    @Autowired
    private AddressPostalIrregularRepository addressPostalIrregularRepository;

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
    private static final String DATA_SPLITER = EditPreparePostalCodeTasklet.DATA_SPLITER;

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

        Integer userCode = userDto.getUserPersonCode();
        final int pairSize = 2;
        List<AddressPostalEntity> listPostalChange = new ArrayList<>();
        for (String workText : wkTblPostalEditRepository.findGroupByWorksTextRepair(userCode, HenkouRiyu.TEISEI)) {

            List<WkTblPostalEditEntity> listChange = wkTblPostalEditRepository
                    .findByInsertUserCodeAndWorksTextAndIsLatestTrueOrderByFlgHenkouRiyu(userCode, workText);

            // ペアになっていない場合はトラブル発生として中断
            if (pairSize != listChange.size()) {
                continue;
            }

            final int posPostal1 = 3;
            final int posPostal2 = 7;

            WkTblPostalEditEntity deleteEntity = listChange.get(1);
            String postal1 = deleteEntity.getPostalcode7().substring(0, posPostal1);
            String postal2 = deleteEntity.getPostalcode7().substring(posPostal1, posPostal2);

            List<AddressPostalEntity> listPostal = addressPostalRepository
                    .findByPostalcode1AndPostalcode2AndIsLatestTrueOrderByAddressNameAsc(postal1, postal2);

            // 郵便番号からリストが取得できない場合は中断
            if (listPostal.isEmpty()) {
                continue;
            }

            for (AddressPostalEntity entity : listPostal) {

                // 新データを編集対象に
                listPostalChange.add(this.createNewPostalEntity(entity, deleteEntity));

                // 旧データは履歴に
                setTableDataHistoryUtil.practiceDelete(userDto, entity);
                listPostalChange.add(entity);

                // 行政区呼び出しでない場合は不規則を処理する必要がある
                this.changeIrregular(entity, deleteEntity);
            }

            // 正規住所を登録
            addressPostalRepository.saveAll(listPostalChange);
            listPostalChange.clear();

            // 編集が正常終了したら、元の編集データも履歴に変更
            for (WkTblPostalEditEntity editEntity : listChange) {
                editEntity.setIsRepair(false);
                if (editEntity.getWorksText().startsWith("カナ")) {
                    setTableDataHistoryUtil.practiceDelete(userDto, editEntity);
                }

            }
            wkTblPostalEditRepository.saveAll(listChange);
        }

        // 処理終了
        return RepeatStatus.FINISHED;
    }

    private AddressPostalEntity createNewPostalEntity(final AddressPostalEntity srcEntity,
            final WkTblPostalEditEntity deleteEntity) {

        AddressPostalEntity entity = new AddressPostalEntity();
        BeanUtils.copyProperties(srcEntity, entity);

        String worksText = deleteEntity.getWorksText();
        String replaceName = worksText.split(DATA_SPLITER)[1];

        String srcName;
        if (worksText.startsWith("市町村")) {
            srcName = deleteEntity.getCityName();
        } else {
            srcName = deleteEntity.getOrgName();
        }

        entity.setAddressOrg(entity.getAddressOrg().replaceAll(srcName, replaceName));
        entity.setAddressName(entity.getAddressName().replaceAll(GetPreParenthesesLogic.practice(srcName),
                GetPreParenthesesLogic.practice(replaceName)));

        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setAddressPostalId(0); // auto increment明記

        return entity;
    }

    private AddressPostalIrregularEntity createNewIrregularPostalEntity(final AddressPostalIrregularEntity srcEntity,
            final WkTblPostalEditEntity deleteEntity) {

        AddressPostalIrregularEntity entity = new AddressPostalIrregularEntity();
        BeanUtils.copyProperties(srcEntity, entity);

        String worksText = deleteEntity.getWorksText();
        String replaceName = worksText.split(DATA_SPLITER)[1];

        String srcName;
        if (worksText.startsWith("市町村")) {
            srcName = deleteEntity.getCityName();
        } else {
            srcName = deleteEntity.getOrgName();
        }

        entity.setAddressOrg(entity.getAddressOrg().replaceAll(srcName, replaceName));
        entity.setAddressName(entity.getAddressName().replaceAll(GetPreParenthesesLogic.practice(srcName),
                GetPreParenthesesLogic.practice(replaceName)));

        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setAddressPostalIrregularId(0); // auto increment明記

        return entity;
    }

    private void changeIrregular(final AddressPostalEntity entity, final WkTblPostalEditEntity deleteEntity) {
        List<AddressPostalIrregularEntity> listPostalIrregularChange = new ArrayList<>();
        if (!entity.getIsGyoseikuData()) {
            List<AddressPostalIrregularEntity> listIrr = addressPostalIrregularRepository
                    .findByPostalcode1AndPostalcode2AndIsLatestTrue(entity.getPostalcode1(), entity.getPostalcode2());
            for (AddressPostalIrregularEntity irrEntity : listIrr) {
                listPostalIrregularChange.add(this.createNewIrregularPostalEntity(irrEntity, deleteEntity));
                setTableDataHistoryUtil.practiceDelete(userDto, irrEntity);
                listPostalIrregularChange.add(irrEntity);
            }
            // 不規則住所を登録
            addressPostalIrregularRepository.saveAll(listPostalIrregularChange);
        }
    }
}
