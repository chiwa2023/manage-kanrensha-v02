package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.lgcode;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.CreateUserLeastDtoByBatchParamUtil;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressRsdtBaseEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblAddressRsdtChangeEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblAddressRsdtFileEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblAddressRsdtChangeRepository;

/**
 * 差異あり新規または更新データItemReader
 */
@Component
public class RsdtWkTblChangeAddressItemWriter extends JpaItemWriter<WkTblAddressRsdtFileEntity> {

    /** EntityManager */
    private final EntityManager entityManager;

    /** テーブル履歴設定Util */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /** アドレス・ベース・レジストリ新規・変更用ワークテーブル */
    @Autowired
    private WkTblAddressRsdtChangeRepository wkTblAddressRsdtChangeRepository;

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
    public RsdtWkTblChangeAddressItemWriter(final @Autowired EntityManagerFactory entityManagerFactory) {
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
    @SuppressWarnings("unchecked")
    public void write(final Chunk<? extends WkTblAddressRsdtFileEntity> items) {

        List<WkTblAddressRsdtChangeEntity> listChange = new ArrayList<>();
        for (WkTblAddressRsdtFileEntity entity : items) {
            String sql = "SELECT * FROM address_rsdt_" + entity.getLgCode() + this.createCondition(entity);

            Query query = entityManager.createNativeQuery(sql, AddressRsdtBaseEntity.class);
            List<AddressRsdtBaseEntity> list = (List<AddressRsdtBaseEntity>) query.getResultList();
            if (list.isEmpty()) {
                // コードで一致がない場合は追加
                listChange.add(this.createInsertEntity(entity, 0));
            } else {
                // コードで一致しても住所部分が異なる場合は編集
                AddressRsdtBaseEntity baseEntity = list.get(0);
                if (!baseEntity.getAddressBlock().equals(entity.getAddressBlock())) {
                    listChange.add(this.createInsertEntity(entity, baseEntity.getAddressRsdtId()));
                }
            }
        }

        wkTblAddressRsdtChangeRepository.saveAll(listChange);
    }

    private WkTblAddressRsdtChangeEntity createInsertEntity(final WkTblAddressRsdtFileEntity entity,
            final Integer rsdtId) {

        WkTblAddressRsdtChangeEntity changeEntity = new WkTblAddressRsdtChangeEntity();
        BeanUtils.copyProperties(entity, changeEntity);

        setTableDataHistoryUtil.practiceInsert(userDto, changeEntity);
        changeEntity.setAddressRsdtId(rsdtId);

        changeEntity.setWkTblAddressRsdtChangeId(0); // auto incremnet明記
        return changeEntity;
    }

    private String createCondition(final WkTblAddressRsdtFileEntity entity) {
        StringBuilder builder = new StringBuilder(" WHERE machiaza_id = '");
        builder.append(entity.getMachiazaId()).append("' AND blk_id= '").append(entity.getBlkId())
                .append("' AND prc_id= '").append(entity.getPrcId()).append("' AND rsdt_id = '")
                .append(entity.getRsdtId()).append("' AND rsdt2_id= '").append(entity.getRsdt2Id())
                .append("'  AND address_building = '' AND is_latest = 1");

        return builder.toString();
    }
}
