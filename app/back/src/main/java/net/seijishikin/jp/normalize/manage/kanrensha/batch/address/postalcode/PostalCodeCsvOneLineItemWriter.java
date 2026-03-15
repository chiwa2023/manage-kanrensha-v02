package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.postalcode;

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
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalIrregularEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.AddressPostalIrregularRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.AddressPostalRepository;

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

    /**
     * コンストラクタ
     *
     * @param entityManagerFactory entityManagerFactory
     */
    public PostalCodeCsvOneLineItemWriter(final @Autowired EntityManagerFactory entityManagerFactory) {
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
    public void write(final Chunk<? extends AddressPostalEntity> items) {

        List<AddressPostalIrregularEntity> listIrregular = new ArrayList<>();
        for (AddressPostalEntity entity : items) {
            setTableDataHistoryUtil.practiceInsert(userDto, entity);
            entity.setAddressPostalId(0); // auto increment明記
            // （かっこ が原文書に存在する場合は特殊例として並行して不規則に保存
            if (entity.getAddressOrg().contains(KEY_EMP)) {
                listIrregular.add(this.copyIrregular(entity));
            }
        }

        addressPostalIrregularRepository.saveAll(listIrregular);
        addressPostalRepository.saveAll(items);
    }

    private AddressPostalIrregularEntity copyIrregular(final AddressPostalEntity entity) {
        AddressPostalIrregularEntity entityIrregular = new AddressPostalIrregularEntity();
        BeanUtils.copyProperties(entity, entityIrregular);
        // 単純な複写でないパターンがあれば追加する
        // nameはかっこより前を登録する
        int pos = entityIrregular.getAddressName().indexOf(KEY_EMP);
        entityIrregular.setAddressName(entityIrregular.getAddressName().substring(0,pos));
        
        setTableDataHistoryUtil.practiceInsert(userDto, entityIrregular);
        entityIrregular.setAddressPostalIrregularId(0); // auto incremment明記

        return entityIrregular;
    }

}
