package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.block;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.exception.SQLGrammarException;
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
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalRepairLogEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblPostalCommonEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.postal.SearchNotExistKyuAzaLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.AddressPostalRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblPostalCommonRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.WriteLogService;

/**
 * 旧字削除ItemWriter
 */
@Component
public class DeleteKyuAzaItemWriter extends JpaItemWriter<AddressPostalRepairLogEntity> {

    /** 郵便番号Repository */
    @Autowired
    private AddressPostalRepository addressPostalRepository;

    /** 郵便番号ワークテーブルRepository */
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

    /** 旧字不存在検索Logic */
    @Autowired
    private SearchNotExistKyuAzaLogic searchNotExistKyuAzaLogic;

    /** カッコあけ */
    private static final String EMP_ST = "（";
    /** カッコ閉じ */
    private static final String EMP_END = "）";

    /** ログ記録Service */
    @Autowired
    private WriteLogService writeLogService;

    /**
     * コンストラクタ
     *
     * @param entityManagerFactory EntityManagerFactory
     */
    public DeleteKyuAzaItemWriter(final @Autowired EntityManagerFactory entityManagerFactory) {
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
    }

    /**
     * 書き込み処理
     */
    @Override
    public void write(final Chunk<? extends AddressPostalRepairLogEntity> items) {

        List<AddressPostalEntity> listAddress = new ArrayList<>();
        List<WkTblPostalCommonEntity> listWkTb = new ArrayList<>();

        for (AddressPostalRepairLogEntity entity : items) {
            // 間違いなく旧字が存在しないことを確認

            // 相当する郵便番号が存在しないときは処理しない
            if (addressPostalRepository.findById(entity.getAddressPostalId()).isEmpty()) {
                writeLogService.writeWarn("ログに記載した郵便番号正規Id" + entity.getAddressPostalId() + "が存在しません");
                continue;
            }

            // 字表記が存在しないことを確認したうえで更新
            String address = entity.getAddressName();
            int posStart = address.indexOf(EMP_ST) + 1;
            int posEnd = address.indexOf(EMP_END);

            if (posStart != -1 && posEnd != -1 && posStart < posEnd) {
                try {
                    if (searchNotExistKyuAzaLogic.practice(entity.getLgCode(), address.substring(posStart, posEnd))) {
                        listAddress.add(this.createNormalEntity(entity));
                        listWkTb.add(this.createWkTblEntity(entity));
                    }
                } catch (SQLGrammarException exception) {
                    // 万が一存在しないテーブルが指定された場合はログに記録して作業続行
                    // 修正必要であるというログは残り続けるため、全処理中断の必要がない
                    writeLogService.writeWarn("住所テーブル" + entity.getLgCode() + "が存在しません");
                }
            } else {
                // 後ろカッコが存在しない場合はログに記録にして作業続行
                // 修正必要であるというログは残り続けるため、全処理中断の必要がない
                writeLogService.writeWarn(entity.getAddressName() + "が正常に処理できませんでした");
            }
        }

        addressPostalRepository.saveAll(listAddress).size();
        wkTblPostalCommonRepository.saveAll(listWkTb).size();
    }

    private AddressPostalEntity createNormalEntity(final AddressPostalRepairLogEntity entity) {

        AddressPostalEntity postalEntity = new AddressPostalEntity();
        BeanUtils.copyProperties(entity, postalEntity);

        // 住所名を（の前までに修正する
        String address = entity.getAddressName();
        int pos = address.indexOf(EMP_ST);
        postalEntity.setAddressName(address.substring(0, pos));

        setTableDataHistoryUtil.practiceInsert(userDto, postalEntity);
        // 編集するのでIdを変更しない

        return postalEntity;
    }

    private WkTblPostalCommonEntity createWkTblEntity(final AddressPostalRepairLogEntity entity) {

        WkTblPostalCommonEntity wkTblEntity = new WkTblPostalCommonEntity();
        BeanUtils.copyProperties(entity, wkTblEntity);
        wkTblEntity.setIsConfirm(false); // 立っていたフラグをおろす

        setTableDataHistoryUtil.practiceInsert(userDto, wkTblEntity);
        wkTblEntity.setWkTblPostalCommonId(0); // auto increment明記
        return wkTblEntity;

    }
}
