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

import jakarta.persistence.EntityManagerFactory;
import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.CreateUserLeastDtoByBatchParamUtil;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressRsdtBaseEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblAddressRsdtDeleteEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblAddressRsdtFileEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblAddressRsdtDeleteRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblAddressRsdtFileRepository;

/**
 * アドレス・ベース・レジストリ差異削除ItemnWriter
 */
@Component
public class RsdtWkTblDeleteAddressItemWriter extends JpaItemWriter<AddressRsdtBaseEntity> {

    /** アドレス・ベース・レジストリワークテーブルRepository */
    @Autowired
    private WkTblAddressRsdtFileRepository wkTblAddressRsdtFileRepository;

    /** アドレス・ベース・レジストリ削除ワークテーブルRepository */
    @Autowired
    private WkTblAddressRsdtDeleteRepository wkTblAddressRsdtDeleteRepository;

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
    public RsdtWkTblDeleteAddressItemWriter(final @Autowired EntityManagerFactory entityManagerFactory) {
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
    public void write(final Chunk<? extends AddressRsdtBaseEntity> items) {

        List<WkTblAddressRsdtDeleteEntity> list = new ArrayList<>();
        for (AddressRsdtBaseEntity entity : items) {
            List<WkTblAddressRsdtFileEntity> listFile = wkTblAddressRsdtFileRepository
                    .findFirstByInsertUserCodeAndMachiazaIdAndBlkIdAndPrcIdAndRsdtIdAndRsdt2IdAndAddressBuildingAndIsLatestTrue(
                            userDto.getUserPersonCode(), entity.getMachiazaId(), entity.getBlkId(), entity.getPrcId(),
                            entity.getRsdtId(), entity.getRsdt2Id(), "");
            if (listFile.isEmpty()) {
                list.add(this.createEntity(entity));
            }
        }

        if (!list.isEmpty()) {
            wkTblAddressRsdtDeleteRepository.saveAll(list);
        }
    }

    private WkTblAddressRsdtDeleteEntity createEntity(final AddressRsdtBaseEntity baseEntity) {

        WkTblAddressRsdtDeleteEntity entity = new WkTblAddressRsdtDeleteEntity();
        BeanUtils.copyProperties(baseEntity, entity);

        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setWkTblAddressRsdtDeleteId(0); // auto increment明記

        return entity;
    }

}
