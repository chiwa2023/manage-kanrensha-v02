package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.block;

import java.util.List;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.persistence.EntityManagerFactory;
import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.CreateUserLeastDtoByBatchParamUtil;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblPostalCommonEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.postal.SelectSingleAddressLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.AddressPostalRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblPostalCommonRepository;

/**
 * （）内単一住所データの呼び出し住所を正規郵便番号に反映するItemWriter
 */
@Component
public class SelectPostalCodeSingleAddressItemWriter extends JpaItemWriter<WkTblPostalCommonEntity> {

    /** 単独住所検索Logic */
    @Autowired
    private SelectSingleAddressLogic selectSingleAddressLogic;

    /** 郵便番号Repository */
    @Autowired
    private AddressPostalRepository addressPostalRepository;

    /** 郵便番号作業Repository */
    @Autowired
    private WkTblPostalCommonRepository wkTblPostalCommonRepository;

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
     * @param entityManagerFactory EntityManagerFactory
     */
    public SelectPostalCodeSingleAddressItemWriter(final @Autowired EntityManagerFactory entityManagerFactory) {
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

        for (WkTblPostalCommonEntity entity : items) {
            // 住居テーブルに存在する地名で有効化
            List<AddressPostalEntity> list = selectSingleAddressLogic.practice(entity, userDto);

            setTableDataHistoryUtil.practiceInsert(userDto, entity);
            entity.setIsLatest(false); // 処理したら履歴になるようにする
            if (!list.isEmpty()) {
                // 住所データは必要部分は編集済みなのでそのまま保存
                addressPostalRepository.saveAll(list);
                wkTblPostalCommonRepository.save(entity);
            }
        }
    }

}
