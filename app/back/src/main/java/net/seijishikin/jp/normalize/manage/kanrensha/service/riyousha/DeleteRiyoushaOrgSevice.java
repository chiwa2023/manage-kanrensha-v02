package net.seijishikin.jp.normalize.manage.kanrensha.service.riyousha;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;
import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha.DeleteRiyoushaOrgCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.RiyoushaCombineOrgEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.RiyoushaOrgMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.RiyoushaOrgPropertyEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.RiyoushaCombineOrgRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.RiyoushaOrgMasterRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.RiyoushaOrgPropertyRepository;

/**
 * 利用者組織削除Service
 */
@Service
public class DeleteRiyoushaOrgSevice {

    /** 利用者組織マスタRepository */
    @Autowired
    private RiyoushaOrgMasterRepository riyoushaOrgMasterRepository;

    /** 利用者組織属性Repository */
    @Autowired
    private RiyoushaOrgPropertyRepository riyoushaOrgPropertyRepository;

    /** 利用者組織個人紐づけRepository */
    @Autowired
    private RiyoushaCombineOrgRepository riyoushaCombineOrgRepository;

    /** テーブル履歴設定Utility */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /**
     * 処理を行う
     * 
     * @param capsuleDto 削除条件Dto
     * @return 処理結果Dto
     */
    @Transactional
    public FrameworkMessageAndResultDto practice(final DeleteRiyoushaOrgCapsuleDto capsuleDto) {

        // 削除するマスタを呼び出し
        Optional<RiyoushaOrgMasterEntity> optionalMaster = riyoushaOrgMasterRepository
                .findById(capsuleDto.getMasterEntity().getRiyoushaOrgMasterId());

        FrameworkMessageAndResultDto resultDto = new FrameworkMessageAndResultDto();
        // マスタが呼び出せなければ失敗
        if (optionalMaster.isEmpty()) {
            resultDto.setIsFailure(true);
            resultDto.setMessage("削除できませんでした");
            return resultDto;
        }

        RiyoushaOrgMasterEntity masterEntity = optionalMaster.get();

        // 削除する属性を呼び出し、できなかったら離脱
        Optional<RiyoushaOrgPropertyEntity> optionalProperty = riyoushaOrgPropertyRepository
                .findById(masterEntity.getRiyoushaOrgPropertyId());
        if (optionalProperty.isEmpty()) {
            resultDto.setIsFailure(true);
            resultDto.setMessage("削除できませんでした");
            return resultDto;
        }

        // 更新すべきデータがそろったら保存
        LeastUserDto userDto = capsuleDto.getUserDto();
        setTableDataHistoryUtil.practiceDelete(userDto, masterEntity);
        RiyoushaOrgPropertyEntity propertyEntity = optionalProperty.get();
        setTableDataHistoryUtil.practiceDelete(userDto, propertyEntity);

        riyoushaOrgMasterRepository.save(masterEntity);
        riyoushaOrgPropertyRepository.save(propertyEntity);

        List<RiyoushaCombineOrgEntity> listPerson = riyoushaCombineOrgRepository
                .findByOrgRiyoushaCodeAndIsLatestTrue(masterEntity.getRiyoushaOrgMasterCode());
        for (RiyoushaCombineOrgEntity personEntity : listPerson) {
            setTableDataHistoryUtil.practiceDelete(userDto, personEntity);
        }
        riyoushaCombineOrgRepository.saveAll(listPerson);

        resultDto.setMessage("正常に削除できました");

        // MEMO SE権限で他人を削除時に通知するかは再検討する
        
        return resultDto;
    }
}
