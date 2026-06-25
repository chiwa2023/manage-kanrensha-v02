package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.postalcode;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.core.step.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.infrastructure.item.Chunk;
import org.springframework.batch.infrastructure.item.database.JpaItemWriter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.persistence.EntityManagerFactory;
import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.CreateUserLeastDtoByBatchParamUtil;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalIrregularEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.AddressPostalIrregularRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.AddressPostalRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.WriteLogService;

/**
 * 郵便番号ItemWriter
 */
@Component
public class PostalCodeCsvOneLineItemWriter extends JpaItemWriter<AddressPostalEntity> {

    /** 郵便番号Repository */
    @Autowired
    private AddressPostalRepository addressPostalRepository;

    /** 郵便番号不規則Repository */
    @Autowired
    private AddressPostalIrregularRepository addressPostalIrregularRepository;

    /** テーブル履歴設定Util */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /** バッチ起動条件からユーザ最低限作成Utility */
    @Autowired
    private CreateUserLeastDtoByBatchParamUtil createUserLeastDtoByBatchParamUtil;

    /** ユーザ最低限Dto */
    private LeastUserDto userDto;

    /** カッコ文字 */
    private static final String KEY_EMP = "（";

    /** バッチ起動条件からユーザ最低限作成Utility */
    @Autowired
    private WriteLogService writeLogService;

    /** 地方自治体コード */
    private String lgCode;

    /**
     * コンストラクタ
     *
     * @param entityManagerFactory entityManagerFactory
     */
    public PostalCodeCsvOneLineItemWriter(final @Autowired EntityManagerFactory entityManagerFactory) {
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
        lgCode = stepExecution.getJobParameters().getString("lgCode");
    }

    /**
     * 書き込み処理
     */
    @Override
    public void write(final Chunk<? extends AddressPostalEntity> items) {

        writeLogService.writeInfo("chunk:" + items.getItems().get(0).getLgCode() + "==" + LocalDateTime.now());
        
        List<AddressPostalIrregularEntity> listIrregular = new ArrayList<>();
        List<AddressPostalEntity> listPostal = new ArrayList<>();
        
        for (AddressPostalEntity entity : items) {
            // 指定地方自治体コードで始まる場合のみに限定
            if(entity.getLgCode().startsWith(lgCode)) {
                setTableDataHistoryUtil.practiceInsert(userDto, entity);
                entity.setAddressPostalId(0); // auto increment明記
                listPostal.add(entity);
                // （かっこ が原文書に存在する場合は特殊例として並行して不規則に保存
                if (entity.getAddressOrg().contains(KEY_EMP)) {
                    listIrregular.add(this.copyIrregular(entity));
                }
            }
        }

        if(!listIrregular.isEmpty()) {
            addressPostalIrregularRepository.saveAll(listIrregular);
        }
        if(!listPostal.isEmpty()) {
            addressPostalRepository.saveAll(listPostal);
        }
    }

    private AddressPostalIrregularEntity copyIrregular(final AddressPostalEntity entity) {
        AddressPostalIrregularEntity entityIrregular = new AddressPostalIrregularEntity();
        BeanUtils.copyProperties(entity, entityIrregular);
        // 単純な複写でないパターンがあれば追加する
        // nameはかっこより前を登録する
        int pos = entityIrregular.getAddressName().indexOf(KEY_EMP);
        if(-1 != pos) {
            entityIrregular.setAddressName(entityIrregular.getAddressName().substring(0,pos));
        }
        
        setTableDataHistoryUtil.practiceInsert(userDto, entityIrregular);
        entityIrregular.setAddressPostalIrregularId(0); // auto incremment明記

        return entityIrregular;
    }

}
