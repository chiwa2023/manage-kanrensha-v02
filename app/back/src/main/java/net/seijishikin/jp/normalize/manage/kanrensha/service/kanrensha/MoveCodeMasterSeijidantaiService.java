package net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.MoveKanrenshaCodeAcceptCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaSeijidantaiAccessEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaSeijidantaiAddressEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaSeijidantaiMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaSeijidantaiPropertyEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaSeijidantaiAccessRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaSeijidantaiAddressRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaSeijidantaiMasterRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaSeijidantaiPropertyRepository;

/**
 * 関連者政治団体コード移動Service
 */
@Service
public class MoveCodeMasterSeijidantaiService {

    /** 関連者政治団体マスタRepository */
    @Autowired
    private KanrenshaSeijidantaiMasterRepository kanrenshaSeijidantaiMasterRepository;

    /** 関連者政治団体住所Repository */
    @Autowired
    private KanrenshaSeijidantaiAddressRepository kanrenshaSeijidantaiAddressRepository;

    /** 関連者政治団体連絡先Repository */
    @Autowired
    private KanrenshaSeijidantaiAccessRepository kanrenshaSeijidantaiAccessRepository;

    /** 関連者政治団体属性Repository */
    @Autowired
    private KanrenshaSeijidantaiPropertyRepository kanrenshaSeijidantaiPropertyRepository;

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
        for (KanrenshaSeijidantaiMasterEntity masterEntity : kanrenshaSeijidantaiMasterRepository
                .findBySeijidantaiKanrenshaCodeAndIsLatestOrderByKanrenshaSeijidantaiMasterIdDesc(abolishCode,
                        SetTableDataHistoryUtil.DELETE_STATE)) {
            Integer oldId = masterEntity.getKanrenshaSeijidantaiMasterId();
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

        List<KanrenshaSeijidantaiAddressEntity> list = new ArrayList<>();

        for (KanrenshaSeijidantaiAddressEntity srcEntity : kanrenshaSeijidantaiAddressRepository
                .findByKanrenshaSeijidantaiIdAndIsLatest(oldId, isSearch)) {

            KanrenshaSeijidantaiAddressEntity newEntity = new KanrenshaSeijidantaiAddressEntity(); // NOPMD NewInstanceInLoop
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setSeijidantaiKanrenshaCode(kanrenshaCode);
            newEntity.setKanrenshaSeijidantaiId(newId);

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

            newEntity.setKanrenshaSeijidantaiAddressId(0); // auto increment明記
            list.add(newEntity);
        }

        kanrenshaSeijidantaiAddressRepository.saveAll(list);
    }

    private void copyAccessEntity(final String kanrenshaCode, final LeastUserDto userDto, final Integer oldId,
            final Integer newId, final boolean isSearch, final boolean isSet) {

        List<KanrenshaSeijidantaiAccessEntity> list = new ArrayList<>();

        for (KanrenshaSeijidantaiAccessEntity srcEntity : kanrenshaSeijidantaiAccessRepository
                .findByKanrenshaSeijidantaiIdAndIsLatest(oldId, isSearch)) {

            KanrenshaSeijidantaiAccessEntity newEntity = new KanrenshaSeijidantaiAccessEntity(); // NOPMD NewInstanceInLoop
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setSeijidantaiKanrenshaCode(kanrenshaCode);
            newEntity.setKanrenshaSeijidantaiId(newId);

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
            newEntity.setKanrenshaSeijidantaiAccessId(0); // auto increment明記
            list.add(newEntity);
        }

        kanrenshaSeijidantaiAccessRepository.saveAll(list);
    }

    private void copyPropertyEntity(final String kanrenshaCode, final LeastUserDto userDto, final Integer oldId,
            final Integer newId, final boolean isSearch, final boolean isSet) {

        List<KanrenshaSeijidantaiPropertyEntity> list = new ArrayList<>();

        for (KanrenshaSeijidantaiPropertyEntity srcEntity : kanrenshaSeijidantaiPropertyRepository
                .findByKanrenshaSeijidantaiIdAndIsLatest(oldId, isSearch)) {

            KanrenshaSeijidantaiPropertyEntity newEntity = new KanrenshaSeijidantaiPropertyEntity(); // NOPMD NewInstanceInLoop
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setSeijidantaiKanrenshaCode(kanrenshaCode);
            newEntity.setKanrenshaSeijidantaiId(newId);

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
            newEntity.setKanrenshaSeijidantaiPropertyId(0); // auto increment明記
            list.add(newEntity);
        }

        kanrenshaSeijidantaiPropertyRepository.saveAll(list);
    }

