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
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblAddressRsdtFileEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.lgcode.ConvertAddressBlockPrefLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblAddressRsdtFileRepository;

/**
 * アドレス・ベース・レジストリワークテーブル書き出しItemWriter
 */
@Component
public class RsdtWkTblAddressFileItemWriter extends JpaItemWriter<AddressRsdtBaseEntity> {

    /** アドレス・ベース・レジストリワークテーブルRepository */
    @Autowired
    private WkTblAddressRsdtFileRepository wkTblAddressRsdtFileRepository;

    /** テーブル履歴設定Util */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /** バッチ起動条件からユーザ最低限作成Utility */
    @Autowired
    private CreateUserLeastDtoByBatchParamUtil createUserLeastDtoByBatchParamUtil;

    /** 都道府県前付加Logic */
    @Autowired
    private ConvertAddressBlockPrefLogic convertAddressBlockPrefLogic;

    /** ユーザ最低限Dto */
    private LeastUserDto userDto;

    /** 地方自治体コード */
    private String lgCode;

    /**
     * コンストラクタ
     *
     * @param entityManagerFactory entityManagerFactory
     */
    public RsdtWkTblAddressFileItemWriter(final @Autowired EntityManagerFactory entityManagerFactory) {
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
        lgCode = stepExecution.getJobParameters().getString("lgCode");
    }

    /**
     * 書き込み処理
     */
    @Override
    public void write(final Chunk<? extends AddressRsdtBaseEntity> items) {

        List<WkTblAddressRsdtFileEntity> list = new ArrayList<>();
        for (AddressRsdtBaseEntity item : items) {
            // 地方自治体コードが一致した場合のみ保存
            if (lgCode.equals(item.getLgCode())) {
                list.add(this.createEntity(item));
            }
        }

        if (!list.isEmpty()) {
            wkTblAddressRsdtFileRepository.saveAll(list);
        }
    }

    private WkTblAddressRsdtFileEntity createEntity(final AddressRsdtBaseEntity item) {

        WkTblAddressRsdtFileEntity entity = new WkTblAddressRsdtFileEntity();
        BeanUtils.copyProperties(item, entity);
        entity.setAddressBlock(convertAddressBlockPrefLogic.practice(item));

        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setWkTblAddressRsdtFileId(0); // auto increment明記

        return entity;
    }
}
