package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.lgcode;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.batch.core.step.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.infrastructure.item.Chunk;
import org.springframework.batch.infrastructure.item.database.JpaItemWriter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.CreateUserLeastDtoByBatchParamUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressRsdtBaseEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressRsdtTemplateEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.lgcode.CreateSqlInsertAddressRsdtLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.WriteLogService;

/**
 * アドレス・ベース・レジストリ移動ItemWriter
 */
@Component
public class MoveAddressRsdtItemWriter extends JpaItemWriter<AddressRsdtBaseEntity> {

    /** entityManager */
    private final EntityManager entityManager;

    /** アドレス・ベース・レジストリ挿入Sql作成Logic */
    @Autowired
    private CreateSqlInsertAddressRsdtLogic createSqlInsertAddressRsdtLogic;

    /** バッチ起動条件からユーザ最低限作成Utility */
    @Autowired
    private CreateUserLeastDtoByBatchParamUtil createUserLeastDtoByBatchParamUtil;

    /** ユーザ最低限Dto */
    private LeastUserDto userDto;

    /** ユーザ最低限Dto */
    private String copyLgCode;

    /** ログ書き出しService */
    @Autowired
    private WriteLogService writeLogService;

    /**
     * コンストラクタ
     *
     * @param entityManagerFactory entityManagerFactory
     */
    public MoveAddressRsdtItemWriter(final @Autowired EntityManagerFactory entityManagerFactory) {
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
        copyLgCode = stepExecution.getJobParameters().getString("copyLgCode");
    }

    /**
     * 書き込み処理
     */
    @Override
    @Transactional
    public void write(final Chunk<? extends AddressRsdtBaseEntity> items) {

        writeLogService.writeInfo("chunk:" + items.getItems().get(0).getLgCode() + "==" + LocalDateTime.now());

        entityManager.joinTransaction();

        LocalDateTime now = LocalDateTime.now();
        String timestampString = now.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        for (AddressRsdtBaseEntity entity : items) {

            Query query = entityManager.createNativeQuery(createSqlInsertAddressRsdtLogic.practice(userDto,
                    this.convertTemplateEntity(entity), timestampString));
            query.executeUpdate();
        }

        entityManager.flush();
    }

    private AddressRsdtTemplateEntity convertTemplateEntity(final AddressRsdtBaseEntity entity) {

        AddressRsdtTemplateEntity entityTemplate = new AddressRsdtTemplateEntity();
        BeanUtils.copyProperties(entity, entityTemplate);
        entityTemplate.setLgCode(copyLgCode);

        return entityTemplate;
    }

}