    private Integer copyMasterEntity(final String kanrenshaCode, final LeastUserDto userDto,
            final KanrenshaSeijidantaiMasterEntity masterEntity) {

        KanrenshaSeijidantaiMasterEntity newEntity = new KanrenshaSeijidantaiMasterEntity();
        BeanUtils.copyProperties(masterEntity, newEntity);

        newEntity.setSeijidantaiKanrenshaCode(kanrenshaCode);

        newEntity.setDeleteTimestamp(SetTableDataHistoryUtil.DELETE_LIMIT_TIMESTAMP);
        setTableDataHistoryUtil.practiceDelete(userDto, newEntity);
        newEntity.setKanrenshaSeijidantaiMasterId(0); // auto increment明記
        return kanrenshaSeijidantaiMasterRepository.save(newEntity).getKanrenshaSeijidantaiMasterId();
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
        for (KanrenshaSeijidantaiMasterEntity srcEntity : kanrenshaSeijidantaiMasterRepository
                .findBySeijidantaiKanrenshaCodeAndIsLatestOrderByKanrenshaSeijidantaiMasterIdDesc(abolishCode,
                        SetTableDataHistoryUtil.INSERT_STATE)) {
            final Integer oldId = srcEntity.getKanrenshaSeijidantaiMasterId();

            KanrenshaSeijidantaiMasterEntity newEntity = new KanrenshaSeijidantaiMasterEntity(); // NOPMD NewInstanceInLoop
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setSeijidantaiKanrenshaCode(kanrenshaCode);

            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaSeijidantaiMasterRepository.save(srcEntity);

            setTableDataHistoryUtil.practiceDelete(userDto, newEntity);
            newEntity.setKanrenshaSeijidantaiMasterId(0); // auto increment明記
            Integer newId = kanrenshaSeijidantaiMasterRepository.save(newEntity).getKanrenshaSeijidantaiMasterId();

            // 作成した最新のIdは他にも伝播
            this.copyAddressEntity(kanrenshaCode, userDto, oldId, newId, isSearch, isSet);
            this.copyAccessEntity(kanrenshaCode, userDto, oldId, newId, isSearch, isSet);
            this.copyPropertyEntity(kanrenshaCode, userDto, oldId, newId, isSearch, isSet);
        }

    }

    private void changeOrgToDelete(final String kanrenshaCode, final LeastUserDto userDto) {

        // 新コード最新を履歴にする
        for (KanrenshaSeijidantaiMasterEntity srcEntity : kanrenshaSeijidantaiMasterRepository
                .findBySeijidantaiKanrenshaCodeAndIsLatestOrderByKanrenshaSeijidantaiMasterIdDesc(kanrenshaCode,
                        SetTableDataHistoryUtil.INSERT_STATE)) {
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaSeijidantaiMasterRepository.save(srcEntity);
        }

        for (KanrenshaSeijidantaiAddressEntity srcEntity : kanrenshaSeijidantaiAddressRepository
                .findBySeijidantaiKanrenshaCodeAndIsLatestOrderByKanrenshaSeijidantaiAddressIdDesc(kanrenshaCode,
                        SetTableDataHistoryUtil.INSERT_STATE)) {
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaSeijidantaiAddressRepository.save(srcEntity);
        }

        for (KanrenshaSeijidantaiAccessEntity srcEntity : kanrenshaSeijidantaiAccessRepository
                .findBySeijidantaiKanrenshaCodeAndIsLatestOrderByKanrenshaSeijidantaiAccessIdDesc(kanrenshaCode,
                        SetTableDataHistoryUtil.INSERT_STATE)) {
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaSeijidantaiAccessRepository.save(srcEntity);
        }

        for (KanrenshaSeijidantaiPropertyEntity srcEntity : kanrenshaSeijidantaiPropertyRepository
                .findBySeijidantaiKanrenshaCodeAndIsLatestOrderByKanrenshaSeijidantaiPropertyIdDesc(kanrenshaCode,
                        SetTableDataHistoryUtil.INSERT_STATE)) {
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaSeijidantaiPropertyRepository.save(srcEntity);
        }

    }

    private void changeAbolishToLatest(final String kanrenshaCode, final String abolishCode,
            final LeastUserDto userDto) {

        // 旧コード最新を新コード最新にする
        boolean isSearch = SetTableDataHistoryUtil.INSERT_STATE;
        boolean isSet = SetTableDataHistoryUtil.INSERT_STATE;
        for (KanrenshaSeijidantaiMasterEntity srcEntity : kanrenshaSeijidantaiMasterRepository
                .findBySeijidantaiKanrenshaCodeAndIsLatestOrderByKanrenshaSeijidantaiMasterIdDesc(abolishCode,
                        SetTableDataHistoryUtil.INSERT_STATE)) {

            KanrenshaSeijidantaiMasterEntity newEntity = new KanrenshaSeijidantaiMasterEntity(); // NOPMD NewInstanceInLoop
            final Integer oldId = srcEntity.getKanrenshaSeijidantaiMasterId();

            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setSeijidantaiKanrenshaCode(kanrenshaCode);

            // 旧コード最新を履歴
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaSeijidantaiMasterRepository.save(srcEntity);

            // 新コード最新を履歴
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaSeijidantaiMasterId(0); // auto increment明記
            Integer newId = kanrenshaSeijidantaiMasterRepository.save(newEntity).getKanrenshaSeijidantaiMasterId();

            // 作成した最新のIdは他にも伝播
            this.copyAddressEntity(kanrenshaCode, userDto, oldId, newId, isSearch, isSet);
            this.copyAccessEntity(kanrenshaCode, userDto, oldId, newId, isSearch, isSet);
            this.copyPropertyEntity(kanrenshaCode, userDto, oldId, newId, isSearch, isSet);

        }

    }

}
