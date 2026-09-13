package net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.MoveKanrenshaCodeAcceptCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonAccessEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonAddressEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonPropertyEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaPersonAccessRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaPersonAddressRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaPersonMasterRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaPersonPropertyRepository;

/**
 * 関連者個人コード移動Service
 */
@Service
public class MoveCodeMasterPersonService {

    /** 関連者個人マスタRepository */
    @Autowired
    private KanrenshaPersonMasterRepository kanrenshaPersonMasterRepository;

    /** 関連者個人住所Repository */
    @Autowired
    private KanrenshaPersonAddressRepository kanrenshaPersonAddressRepository;

    /** 関連者個人連絡先Repository */
    @Autowired
    private KanrenshaPersonAccessRepository kanrenshaPersonAccessRepository;

    /** 関連者個人属性Repository */
    @Autowired
    private KanrenshaPersonPropertyRepository kanrenshaPersonPropertyRepository;

    /** テーブル履歴設定Util */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /**
     * 処理を行う
     * 
     * @param capsuleDto 処理条件Dto
     * @return 処理結果
     */
    public boolean practice(final MoveKanrenshaCodeAcceptCapsuleDto capsuleDto) {
        // すべての作業を一貫して行い、できない場合は巻き戻しが必要なのでここで@Transactionalは使用しない

        boolean isAbolishLast = capsuleDto.getKanrenshaCodeMoveEntity().getIsAbolishLast();

        String orgCode = capsuleDto.getKanrenshaCodeMoveEntity().getOriginKanrenshaCode();
        String abolishCode = capsuleDto.getKanrenshaCodeMoveEntity().getAbolishKanrenshaCode();
        LeastUserDto userDto = capsuleDto.getUserDto();

        // 旧コードマスタ履歴を新コードマスタの履歴として複写
        boolean isSearch = SetTableDataHistoryUtil.DELETE_STATE;
        boolean isSet = SetTableDataHistoryUtil.DELETE_STATE;
        for (KanrenshaPersonMasterEntity masterEntity : kanrenshaPersonMasterRepository
                .findByPersonKanrenshaCodeAndIsLatestOrderByKanrenshaPersonMasterIdDesc(abolishCode,
                        SetTableDataHistoryUtil.DELETE_STATE)) {
            Integer oldId = masterEntity.getKanrenshaPersonMasterId();
            Integer newId = this.copyMasterEntity(orgCode, userDto, masterEntity);

            this.copyAddressEntity(orgCode, userDto, oldId, newId, isSearch, isSet);
            this.copyAccessEntity(orgCode, userDto, oldId, newId, isSearch, isSet);
            this.copyPropertyEntity(orgCode, userDto, oldId, newId, isSearch, isSet);
        }

        if (isAbolishLast) {
            // 廃止コードの最新を最新にしたい場合
            
            // 新コードの最新を履歴に変換
            this.changeOrgToDelete(orgCode, userDto);
            // 旧コードの最新を新コードの最新として追加
            this.changeAbolishToLatest(orgCode, abolishCode, userDto);
        } else {
            // 維持コードの最新をそのまま最新にする
            
            // 旧コードの最新を履歴にして新コードの履歴として追加
            this.changeAbolishToDelete(orgCode, abolishCode, userDto);
        }

        return true;
    }

    private void copyAddressEntity(final String kanrenshaCode, final LeastUserDto userDto, final Integer oldId,
            final Integer newId, final boolean isSearch, final boolean isSet) {

        List<KanrenshaPersonAddressEntity> list = new ArrayList<>();

        for (KanrenshaPersonAddressEntity srcEntity : kanrenshaPersonAddressRepository
                .findByKanrenshaPersonIdAndIsLatest(oldId, isSearch)) {

            KanrenshaPersonAddressEntity newEntity = new KanrenshaPersonAddressEntity(); // NOPMD NewInstanceInLoop
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setPersonKanrenshaCode(kanrenshaCode);
            newEntity.setKanrenshaPersonId(newId);

            if (isSearch && !isSet) {
                setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
                list.add(srcEntity);
            }
            if (isSet) {
                setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            } else {
                newEntity.setDeleteTimestamp(SetTableDataHistoryUtil.DELETE_LIMIT_TIMESTAMP);
                setTableDataHistoryUtil.practiceDelete(userDto, newEntity);
            }

            newEntity.setKanrenshaPersonAddressId(0); // auto increment明記
            list.add(newEntity);
        }

        kanrenshaPersonAddressRepository.saveAll(list);
    }

