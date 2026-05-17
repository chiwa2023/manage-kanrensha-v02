package net.seijishikin.jp.normalize.manage.kanrensha.service.riyousha;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.UserRoleConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.RiyoushaCombineOrgEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.RiyoushaPartnerApiMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.RiyoushaPersonPropertyEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.UserRoleEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.RiyoushaCombineOrgRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.RiyoushaPartnerApiMasterRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.RiyoushaPersonPropertyRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.UserRoleRepository;

/**
 * 利用者APIパートナー削除Service
 */
@Service
public class DeleteRiyoushaPartnerApiService {

    /** 利用者APIパートナーマスタRepository */
    @Autowired
    private RiyoushaPartnerApiMasterRepository riyoushaPartnerApiMasterRepository;

    /** 利用者個人属性Repository */
    @Autowired
    private RiyoushaPersonPropertyRepository riyoushaPersonPropertyRepository;

    /** 利用者組織紐づけ個人Respoitory */
    @Autowired
    private RiyoushaCombineOrgRepository riyoushaCombineOrgRepository;

    /** ユーザ権限Respoitory */
    @Autowired
    private UserRoleRepository userRoleRepository;

    /** テーブル履歴設定utility */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /**
     * 処理を行う
     * 
     * @param riyoushaCode 利用者コード
     * @param userDto      ユーザ最小限Dto
     * @return マスタ更新サイズ
     */
    @Transactional
    public Integer practice(final Integer riyoushaCode, final LeastUserDto userDto) {

        // 組織との紐づけを全削除
        List<RiyoushaCombineOrgEntity> listCombine = riyoushaCombineOrgRepository
                .findByPersonRiyoushaCodeAndRiyoushaRoleAndIsLatestTrue(riyoushaCode, UserRoleConstants.PARTNER_API);

        for (RiyoushaCombineOrgEntity entity : listCombine) {
            setTableDataHistoryUtil.practiceDelete(userDto, entity);
        }
        Integer updateSize = riyoushaCombineOrgRepository.saveAll(listCombine).size();

        // 最新(1件しかないはず)マスタ全削除
        List<RiyoushaPartnerApiMasterEntity> listMaster = riyoushaPartnerApiMasterRepository
                .findByRiyoushaPartnerApiMasterCodeAndIsLatestTrue(riyoushaCode);
        for (RiyoushaPartnerApiMasterEntity entity : listMaster) {
            // 紐づく属性も削除
            RiyoushaPersonPropertyEntity propertyEntity = this.getpropertyEntity(entity.getRiyoushaPersonPropertyId());
            if (!Objects.isNull(listMaster)) {
                setTableDataHistoryUtil.practiceDelete(userDto, propertyEntity);
                riyoushaPersonPropertyRepository.save(propertyEntity);
                updateSize++;
            }
            setTableDataHistoryUtil.practiceDelete(userDto, entity);
        }

        // 該当の権限のみを削除(当然ユーザは残す)
        List<UserRoleEntity> listRole = userRoleRepository.findRiyoushaCodeAndRole(riyoushaCode,
                UserRoleConstants.PARTNER_API);
        final int sizeOnlyManager = 1;
        if (sizeOnlyManager == listRole.size()) {
            // 他の権限がある場合は該当権限関連者コードを初期化する(ログインして回復する余地を残す)
            UserRoleEntity deleteRoleEntityOld = listRole.get(0);
            UserRoleEntity deleteRoleEntityNew = new UserRoleEntity();
            BeanUtils.copyProperties(deleteRoleEntityOld, deleteRoleEntityNew);
            setTableDataHistoryUtil.practiceDelete(userDto, deleteRoleEntityOld);

            deleteRoleEntityNew.setRiyoushaCode(0);
            setTableDataHistoryUtil.practiceInsert(userDto, deleteRoleEntityNew);
            deleteRoleEntityNew.setUserRoleId(0); // auto increment明記

            userRoleRepository.save(deleteRoleEntityOld);
            userRoleRepository.save(deleteRoleEntityNew);
            updateSize += 2;
        } else {
            // 他の権限がある場合は該当権限のみを削除する
            for (UserRoleEntity entity : listRole) {
                if (UserRoleConstants.PARTNER_API.equals(entity.getRole())) {
                    setTableDataHistoryUtil.practiceDelete(userDto, entity);
                    userRoleRepository.save(entity);
                    updateSize++;
                }
            }
        }

        updateSize += riyoushaPartnerApiMasterRepository.saveAll(listMaster).size();
        return updateSize;
    }

    private RiyoushaPersonPropertyEntity getpropertyEntity(final Integer propertyId) {

        Optional<RiyoushaPersonPropertyEntity> optional = riyoushaPersonPropertyRepository.findById(propertyId);
        if (!optional.isEmpty()) {
            RiyoushaPersonPropertyEntity propertyEntity = optional.get();
            if (propertyEntity.getIsLatest()) {
                return propertyEntity;
            }
        }
        return null;
    }
}
