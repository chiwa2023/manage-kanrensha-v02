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
public class PostalCodeCsvJigyoushaItemWriter extends JpaItemWriter<AddressPostalIrregularEntity> {

    /** 郵便番号不規則Repository */
    @Autowired
    private AddressPostalIrregularRepository addressPostalIrregularRepository;

    /** 郵便番号Repository */
    @Autowired
    private AddressPostalRepository addressPostalRepository;

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
    public PostalCodeCsvJigyoushaItemWriter(final @Autowired EntityManagerFactory entityManagerFactory) {
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
    public void write(final Chunk<? extends AddressPostalIrregularEntity> items) {

        List<AddressPostalEntity> listPostal = new ArrayList<>();
        for (AddressPostalIrregularEntity entity : items) {
            setTableDataHistoryUtil.practiceInsert(userDto, entity);
            entity.setAddressPostalIrregularId(0); // auto increment明記
            listPostal.add(this.createPostal(entity));
        }

        addressPostalIrregularRepository.saveAll(items);
        addressPostalRepository.saveAll(listPostal);
    }

    private AddressPostalEntity createPostal(final AddressPostalIrregularEntity entity) {

        AddressPostalEntity postalEntity = new AddressPostalEntity();
        BeanUtils.copyProperties(entity, postalEntity);
        postalEntity.setIsGyoseikuData(false); // 必ず不規則テーブルを呼ぶ
        setTableDataHistoryUtil.practiceInsert(userDto, postalEntity);
        postalEntity.setAddressPostalId(0); // auto increment明記

        return postalEntity;
    }
}