    private void copyAccessEntity(final String kanrenshaCode, final LeastUserDto userDto, final Integer oldId,
            final Integer newId, final boolean isSearch, final boolean isSet) {

        List<KanrenshaPersonAccessEntity> list = new ArrayList<>();

        for (KanrenshaPersonAccessEntity srcEntity : kanrenshaPersonAccessRepository
                .findByKanrenshaPersonIdAndIsLatest(oldId, isSearch)) {

            KanrenshaPersonAccessEntity newEntity = new KanrenshaPersonAccessEntity(); // NOPMD NewInstanceInLoop
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setPersonKanrenshaCode(kanrenshaCode);
            newEntity.setKanrenshaPersonId(newId);

            if (isSearch && !isSet) {
                setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
                list.add(srcEntity);
            }
            if (isSet) {
                setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            } else {
                newEntity.setDeleteTimestamp(SetTableDataHistoryUtil.DELETE_LIMIT_TIMESTAMP);
                setTableDataHistoryUtil.practiceDelete(userDto, newEntity);
            }
            newEntity.setKanrenshaPersonAccessId(0); // auto increment明記
            list.add(newEntity);
        }

        kanrenshaPersonAccessRepository.saveAll(list);
    }

    private void copyPropertyEntity(final String kanrenshaCode, final LeastUserDto userDto, final Integer oldId,
            final Integer newId, final boolean isSearch, final boolean isSet) {

        List<KanrenshaPersonPropertyEntity> list = new ArrayList<>();

        for (KanrenshaPersonPropertyEntity srcEntity : kanrenshaPersonPropertyRepository
                .findByKanrenshaPersonIdAndIsLatest(oldId, isSearch)) {

            KanrenshaPersonPropertyEntity newEntity = new KanrenshaPersonPropertyEntity(); // NOPMD NewInstanceInLoop
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setPersonKanrenshaCode(kanrenshaCode);
            newEntity.setKanrenshaPersonId(newId);

            if (isSearch && !isSet) {
                setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
                list.add(srcEntity);
            }
            if (isSet) {
                setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            } else {
                newEntity.setDeleteTimestamp(SetTableDataHistoryUtil.DELETE_LIMIT_TIMESTAMP);
                setTableDataHistoryUtil.practiceDelete(userDto, newEntity);
            }
            newEntity.setKanrenshaPersonPropertyId(0); // auto increment明記
            list.add(newEntity);
        }

        kanrenshaPersonPropertyRepository.saveAll(list);
    }

    private Integer copyMasterEntity(final String kanrenshaCode, final LeastUserDto userDto,
            final KanrenshaPersonMasterEntity masterEntity) {

        KanrenshaPersonMasterEntity newEntity = new KanrenshaPersonMasterEntity();
        BeanUtils.copyProperties(masterEntity, newEntity);

        newEntity.setPersonKanrenshaCode(kanrenshaCode);

        newEntity.setDeleteTimestamp(SetTableDataHistoryUtil.DELETE_LIMIT_TIMESTAMP);
        setTableDataHistoryUtil.practiceDelete(userDto, newEntity);
        newEntity.setKanrenshaPersonMasterId(0); // auto increment明記
        return kanrenshaPersonMasterRepository.save(newEntity).getKanrenshaPersonMasterId();
    }

