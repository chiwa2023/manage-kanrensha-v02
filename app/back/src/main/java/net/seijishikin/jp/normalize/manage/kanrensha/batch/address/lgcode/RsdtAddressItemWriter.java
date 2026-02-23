package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.lgcode;

import java.util.Objects;

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
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressRsdtBaseEntity;

/**
 * アドレス・ベース・レジストリ住居ItemWriter
 */
@Component
public class RsdtAddressItemWriter extends JpaItemWriter<AddressRsdtBaseEntity> {

    /** entityManager */
    private final EntityManager entityManager;

    /** カンマ */
    private static final String COMMA = ",";

    /** シングルクォーテーション */
    private static final String QUOTE_SINGLE = "'";

    /** テーブル履歴設定Util */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /** バッチ起動条件からユーザ最低限作成Utility */
    @Autowired
    private CreateUserLeastDtoByBatchParamUtil createUserLeastDtoByBatchParamUtil;

    /** ユーザ最低限Dto */
    private LeastUserDto userDto;

    /**
     * コンストラクタ
     *
     * @param entityManagerFactory entityManagerFactory
     */
    public RsdtAddressItemWriter(final @Autowired EntityManagerFactory entityManagerFactory) {
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
    public void write(final Chunk<? extends AddressRsdtBaseEntity> items) {

        final String blank = "";
        // ローカル専用のトランザクションを設定しそこにJoinせよ、とのこと
        // 参考
        // https://stackoverflow.com/questions/25821579/transactionrequiredexception-executing-an-update-delete-query
        entityManager.joinTransaction();
        for (AddressRsdtBaseEntity entity : items) {
            // 2重登録になるケースを避けるために、条件によっては空Entityを渡すことがあるので、その場合は処理を回避する
            if (!blank.equals(entity.getLgCode())) {

                setTableDataHistoryUtil.practiceInsert(userDto, entity);

                Query query = entityManager.createNativeQuery("INSERT INTO address_rsdt_" + entity.getLgCode()
                        + " (address_rsdt_id , lg_code , postalcode1 , postalcode2 , machiaza_id , prc_id , "
                        + " blk_id , rsdt_id , rsdt2_id , address_block , address_building , "
                        + " effect_date , abolish_date , is_latest , insert_user_id , "
                        + " insert_user_code, insert_user_name , insert_timestamp , delete_user_id ,  "
                        + " delete_user_code ,delete_user_name , delete_timestamp) VALUES (0,"
                        + this.createInsertParameter(entity) + ")");
                query.executeUpdate();
            }
        }
        entityManager.flush();
    }

    private String createInsertParameter(final AddressRsdtBaseEntity entity) {
        StringBuilder builder = new StringBuilder();

        builder.append(QUOTE_SINGLE).append(entity.getLgCode()).append(QUOTE_SINGLE).append(COMMA) //
                .append(QUOTE_SINGLE).append(entity.getPostalcode1()).append(QUOTE_SINGLE).append(COMMA)
                .append(QUOTE_SINGLE).append(entity.getPostalcode2()).append(QUOTE_SINGLE).append(COMMA)
                .append(QUOTE_SINGLE).append(entity.getMachiazaId()).append(QUOTE_SINGLE).append(COMMA)
                .append(QUOTE_SINGLE).append(entity.getPrcId()).append(QUOTE_SINGLE).append(COMMA) //
                .append(QUOTE_SINGLE).append(entity.getBlkId()).append(QUOTE_SINGLE).append(COMMA) //
                .append(QUOTE_SINGLE).append(entity.getRsdtId()).append(QUOTE_SINGLE).append(COMMA) //
                .append(QUOTE_SINGLE).append(entity.getRsdt2Id()).append(QUOTE_SINGLE).append(COMMA)
                .append(QUOTE_SINGLE).append(entity.getAddressBlock()).append(QUOTE_SINGLE).append(COMMA)
                .append(QUOTE_SINGLE).append(entity.getAddressBuilding()).append(QUOTE_SINGLE).append(COMMA)
                .append(QUOTE_SINGLE).append(entity.getEffectDate()).append(QUOTE_SINGLE).append(COMMA);
        
        // null insert時にはコンマを外さないと例外を食らってしまう
        if (Objects.isNull(entity.getAbolishDate())) {
            builder.append(entity.getAbolishDate()).append(COMMA);
        } else {
            builder.append(QUOTE_SINGLE).append(entity.getAbolishDate()).append(QUOTE_SINGLE).append(COMMA);
        }

        builder.append(QUOTE_SINGLE).append(this.convertLatest(entity.getIsLatest())).append(QUOTE_SINGLE).append(COMMA)
                .append(QUOTE_SINGLE).append(entity.getInsertUserId()).append(QUOTE_SINGLE).append(COMMA)
                .append(QUOTE_SINGLE).append(entity.getInsertUserCode()).append(QUOTE_SINGLE).append(COMMA)
                .append(QUOTE_SINGLE).append(entity.getInsertUserName()).append(QUOTE_SINGLE).append(COMMA)
                .append(QUOTE_SINGLE).append(entity.getInsertTimestamp()).append(QUOTE_SINGLE).append(COMMA)
                .append(QUOTE_SINGLE).append(entity.getDeleteUserId()).append(QUOTE_SINGLE).append(COMMA)
                .append(QUOTE_SINGLE).append(entity.getDeleteUserCode()).append(QUOTE_SINGLE).append(COMMA)
                .append(QUOTE_SINGLE).append(entity.getDeleteUserName()).append(QUOTE_SINGLE).append(COMMA)
                .append(QUOTE_SINGLE).append(entity.getDeleteTimestamp()).append(QUOTE_SINGLE);

        return builder.toString();
    }

    private Integer convertLatest(final boolean isLatest) {
        // TODO 3回以上出現したらutil化する
        if (isLatest) {
            return 1;
        } else {
            return 0;
        }
    }
}
