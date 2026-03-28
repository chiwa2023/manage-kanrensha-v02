package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.block;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.CreateUserLeastDtoByBatchParamUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalEntity;

/**
 * 住所テーブル郵便番号更新ItemReader
 */
@Component
public class UpdateRsdtPostalCodeItemWriter extends JpaItemWriter<AddressPostalEntity> {

    /** entityManager */
    private final EntityManager entityManager;

    /** バッチ起動条件からユーザ最低限作成Utility */
    @Autowired
    private CreateUserLeastDtoByBatchParamUtil createUserLeastDtoByBatchParamUtil;

    /** ユーザ最低限Dto */
    private LeastUserDto userDto;

    /**
     * コンストラクタ
     *
     * @param entityManagerFactory EntityManagerFactory
     */
    public UpdateRsdtPostalCodeItemWriter(final @Autowired EntityManagerFactory entityManagerFactory) {
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
    public void write(final Chunk<? extends AddressPostalEntity> items) {

        LocalDateTime now = LocalDateTime.now();
        String timestampString = now.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        entityManager.joinTransaction();
        for (AddressPostalEntity entity : items) {
            Query query = entityManager.createNativeQuery(this.createSQL(entity, timestampString));
            query.executeUpdate();
        }
        entityManager.flush();
    }

    private String createSQL(final AddressPostalEntity entity, final String timestampString) {
        StringBuilder builder = new StringBuilder();

        builder.append("UPDATE address_rsdt_").append(entity.getLgCode()).append(" SET postalcode1 = '")
                .append(entity.getPostalcode1()).append("' , postalcode2 = '").append(entity.getPostalcode2())
                .append("' , insert_user_id = ").append(userDto.getUserPersonId()).append(" , insert_user_code = ")
                .append(userDto.getUserPersonCode()).append(" , insert_user_name = '")
                .append(userDto.getUserPersonName()).append("' ,insert_timestamp = '").append(timestampString)
                .append("' WHERE address_block LIKE '").append(entity.getAddressName())
                .append("%' AND is_latest = 1 AND postalcode1 = '' AND postalcode2 = ''");

        return builder.toString();
    }

}
