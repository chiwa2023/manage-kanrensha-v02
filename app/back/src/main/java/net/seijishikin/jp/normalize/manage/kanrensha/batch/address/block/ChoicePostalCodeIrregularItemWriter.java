package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.block;

import java.util.List;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.persistence.EntityManagerFactory;
import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.CreateUserLeastDtoByBatchParamUtil;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblPostalCommonEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.postal.CopyPostalCodeByRangeNameLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.AddressPostalRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblPostalCommonRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.WriteLogService;

/**
 * 郵便番号不規則抽出ItemWriter
 */
@Component
public class ChoicePostalCodeIrregularItemWriter extends JpaItemWriter<WkTblPostalCommonEntity> {

    /** 郵便番号作業Repository */
    @Autowired
    private WkTblPostalCommonRepository wkTblPostalCommonRepository;

    /** 郵便番号Repository */
    @Autowired
    private AddressPostalRepository addressPostalRepository;

    /** 郵便番号範囲複写Logic */
    @Autowired
    private CopyPostalCodeByRangeNameLogic copyPostalCodeByRangeNameLogic;

    /** テーブル履歴設定Util */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /** バッチ起動条件からユーザ最低限作成Utility */
    @Autowired
    private CreateUserLeastDtoByBatchParamUtil createUserLeastDtoByBatchParamUtil;

    /** ユーザ最低限Dto */
    private LeastUserDto userDto;

    /** ログ書き出しService */
    private WriteLogService writeLogService;

    /**
     * コンストラクタ
     *
     * @param entityManagerFactory EntityManagerFactory
     */
    public ChoicePostalCodeIrregularItemWriter(final @Autowired EntityManagerFactory entityManagerFactory) {
        super();
        super.setEntityManagerFactory(entityManagerFactory);
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
    public void write(final Chunk<? extends WkTblPostalCommonEntity> items) {

        writeLogService.writeInfo("---Irregular" + items.getItems().get(0).getLgCode());

        for (WkTblPostalCommonEntity entity : items) {
            setTableDataHistoryUtil.practiceInsert(userDto, entity);
            entity.setIsLatest(false); // ワークテーブルに登録する場合は終了にする
            // 範囲複写用郵便番号作成を試みて空リストが返ってこなければ作業対象
            List<AddressPostalEntity> list = copyPostalCodeByRangeNameLogic.practice(entity, userDto);
            if (!list.isEmpty()) {
                addressPostalRepository.saveAll(list);
                wkTblPostalCommonRepository.save(entity);
            }
        }
    }

}
