package net.seijishikin.jp.normalize.manage.kanrensha.logic.works_apploval;

import java.util.Objects;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaAddressBaseEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaKigyouDtAddressEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaKigyouDtMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.CallKigyouDtMasterEntityLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaKigyouDtAddressRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaKigyouDtMasterRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateAllAddressUtil;

/**
 * 関連者企業団体住所承認作業更新Logic
 */
@Component
public class UpdateApprovalKanrenshaKigyouDtAddressLogic {

    /** 関連者企業団体住所Repository */
    @Autowired
    private KanrenshaKigyouDtAddressRepository kanrenshaKigyouDtAddressRepository;

    /** 関連者企業団体マスタRepository */
    @Autowired
    private KanrenshaKigyouDtMasterRepository kanrenshaKigyouDtMasterRepository;

    /** 関連者企業団体マスタ呼び出しLogic */
    @Autowired
    private CallKigyouDtMasterEntityLogic callKigyouDtMasterEntityLogic;

    /** テーブル履歴設定Util */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /**
     * 処理を行う
     *
     * @param entity  関連者住所承認用Entity
     * @param userDto ユーザ最小限Dto
     * @return 更新行
     */
    public Integer practice(final KanrenshaAddressBaseEntity entity, final LeastUserDto userDto) {

        KanrenshaKigyouDtAddressEntity entityLoad = kanrenshaKigyouDtAddressRepository
                .findById(entity.getKanrenshaAddressId()).get();

        if (Objects.equals(entity.getPostalcode1(), entityLoad.getPostalcode1())
                && Objects.equals(entity.getPostalcode2(), entityLoad.getPostalcode2())
                && Objects.equals(entity.getAddressPostal(), entityLoad.getAddressPostal())
                && Objects.equals(entity.getAddressBlock(), entityLoad.getAddressBlock())
                && Objects.equals(entity.getAddressBuilding(), entityLoad.getAddressBuilding())
                && Objects.equals(entity.getLgCode(), entityLoad.getLgCode())
                && Objects.equals(entity.getMachiazaId(), entityLoad.getMachiazaId())
                && Objects.equals(entity.getBlkId(), entityLoad.getBlkId())
                && Objects.equals(entity.getPrcId(), entityLoad.getPrcId())
                && Objects.equals(entity.getRsdtId(), entityLoad.getRsdtId())
                && Objects.equals(entity.getRsdt2Id(), entityLoad.getRsdt2Id())
                && Objects.equals(entity.getIsPostalEdit(), entityLoad.getIsPostalEdit())
                && Objects.equals(entity.getIsBlockEdit(), entityLoad.getIsBlockEdit())
                && Objects.equals(entity.getIsBuildingEdit(), entityLoad.getIsBuildingEdit())
                && Objects.equals(entity.getIsPostalAccept(), entityLoad.getIsPostalAccept())
                && Objects.equals(entity.getIsBlockAccept(), entityLoad.getIsBlockAccept())
                && Objects.equals(entity.getIsBuildingAccept(), entityLoad.getIsBuildingAccept())) {
            // 呼び出したentityに対して住所が変更がない場合はこのロジックでの処理を中断
            return 0;
        } else {
            setTableDataHistoryUtil.practiceDelete(userDto, entityLoad);
            kanrenshaKigyouDtAddressRepository.save(entityLoad);

        }

        // マスタ本体も必要に応じて修正
        Integer newMasterId = this.updateMaster(callKigyouDtMasterEntityLogic.practice(entityLoad.getKigyouDtKanrenshaCode()),
                userDto, CreateAllAddressUtil.practice(entity.getAddressPostal(), entity.getAddressBlock(),
                        entity.getAddressBuilding()));

        // 住所履歴積み上げ
        KanrenshaKigyouDtAddressEntity entityInsert = this.cloneNewEntity(entity, entityLoad, userDto);
        if (0 != newMasterId) {
            entityInsert.setKanrenshaKigyouDtId(newMasterId);
        }

        if (0 == kanrenshaKigyouDtAddressRepository.save(entityInsert).getKanrenshaKigyouDtAddressId()) {
            return 0;
        } else {
            return 1;
        }

    }

    /**
     * 積み上げ履歴を作成する
     * 
     * @param entity     編集中Entity
     * @param entityLoad 基Entity
     * @param userDto    ユーザ最小限Dto
     * @return 更新時は挿入Id
     */
    private KanrenshaKigyouDtAddressEntity cloneNewEntity(final KanrenshaAddressBaseEntity entity,
            final KanrenshaKigyouDtAddressEntity entityLoad, final LeastUserDto userDto) {

        KanrenshaKigyouDtAddressEntity entitySaved = new KanrenshaKigyouDtAddressEntity();
        BeanUtils.copyProperties(entity, entitySaved);

        entitySaved.setKigyouDtKanrenshaCode(entityLoad.getKigyouDtKanrenshaCode());

        setTableDataHistoryUtil.practiceInsert(userDto, entitySaved);
        entitySaved.setKanrenshaKigyouDtAddressId(0); // auto increment
        return entitySaved;
    }

    /**
     * マスタを更新する
     * 
     * @param masterEntity  マスタEntity
     * @param userDto       ユーザ最小限Dtto
     * @param nextShokugyou 新たな職業
     * @return 新規データId
     */
    private Integer updateMaster(final KanrenshaKigyouDtMasterEntity masterEntity, final LeastUserDto userDto,
            final String nextAddress) {

        // 最新かつ住所が異なる場合はマスタも更新
        
        if (masterEntity.getIsLatest() && !nextAddress.equals(masterEntity.getAllAddress())) {

            KanrenshaKigyouDtMasterEntity masterNewEntity = new KanrenshaKigyouDtMasterEntity();
            BeanUtils.copyProperties(masterEntity, masterNewEntity);
            setTableDataHistoryUtil.practiceDelete(userDto, masterEntity);

            masterNewEntity.setAllAddress(nextAddress);
            setTableDataHistoryUtil.practiceInsert(userDto, masterNewEntity);
            masterNewEntity.setKanrenshaKigyouDtMasterId(0); // auto increment明示

            kanrenshaKigyouDtMasterRepository.save(masterEntity);
            return kanrenshaKigyouDtMasterRepository.save(masterNewEntity).getKanrenshaKigyouDtMasterId();
        }

        return 0; // 変更なし
    }

}
