package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.lgcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressAllCityEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblAddressCityEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.AddressAllCityRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblAddressCityRepository;

/**
 * 市区町村テーブルItemWriter
 */
@Component
public class AddressAllCityItemWriter extends JpaItemWriter<AddressAllCityEntity> {

    /** 市区町村テーブルRepository */
    @Autowired
    private AddressAllCityRepository addressAllCityRepository;

    /** 市区町村ワークテーブルRepository */
    @Autowired
    private WkTblAddressCityRepository wkTblAddressCityRepository;

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
    public AddressAllCityItemWriter(final @Autowired EntityManagerFactory entityManagerFactory) {
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
    public void write(final Chunk<? extends AddressAllCityEntity> items) {

        List<AddressAllCityEntity> listSave = new ArrayList<>();
        List<WkTblAddressCityEntity> listWkTbl = new ArrayList<>();

        for (AddressAllCityEntity entity : items) {

            // 何はともあれワークうテーブルに挿入
            listWkTbl.add(this.createWkTblEntity(entity.getLgCode()));

            // 同コードは存在するか確認(ほとんどの場合存在する)
            List<AddressAllCityEntity> list = addressAllCityRepository.findByLgCodeAndIsLatestTrue(entity.getLgCode());

            final int normalExistSize = 1;

            if (normalExistSize < list.size()) {
                // 該当超自治体コードが複数ある場合はデータ不正。
                // 過去データをすべて廃棄して、今回のデータに入れ替え
                for (AddressAllCityEntity entityOld : list) {
                    setTableDataHistoryUtil.practiceDelete(userDto, entityOld);
                    listSave.add(entityOld);
                }
                // 今回のデータを正として投入
                setTableDataHistoryUtil.practiceInsert(userDto, entity);
                entity.setAddressAllCityId(0); // auto increment 明記
                listSave.add(entity);
                continue;
            }
            if (list.isEmpty()) {
                // 該当自治体コードがない場合はInsert
                setTableDataHistoryUtil.practiceInsert(userDto, entity);
                entity.setAddressAllCityId(0); // auto increment 明記
                listSave.add(entity);
                continue;
            }

            if (normalExistSize == list.size()) {
                // 正常データ1件の場合
                AddressAllCityEntity entityPre = list.get(0);

                if (!this.isSame(entity, entityPre)) {
                    // 変更がある場合は入れ替え
                    setTableDataHistoryUtil.practiceDelete(userDto, entityPre);
                    listSave.add(entityPre);

                    setTableDataHistoryUtil.practiceInsert(userDto, entity);
                    entity.setAddressAllCityId(0); // auto increment明記
                    listSave.add(entity);
                }
                // 変更がなければ何もしない

            }
        }

        addressAllCityRepository.saveAll(listSave);
        wkTblAddressCityRepository.saveAll(listWkTbl);
    }

    private WkTblAddressCityEntity createWkTblEntity(final String lgCode) {

        WkTblAddressCityEntity entity = new WkTblAddressCityEntity();

        entity.setLgCode(lgCode);

        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setWkTblAddressCityId(0); // auto increment明記
        return entity;
    }

    private boolean isSame(final AddressAllCityEntity entity, final AddressAllCityEntity entityPre) {
        if (!entity.getAddressName().equals(entityPre.getAddressName())) {
            return false;
        }
        if (!entity.getAddressNameKana().equals(entityPre.getAddressNameKana())) {
            return false;
        }
        if (!entity.getEffectDate().equals(entityPre.getEffectDate())) {
            return false;
        }
        if (Objects.isNull(entity.getAbolishDate())) {
            return Objects.isNull(entityPre.getAbolishDate());
        } else {
            return entity.getAbolishDate().equals(entityPre.getAbolishDate());
        }

    }

}
