package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.lgcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.batch.core.step.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.infrastructure.item.Chunk;
import org.springframework.batch.infrastructure.item.database.JpaItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.persistence.EntityManagerFactory;
import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.CreateUserLeastDtoByBatchParamUtil;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblAddressRsdtChangeEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblAddressRsdtDeleteEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblAddressRsdtMarkEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblAddressRsdtChangeRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblAddressRsdtDeleteRepository;

/**
 * アドレス・ベース・レジストリワークテーブル処理マークItemWriter
 */
@Component
public class WkTblAddressMarkItemWriter extends JpaItemWriter<WkTblAddressRsdtMarkEntity> {

    /** テーブル履歴設定Util */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /** アドレス・ベース・レジストリ新規・変更用ワークテーブル */
    @Autowired
    private WkTblAddressRsdtChangeRepository wkTblAddressRsdtChangeRepository;

    /** アドレス・ベース・レジストリ新規・変更用ワークテーブル */
    @Autowired
    private WkTblAddressRsdtDeleteRepository wkTblAddressRsdtDeleteRepository;

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
    public WkTblAddressMarkItemWriter(final @Autowired EntityManagerFactory entityManagerFactory) {
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
    public void write(final Chunk<? extends WkTblAddressRsdtMarkEntity> items) {

        List<WkTblAddressRsdtDeleteEntity> listDelete = new ArrayList<>();
        List<WkTblAddressRsdtChangeEntity> listChange = new ArrayList<>();
        for (WkTblAddressRsdtMarkEntity entity : items) {

            Integer deleteId = entity.getWlRsdtDeleteId();
            if (0 != deleteId) {
                WkTblAddressRsdtDeleteEntity deleteEntity = this.getDeleteEntity(deleteId);
                if (!Objects.isNull(deleteEntity)) {
                    listDelete.add(deleteEntity);
                }
            }

            Integer changeId = entity.getWlRsdtChangeId();
            if (0 != changeId) {
                WkTblAddressRsdtChangeEntity changeEntity = this.getChangeEntity(changeId);
                if (!Objects.isNull(changeEntity)) {
                    listChange.add(changeEntity);
                }
            }
        }

        if (!listChange.isEmpty()) {
            wkTblAddressRsdtChangeRepository.saveAll(listChange);
        }

        if (!listDelete.isEmpty()) {
            wkTblAddressRsdtDeleteRepository.saveAll(listDelete);
        }

    }

    private WkTblAddressRsdtDeleteEntity getDeleteEntity(final Integer tableId) {
        Optional<WkTblAddressRsdtDeleteEntity> optional = wkTblAddressRsdtDeleteRepository.findById(tableId);

        if (optional.isEmpty()) {
            return null;
        }

        WkTblAddressRsdtDeleteEntity entity = optional.get();
        if (entity.getIsLatest()) {
            setTableDataHistoryUtil.practiceDelete(userDto, entity);
            return entity;
        } else {
            // 何かの理由で履歴になってしまっているデータも登録しない
            return null;
        }
    }

    private WkTblAddressRsdtChangeEntity getChangeEntity(final Integer tableId) {
        Optional<WkTblAddressRsdtChangeEntity> optional = wkTblAddressRsdtChangeRepository.findById(tableId);

        if (optional.isEmpty()) {
            return null;
        }

        WkTblAddressRsdtChangeEntity entity = optional.get();
        if (entity.getIsLatest()) {
            setTableDataHistoryUtil.practiceDelete(userDto, entity);
            return entity;
        } else {
            // 何かの理由で履歴になってしまっているデータも登録しない
            return null;
        }
    }

}
