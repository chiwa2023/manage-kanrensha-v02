package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.block;

import java.util.List;

import org.springframework.batch.core.step.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.infrastructure.item.Chunk;
import org.springframework.batch.infrastructure.item.database.JpaItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.persistence.EntityManagerFactory;
import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.CreateUserLeastDtoByBatchParamUtil;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblPostalCommonEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.postal.CheckExistPostalCodeByOtherLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.AddressPostalRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblPostalCommonRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.WriteLogService;

/**
 * 郵便番号不規則データ(その他)有効ItemWriter
 */
@Component
public class SelectPostalCodeOtherItemWriter extends JpaItemWriter<WkTblPostalCommonEntity> {

    /** その他住所存在確認Logic */
    @Autowired
    private CheckExistPostalCodeByOtherLogic checkExistPostalCodeByOtherLogic;

    /** 郵便番号Repository */
    @Autowired
    private AddressPostalRepository addressPostalRepository;

    /** 郵便番号作業Repository */
    @Autowired
    private WkTblPostalCommonRepository wkTblPostalCommonRepository;

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
    public SelectPostalCodeOtherItemWriter(final @Autowired EntityManagerFactory entityManagerFactory) {
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
    public void write(final Chunk<? extends WkTblPostalCommonEntity> items) {

        writeLogService.writeInfo("---other" + items.getItems().get(0).getLgCode());

        for (WkTblPostalCommonEntity entity : items) {
            // 住居テーブルに共通部分データが存在すれば、データとして有効化
            List<AddressPostalEntity> list = checkExistPostalCodeByOtherLogic.practice(entity);
            setTableDataHistoryUtil.practiceInsert(userDto, entity);
            entity.setIsLatest(false);
            if (!list.isEmpty()) {
                // 住居データ参照可能フラグをON
                for (AddressPostalEntity postalEntity : list) {
                    postalEntity.setAddressName(entity.getAddressName());
                    postalEntity.setIsGyoseikuData(true);
                    setTableDataHistoryUtil.practiceInsert(userDto, postalEntity);
                }
                addressPostalRepository.saveAll(list);
                wkTblPostalCommonRepository.save(entity);
            }
        }
    }

}
