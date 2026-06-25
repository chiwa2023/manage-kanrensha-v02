package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.lgcode;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.batch.core.step.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.infrastructure.item.Chunk;
import org.springframework.batch.infrastructure.item.database.JpaItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.CreateUserLeastDtoByBatchParamUtil;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblAddressRsdtDeleteEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblAddressRsdtMarkEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.lgcode.CreateSqlAddressRsdtHistoryLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblAddressRsdtMarkRepository;

/**
 * アドレス・ベース・レジストリワークテーブル削除実テーブル反映ItemWriter
 */
@Component
public class WkTblAddressDeleteItemWriter extends JpaItemWriter<WkTblAddressRsdtDeleteEntity> {

    /** entityManager */
    private final EntityManager entityManager;

    /** アドレス・ベース・レジストリワークテーブル処理マークRepository */
    @Autowired
    private WkTblAddressRsdtMarkRepository wkTblAddressRsdtMarkRepository;

    /** テーブル履歴設定Util */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /** バッチ起動条件からユーザ最低限作成Utility */
    @Autowired
    private CreateUserLeastDtoByBatchParamUtil createUserLeastDtoByBatchParamUtil;

    /** アドレス・ベース・レジストリ履歴更新SQL作成Logic */
    @Autowired
    private CreateSqlAddressRsdtHistoryLogic createSqlAddressRsdtHistoryLogic;

    /** ユーザ最低限Dto */
    private LeastUserDto userDto;

    /**
     * コンストラクタ
     *
     * @param entityManagerFactory entityManagerFactory
     */
    public WkTblAddressDeleteItemWriter(final @Autowired EntityManagerFactory entityManagerFactory) {
        super(entityManagerFactory);
        entityManager = entityManagerFactory.createEntityManager();
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
    @Transactional
    public void write(final Chunk<? extends WkTblAddressRsdtDeleteEntity> items) {

        // ローカルトランザクション
        entityManager.joinTransaction();

        LocalDateTime now = LocalDateTime.now();
        String timestampString = now.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        for (WkTblAddressRsdtDeleteEntity entity : items) {
            Query query = entityManager.createNativeQuery(createSqlAddressRsdtHistoryLogic.practice(userDto,
                    entity.getLgCode(), entity.getAddressRsdtId(), timestampString));
            query.executeUpdate();
            // 処理マークもする
            wkTblAddressRsdtMarkRepository.save(this.createMarkEntity(entity));
        }

        entityManager.flush();
    }

    private WkTblAddressRsdtMarkEntity createMarkEntity(final WkTblAddressRsdtDeleteEntity entity) {

        WkTblAddressRsdtMarkEntity markEntity = new WkTblAddressRsdtMarkEntity();
        markEntity.setWlRsdtDeleteId(entity.getWkTblAddressRsdtDeleteId());

        setTableDataHistoryUtil.practiceInsert(userDto, markEntity);
        markEntity.setWkTblAddressRsdtMarkId(0); // auto increment明記

        return markEntity;
    }

}
