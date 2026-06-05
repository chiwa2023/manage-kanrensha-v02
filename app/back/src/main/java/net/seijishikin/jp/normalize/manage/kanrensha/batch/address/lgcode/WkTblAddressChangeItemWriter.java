package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.lgcode;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.CreateUserLeastDtoByBatchParamUtil;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressRsdtTemplateEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblAddressRsdtChangeEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblAddressRsdtMarkEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.lgcode.CreateSqlAddressRsdtHistoryLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.lgcode.CreateSqlInsertAddressRsdtLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblAddressRsdtMarkRepository;

/**
 * アドレス・ベース・レジストリワークテーブルから正式テーブルへ複写ItemWriter
 */
@Component
public class WkTblAddressChangeItemWriter extends JpaItemWriter<WkTblAddressRsdtChangeEntity> {

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

    /** アドレス・ベース・レジストリ最新挿入SQL作成Logic */
    @Autowired
    private CreateSqlInsertAddressRsdtLogic createSqlInsertAddressRsdtLogic;

    /** ユーザ最低限Dto */
    private LeastUserDto userDto;

    /**
     * コンストラクタ
     *
     * @param entityManagerFactory entityManagerFactory
     */
    public WkTblAddressChangeItemWriter(final @Autowired EntityManagerFactory entityManagerFactory) {
        super();
        super.setEntityManagerFactory(entityManagerFactory);
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
    public void write(final Chunk<? extends WkTblAddressRsdtChangeEntity> items) {

        // ローカルトランザクション
        entityManager.joinTransaction();

        LocalDateTime now = LocalDateTime.now();
        String timestampString = now.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        for (WkTblAddressRsdtChangeEntity entity : items) {

            if (0 == entity.getAddressRsdtId()) {
                // 新規登録
                this.insertAddressRsdt(entity, timestampString);
            } else {
                // 前データを履歴にしてテーブル登録値を最新にする
                this.changeAddressRsdt(entity, timestampString);
            }

            // 処理マークもする
            wkTblAddressRsdtMarkRepository.save(this.createMarkEntity(entity));
        }

        entityManager.flush();
    }

    private WkTblAddressRsdtMarkEntity createMarkEntity(final WkTblAddressRsdtChangeEntity entity) {

        WkTblAddressRsdtMarkEntity markEntity = new WkTblAddressRsdtMarkEntity();
        markEntity.setWlRsdtChangeId(entity.getWkTblAddressRsdtChangeId());

        setTableDataHistoryUtil.practiceInsert(userDto, markEntity);
        markEntity.setWkTblAddressRsdtMarkId(0); // auto increment明記

        return markEntity;
    }

    private void insertAddressRsdt(final WkTblAddressRsdtChangeEntity entity, final String timestampString) {

        AddressRsdtTemplateEntity baseEntity = new AddressRsdtTemplateEntity();
        BeanUtils.copyProperties(entity, baseEntity);

        // 最新データを挿入
        String sql = createSqlInsertAddressRsdtLogic.practice(userDto, baseEntity, timestampString);
        Query query = entityManager.createNativeQuery(sql);
        query.executeUpdate();
    }

    private void changeAddressRsdt(final WkTblAddressRsdtChangeEntity entity, final String timestampString) {

        AddressRsdtTemplateEntity baseEntity = new AddressRsdtTemplateEntity();
        BeanUtils.copyProperties(entity, baseEntity);

        // 既存データを履歴に変更
        String sqlHistory = createSqlAddressRsdtHistoryLogic.practice(userDto, baseEntity.getLgCode(),
                baseEntity.getAddressRsdtId(), timestampString);
        Query queryHistory = entityManager.createNativeQuery(sqlHistory);
        queryHistory.executeUpdate();

        // 最新データを挿入
        String sqlLatest = createSqlInsertAddressRsdtLogic.practice(userDto, baseEntity, timestampString);
        Query queryLatest = entityManager.createNativeQuery(sqlLatest);
        queryLatest.executeUpdate();
    }

}
