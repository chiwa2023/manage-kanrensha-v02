package net.seijishikin.jp.normalize.manage.kanrensha.service.works_approval;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonPropertyEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.CallPersonMasterEntityLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaPersonMasterRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaPersonPropertyRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateAllShokugyoUtil;

/**
 * 職業承認作業保存Service
 */
@Service
public class SaveApprovalShokugyouService {

    /** 関連者個人属性Respository */
    @Autowired
    private KanrenshaPersonPropertyRepository kanrenshaPersonPropertyRepository;

    /** 関連者個人マスタRespository */
    @Autowired
    private KanrenshaPersonMasterRepository kanrenshaPersonMasterRepository;

    /** 関連者個人マスタ呼び出しLogic */
    @Autowired
    private CallPersonMasterEntityLogic callPersonMasterEntityLogic;

    /** テーブル履歴設定Util */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /**
     * 処理を行う
     *
     * @param listShokugyou 関連者個人基本リスト
     * @param userDto       職業最小限Dto
     * @return 処理行数
     */
    public Integer practice(final List<KanrenshaPersonPropertyEntity> listShokugyou, final LeastUserDto userDto) {

        List<KanrenshaPersonPropertyEntity> listHistory = new ArrayList<>();
        List<KanrenshaPersonPropertyEntity> listInsert = new ArrayList<>();

        for (KanrenshaPersonPropertyEntity entity : listShokugyou) {
            KanrenshaPersonPropertyEntity entityHistory = this.judgeSaveEntity(entity, userDto);
            if (!Objects.isNull(entityHistory)) {
                listHistory.add(entityHistory);

                // マスタも変更
                KanrenshaPersonMasterEntity masterEntity = callPersonMasterEntityLogic
                        .practice(entityHistory.getPersonKanrenshaCode());

                Integer newMasterId = this.updateMaster(masterEntity, userDto, CreateAllShokugyoUtil.practice(entity));
                KanrenshaPersonPropertyEntity newPropertyEntity = this.cloneNewEntity(entity, userDto);

                // マスタを変更した場合はそのIdをセットして更新対象とする
                if (newMasterId != 0) {
                    newPropertyEntity.setKanrenshaPersonId(newMasterId);
                }

                listInsert.add(newPropertyEntity);
            }
        }

        kanrenshaPersonPropertyRepository.saveAll(listHistory);
        return kanrenshaPersonPropertyRepository.saveAll(listInsert).size();
    }

    /**
     * 更新対象が判定する
     * 
     * @param entity  編集Entity
     * @param userDto ユーザ最小限Dto
     * @return 更新するEntity
     */
    private KanrenshaPersonPropertyEntity judgeSaveEntity(final KanrenshaPersonPropertyEntity entity,
            final LeastUserDto userDto) {

        // Idで取得できない時は全処理中断
        KanrenshaPersonPropertyEntity previousEntity = kanrenshaPersonPropertyRepository
                .findById(entity.getKanrenshaPersonPropertyId()).get();

        // 職業に一切変更がなければnullを戻してこのEntityについては処理をしない
        if (Objects.equals(entity.getGyoushu(), previousEntity.getGyoushu())
                && Objects.equals(entity.getYakushoku(), previousEntity.getYakushoku())
                && Objects.equals(entity.getShokugyouUserWrite(), previousEntity.getShokugyouUserWrite())
                && Objects.equals(entity.getKigyouDtNo(), previousEntity.getKigyouDtNo())
                && Objects.equals(entity.getKigyouDtName(), previousEntity.getKigyouDtName())
                && Objects.equals(entity.getKigyouDtAddress(), previousEntity.getKigyouDtAddress())
                && Objects.equals(entity.getIsShokyouEdit(), previousEntity.getIsShokyouEdit())
                && Objects.equals(entity.getIsShokyouAccept(), previousEntity.getIsShokyouAccept())) {
            return null;
        } else {
            // 変更があった場合は履歴に変更したEntityを戻す
            KanrenshaPersonPropertyEntity entityChange = new KanrenshaPersonPropertyEntity();
            BeanUtils.copyProperties(previousEntity, entityChange);
            setTableDataHistoryUtil.practiceDelete(userDto, entityChange);
            return entityChange;
        }
    }

    /**
     * 編集Entityを新規データとする
     * 
     * @param entity  新規Entity
     * @param userDto ユーザ最小限Dto
     * @return 新規Entity
     */
    private KanrenshaPersonPropertyEntity cloneNewEntity(final KanrenshaPersonPropertyEntity entity,
            final LeastUserDto userDto) {

        KanrenshaPersonPropertyEntity entityChange = new KanrenshaPersonPropertyEntity();

        BeanUtils.copyProperties(entity, entityChange);
        entityChange.setKanrenshaPersonPropertyId(0);
        setTableDataHistoryUtil.practiceInsert(userDto, entityChange);
        return entityChange;
    }

    /**
     * マスタを更新する
     * 
     * @param masterEntity マスタEntity
     * @param userDto ユーザ最小限Dtto
     * @param nextShokugyou　新たな職業
     * @return 新規データId
     */
    private Integer updateMaster(final KanrenshaPersonMasterEntity masterEntity, final LeastUserDto userDto,
            final String nextShokugyou) {

        // 最新かつ職業が異なる場合はマスタも更新
        if (masterEntity.getIsLatest() && !nextShokugyou.equals(masterEntity.getPersonShokugyou())) {

            KanrenshaPersonMasterEntity masterNewEntity = new KanrenshaPersonMasterEntity();
            BeanUtils.copyProperties(masterEntity, masterNewEntity);
            setTableDataHistoryUtil.practiceDelete(userDto, masterEntity);

            masterNewEntity.setPersonShokugyou(nextShokugyou);
            setTableDataHistoryUtil.practiceInsert(userDto, masterNewEntity);
            masterNewEntity.setKanrenshaPersonMasterId(0); // auto increment明示

            kanrenshaPersonMasterRepository.save(masterEntity);
            return kanrenshaPersonMasterRepository.save(masterNewEntity).getKanrenshaPersonMasterId();
        }
        
        return 0; // 変更なし
    }

}