    /**
     * 廃止コード最新を元コード履歴に変換する
     * 
     * @param kanrenshaCode 元コード
     * @param abolishCode   廃止コード
     * @param userDto       ユーザ最小限
     */
    private void changeAbolishToDelete(final String kanrenshaCode, final String abolishCode,
            final LeastUserDto userDto) {

        // 廃止コード最新をループして廃止コードを履歴、新コードを履歴として追加
        boolean isSearch = SetTableDataHistoryUtil.INSERT_STATE;
        boolean isSet = SetTableDataHistoryUtil.DELETE_STATE;
        for (KanrenshaPersonMasterEntity srcEntity : kanrenshaPersonMasterRepository
                .findByPersonKanrenshaCodeAndIsLatestOrderByKanrenshaPersonMasterIdDesc(abolishCode,
                        SetTableDataHistoryUtil.INSERT_STATE)) {
            final Integer oldId = srcEntity.getKanrenshaPersonMasterId();

            KanrenshaPersonMasterEntity newEntity = new KanrenshaPersonMasterEntity(); // NOPMD NewInstanceInLoop
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setPersonKanrenshaCode(kanrenshaCode);

            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaPersonMasterRepository.save(srcEntity);

            setTableDataHistoryUtil.practiceDelete(userDto, newEntity);
            newEntity.setKanrenshaPersonMasterId(0); // auto increment明記
            Integer newId = kanrenshaPersonMasterRepository.save(newEntity).getKanrenshaPersonMasterId();

            // 作成した最新のIdは他にも伝播
            this.copyAddressEntity(kanrenshaCode, userDto, oldId, newId, isSearch, isSet);
            this.copyAccessEntity(kanrenshaCode, userDto, oldId, newId, isSearch, isSet);
            this.copyPropertyEntity(kanrenshaCode, userDto, oldId, newId, isSearch, isSet);
        }

    }

    private void changeOrgToDelete(final String kanrenshaCode, final LeastUserDto userDto) {

        // 新コード最新を履歴にする
        for (KanrenshaPersonMasterEntity srcEntity : kanrenshaPersonMasterRepository
                .findByPersonKanrenshaCodeAndIsLatestOrderByKanrenshaPersonMasterIdDesc(kanrenshaCode,
                        SetTableDataHistoryUtil.INSERT_STATE)) {
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaPersonMasterRepository.save(srcEntity);
        }

        for (KanrenshaPersonAddressEntity srcEntity : kanrenshaPersonAddressRepository
                .findByPersonKanrenshaCodeAndIsLatestOrderByKanrenshaPersonAddressIdDesc(kanrenshaCode,
                        SetTableDataHistoryUtil.INSERT_STATE)) {
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaPersonAddressRepository.save(srcEntity);
        }

        for (KanrenshaPersonAccessEntity srcEntity : kanrenshaPersonAccessRepository
                .findByPersonKanrenshaCodeAndIsLatestOrderByKanrenshaPersonAccessIdDesc(kanrenshaCode,
                        SetTableDataHistoryUtil.INSERT_STATE)) {
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaPersonAccessRepository.save(srcEntity);
        }

        for (KanrenshaPersonPropertyEntity srcEntity : kanrenshaPersonPropertyRepository
                .findByPersonKanrenshaCodeAndIsLatestOrderByKanrenshaPersonPropertyIdDesc(kanrenshaCode,
                        SetTableDataHistoryUtil.INSERT_STATE)) {
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaPersonPropertyRepository.save(srcEntity);
        }

    }

    private void changeAbolishToLatest(final String kanrenshaCode, final String abolishCode,
            final LeastUserDto userDto) {

        // 旧コード最新を新コード最新にする
        boolean isSearch = SetTableDataHistoryUtil.INSERT_STATE;
        boolean isSet = SetTableDataHistoryUtil.INSERT_STATE;
        for (KanrenshaPersonMasterEntity srcEntity : kanrenshaPersonMasterRepository
                .findByPersonKanrenshaCodeAndIsLatestOrderByKanrenshaPersonMasterIdDesc(abolishCode,
                        SetTableDataHistoryUtil.INSERT_STATE)) {

            KanrenshaPersonMasterEntity newEntity = new KanrenshaPersonMasterEntity(); // NOPMD NewInstanceInLoop
            final Integer oldId = srcEntity.getKanrenshaPersonMasterId();

            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setPersonKanrenshaCode(kanrenshaCode);

            // 旧コード最新を履歴
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaPersonMasterRepository.save(srcEntity);

            // 新コード最新を履歴
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaPersonMasterId(0); // auto increment明記
            Integer newId = kanrenshaPersonMasterRepository.save(newEntity).getKanrenshaPersonMasterId();

            // 作成した最新のIdは他にも伝播
            this.copyAddressEntity(kanrenshaCode, userDto, oldId, newId, isSearch, isSet);
            this.copyAccessEntity(kanrenshaCode, userDto, oldId, newId, isSearch, isSet);
            this.copyPropertyEntity(kanrenshaCode, userDto, oldId, newId, isSearch, isSet);

        }

    }

}
