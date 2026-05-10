package net.seijishikin.jp.normalize.manage.kanrensha.service.riyousha;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.UserRoleConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.RiyoushaCombineOrgEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.RiyoushaCombineOrgRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.RiyoushaManagerMasterRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.RiyoushaOrgMasterRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.RiyoushaPartnerApiMasterRepository;

/**
 * 利用者組織紐づけService
 */
@Service
public class InsertRiyoushaCombinePersonService {

    /** 利用者組織紐づけ個人Respoitory */
    @Autowired
    private RiyoushaCombineOrgRepository riyoushaCombineOrgRepository;

    /** 利用者運営者マスタRespoitory */
    @Autowired
    private RiyoushaManagerMasterRepository riyoushaManagerMasterRepository;

    /** 利用者APIパートナーマスタRespoitory */
    @Autowired
    private RiyoushaPartnerApiMasterRepository riyoushaPartnerApiMasterRepository;

    /** 利用者組織マスタRespoitory */
    @Autowired
    private RiyoushaOrgMasterRepository riyoushaOrgMasterRepository;

    /** テーブル履歴設定Utility */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /**
     * 処理を行う
     * 
     * @param orgEntity 利用者組織個人紐づけEntity
     * @param userDto   ユーザ最小限Dto
     * @return 処理後Id
     */
    @Transactional
    public Integer practice(final RiyoushaCombineOrgEntity orgEntity, final LeastUserDto userDto) {

        boolean isCorrectPerson = this.isCorrectPerson(orgEntity.getRiyoushaRole(), orgEntity.getPersonRiyoushaCode(),
                orgEntity.getPersonRiyoushaName());
        boolean isCorrectOrg = this.isCorrectOrg(orgEntity.getOrgRiyoushaCode(), orgEntity.getOrgName());

        // 個人か組織、どちらかが不正確な場合は処理しない
        if (!isCorrectPerson) {
            throw new EmptyResultDataAccessException("指定した個人が利用者に存在しませんでした", 1);
        }
        if (!isCorrectOrg) {
            throw new EmptyResultDataAccessException("指定した組織が利用者に存在しませんでした。", 1);
        }

        Integer code = 1;
        Optional<RiyoushaCombineOrgEntity> optional = riyoushaCombineOrgRepository
                .findFirstByOrderByRiyoushaCombineOrgCodeDesc();
        if (!optional.isEmpty()) {
            code += optional.get().getRiyoushaCombineOrgCode();
        }
        orgEntity.setRiyoushaCombineOrgCode(code);
        setTableDataHistoryUtil.practiceInsert(userDto, orgEntity);
        orgEntity.setRiyoushaCombineOrgId(0); // auto increment明記

        return riyoushaCombineOrgRepository.save(orgEntity).getRiyoushaCombineOrgId();

    }

    private boolean isCorrectOrg(final Integer code, final String name) {
        return !riyoushaOrgMasterRepository.findByRiyoushaOrgMasterCodeAndAllNameAndIsLatestTrue(code, name).isEmpty();
    }

    private boolean isCorrectPerson(final String role, final Integer code, final String name) {
        if (UserRoleConstants.MANAGER.equals(role)) {
            return !riyoushaManagerMasterRepository.findByRiyoushaManagerMasterCodeAndAllNameAndIsLatestTrue(code, name)
                    .isEmpty();
        } else {
            return !riyoushaPartnerApiMasterRepository
                    .findByRiyoushaPartnerApiMasterCodeAndAllNameAndIsLatestTrue(code, name).isEmpty();
        }
    }

}
