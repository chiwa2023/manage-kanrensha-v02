package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.block;

import java.util.List;

import org.springframework.batch.core.step.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.infrastructure.item.Chunk;
import org.springframework.batch.infrastructure.item.database.JpaItemWriter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.persistence.EntityManagerFactory;
import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.CreateUserLeastDtoByBatchParamUtil;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalIrregularEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblPostalCommonEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.postal.SplitIrregularToutenLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblPostalCommonRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.WriteLogService;

/**
 * 読点分割ItemWriter
 */
@Component
public class SplitToutenOrgItemWriter extends JpaItemWriter<AddressPostalIrregularEntity> {

    /** 郵便番号作業Repository */
    @Autowired
    private WkTblPostalCommonRepository wkTblPostalCommonRepository;

    /** 郵便番号読点分割Logic */
    @Autowired
    private SplitIrregularToutenLogic splitIrregularToutenLogic;

    /** テーブル履歴設定Util */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /** バッチ起動条件からユーザ最低限作成Utility */
    @Autowired
    private CreateUserLeastDtoByBatchParamUtil createUserLeastDtoByBatchParamUtil;

    /** ユーザ最低限Dto */
    private LeastUserDto userDto;

    /** ログ書き出しService */
    @Autowired
    private WriteLogService writeLogService;

    /**
     * コンストラクタ
     *
     * @param entityManagerFactory EntityManagerFactory
     */
    public SplitToutenOrgItemWriter(final @Autowired EntityManagerFactory entityManagerFactory) {
        super(entityManagerFactory);
    }

    /**
     * BeforeStep(ユーザ情報設定)
     *
     * @param stepExecution stepExecution
     */
    @BeforeStep
    public void beforeStep(final StepExecution stepExecution) {

        userDto = createUserLeastDtoByBatchParamUtil.practice(stepExecution);
    }

    /**
     * 書き込み処理
     */
    @Override
    public void write(final Chunk<? extends AddressPostalIrregularEntity> items) {

        writeLogService.writeInfo("---split touten" + items.getItems().get(0).getLgCode());

        for (AddressPostalIrregularEntity entity : items) {
            setTableDataHistoryUtil.practiceInsert(userDto, entity);
            entity.setIsLatest(false); // ワークテーブルに登録する場合は終了にする

            // 範囲複写用郵便番号作成を試みて空リストが返ってこなければ作業対象
            List<WkTblPostalCommonEntity> list = splitIrregularToutenLogic.practice(entity, userDto);
            if (!list.isEmpty()) {

                wkTblPostalCommonRepository.saveAll(list);
                wkTblPostalCommonRepository.save(this.createWkTblEntity(entity));
            }
        }
    }

    private WkTblPostalCommonEntity createWkTblEntity(final AddressPostalIrregularEntity irregularEntity) {

        WkTblPostalCommonEntity entity = new WkTblPostalCommonEntity();
        BeanUtils.copyProperties(irregularEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setIsLatest(false); // 後から消去用設定だけをする

        return entity;
    }

}
