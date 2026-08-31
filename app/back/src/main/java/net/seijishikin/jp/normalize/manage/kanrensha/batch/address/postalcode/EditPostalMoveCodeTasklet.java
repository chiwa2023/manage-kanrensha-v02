package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.postalcode;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.core.step.StepContribution;
import org.springframework.batch.core.step.StepExecution;
import org.springframework.batch.core.listener.StepExecutionListener;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.infrastructure.repeat.RepeatStatus;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.CreateUserLeastDtoByBatchParamUtil;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.address.postalcode.EditPostalConstants.HenkouRiyu;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalIrregularEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblPostalEditEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.AddressPostalIrregularRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.AddressPostalRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblPostalEditRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.service.postal.MovePostalcodeRsdtService;

/**
 * 郵便番号差分から郵便番号変更Tasklet
 */
@Component
public class EditPostalMoveCodeTasklet implements Tasklet, StepExecutionListener {

    /** 郵便番号編集ワークテーブルRepository */
    @Autowired
    private WkTblPostalEditRepository wkTblPostalEditRepository;

    /** 正規郵便番号Repository */
    @Autowired
    private AddressPostalRepository addressPostalRepository;

    /** 正規郵便番号Repository */
    @Autowired
    private EntityManager entityManager;

    /** 正規郵便番号Repository */
    @Autowired
    private AddressPostalIrregularRepository addressPostalIrregularRepository;

    /** 郵便番号変更Service */
    @Autowired
    private MovePostalcodeRsdtService movePostalcodeRsdtService;

    /** テーブル履歴設定Util */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /** バッチ起動条件からユーザ最低限作成Utility */
    @Autowired
    private CreateUserLeastDtoByBatchParamUtil createUserLeastDtoByBatchParamUtil;

    /** ユーザ最低限Dto */
    private LeastUserDto userDto;

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

        // Transaction参加を明示
        entityManager.joinTransaction();

        Integer userCode = userDto.getUserPersonCode();

        List<AddressPostalEntity> listPostalChange = new ArrayList<>();
        final int posPostal1 = 3;
        final int posPostal2 = 7;
        final int pairSize = 2;
        for (String workText : wkTblPostalEditRepository.findGroupByWorksTextRepair(userCode,
                HenkouRiyu.YUUBIN_CHOUSEI)) {

            List<WkTblPostalEditEntity> listChange = wkTblPostalEditRepository
                    .findByInsertUserCodeAndWorksTextAndIsLatestTrueOrderByFlgHenkouRiyu(userCode, workText);

            // ペアになっていない場合はトラブル発生として中断
            if (pairSize != listChange.size()) {
                continue;
            }

            // 新旧郵便番号を取得
            WkTblPostalEditEntity deleteEditEntity = listChange.get(1);
            String postalOld1 = deleteEditEntity.getPostalcode7().substring(0, posPostal1);
            String postalOld2 = deleteEditEntity.getPostalcode7().substring(posPostal1, posPostal2);

            WkTblPostalEditEntity insertEditEntity = listChange.get(0);
            String postalNew1 = insertEditEntity.getPostalcode7().substring(0, posPostal1);
            String postalNew2 = insertEditEntity.getPostalcode7().substring(posPostal1, posPostal2);

            List<AddressPostalEntity> listPostal = addressPostalRepository
                    .findByPostalcode1AndPostalcode2AndIsLatestTrueOrderByAddressNameAsc(postalOld1, postalOld2);

            // 郵便番号からリストが取得できない場合は中断
            if (listPostal.isEmpty()) {
                continue;
            }

            for (AddressPostalEntity entity : listPostal) {

                // 新データを編集対象に
                listPostalChange.add(this.createNewPostalEntity(entity, postalNew1, postalNew2));

                // 旧データは履歴に
                setTableDataHistoryUtil.practiceDelete(userDto, entity);
                listPostalChange.add(entity);

                // 行政区呼び出しでない場合は不規則を処理する必要がある
                this.changeIrregular(entity, postalNew1, postalNew2);
            }

            // 正規住所を登録
            addressPostalRepository.saveAll(listPostalChange);
            listPostalChange.clear();

            // 住居テーブルも郵便番号移動処理を行う
            movePostalcodeRsdtService.practice(entityManager, userDto, insertEditEntity.getLgCode(), postalOld1,
                    postalOld2, postalNew1, postalNew2);

            // 編集が正常終了したら、元の編集データも履歴に変更
            for (WkTblPostalEditEntity editEntity : listChange) {
                editEntity.setIsRepair(false);
                setTableDataHistoryUtil.practiceDelete(userDto, editEntity);
            }
            wkTblPostalEditRepository.saveAll(listChange);

            // 一郵便番号が終わったらflush
            entityManager.flush();
        }

        // 処理終了
        return RepeatStatus.FINISHED;
    }

    private AddressPostalEntity createNewPostalEntity(final AddressPostalEntity srcEntity, final String postalNew1,
            final String postalNew2) {

        AddressPostalEntity entity = new AddressPostalEntity();
        BeanUtils.copyProperties(srcEntity, entity);
        entity.setPostalcode1(postalNew1);
        entity.setPostalcode2(postalNew2);

        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setAddressPostalId(0); // auto increment明記

        return entity;
    }

    private AddressPostalIrregularEntity createNewIrregularPostalEntity(final AddressPostalIrregularEntity srcEntity,
            final String postalNew1, final String postalNew2) {

        AddressPostalIrregularEntity entity = new AddressPostalIrregularEntity();
        BeanUtils.copyProperties(srcEntity, entity);
        entity.setPostalcode1(postalNew1);
        entity.setPostalcode2(postalNew2);

        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setAddressPostalIrregularId(0); // auto increment明記

        return entity;
    }

    private void changeIrregular(final AddressPostalEntity entity, final String postalNew1, final String postalNew2) {
        List<AddressPostalIrregularEntity> listPostalIrregularChange = new ArrayList<>();
        if (!entity.getIsGyoseikuData()) {
            List<AddressPostalIrregularEntity> listIrr = addressPostalIrregularRepository
                    .findByPostalcode1AndPostalcode2AndIsLatestTrue(entity.getPostalcode1(), entity.getPostalcode2());
            for (AddressPostalIrregularEntity irrEntity : listIrr) {
                listPostalIrregularChange.add(this.createNewIrregularPostalEntity(irrEntity, postalNew1, postalNew2));
                setTableDataHistoryUtil.practiceDelete(userDto, irrEntity);
                listPostalIrregularChange.add(irrEntity);
            }
            // 不規則住所を登録
            addressPostalIrregularRepository.saveAll(listPostalIrregularChange);
        }
    }

}
