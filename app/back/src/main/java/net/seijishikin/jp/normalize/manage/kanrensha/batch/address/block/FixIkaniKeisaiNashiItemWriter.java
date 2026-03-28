package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.block;

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
import net.seijishikin.jp.normalize.manage.kanrensha.dto.postal.PostalCodeBlockResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblPostalCommonEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblPostalCommonRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.service.postal.SearchAddressRsdtIkaniKeisaiNashiService;

/**
 * 以下に掲載がない場合修正ItemWriter
 */
@Component
public class FixIkaniKeisaiNashiItemWriter extends JpaItemWriter<WkTblPostalCommonEntity> {

    /** 郵便番号ワークテーブルRepository */
    @Autowired
    private WkTblPostalCommonRepository wkTblPostalCommonRepository;

    /** テーブル履歴設定Util */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /** バッチ起動条件からユーザ最低限作成Utility */
    @Autowired
    private CreateUserLeastDtoByBatchParamUtil createUserLeastDtoByBatchParamUtil;

    /** 以下に掲載のない場合住所検索Service */
    @Autowired
    private SearchAddressRsdtIkaniKeisaiNashiService searchAddressRsdtIkaniKeisaiNashiService;

    /** ユーザ最低限Dto */
    private LeastUserDto userDto;

    /**
     * コンストラクタ
     *
     * @param entityManagerFactory EntityManagerFactory
     */
    public FixIkaniKeisaiNashiItemWriter(final @Autowired EntityManagerFactory entityManagerFactory) {
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
    public void write(final Chunk<? extends WkTblPostalCommonEntity> items) {

        List<WkTblPostalCommonEntity> list = new ArrayList<>();
        for (WkTblPostalCommonEntity entity : items) {

            PostalCodeBlockResultDto resultDto = searchAddressRsdtIkaniKeisaiNashiService
                    .practice(this.createPostalEntity(entity));

            // 実際に実施通りに呼び出して時に何らかの項目リストが取得できたらとりあえず修正成功とする
            if (!resultDto.getListOptions().isEmpty()) {
                entity.setAddressName(entity.getAddressName().replaceAll("以下に掲載がない場合", ""));
                setTableDataHistoryUtil.practiceInsert(userDto, entity); // 引き続き利用で更新
                entity.setWkTblPostalCommonId(0); // auto increment 明記
                list.add(entity);
            }
        }

        wkTblPostalCommonRepository.saveAll(list);
    }

    private AddressPostalEntity createPostalEntity(final WkTblPostalCommonEntity entity) {

        AddressPostalEntity postalEntity = new AddressPostalEntity();
        BeanUtils.copyProperties(entity, postalEntity);

        return postalEntity;
    }

}
