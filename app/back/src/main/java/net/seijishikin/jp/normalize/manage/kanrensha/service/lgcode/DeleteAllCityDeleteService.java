package net.seijishikin.jp.normalize.manage.kanrensha.service.lgcode;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.address_rsdt.EditAddressCityDeleteCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressCityDeleteEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.AddressCityDeleteRepository;

/**
 * 地方自治体コード削除Service
 */
@Service
public class DeleteAllCityDeleteService {

    /** 地方自治体コード削除Repository */
    @Autowired
    private AddressCityDeleteRepository addressCityDeleteRepository;

    /** テーブル履歴設定Util */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /**
     * 処理を行う
     * 
     * @param capsuleDto 実行条件Dto
     * @return 処理Id
     */
    @Transactional
    public Integer practice(final EditAddressCityDeleteCapsuleDto capsuleDto) {

        Integer deleteId = capsuleDto.getEditEntity().getAddressCityDeleteId();
        Optional<AddressCityDeleteEntity> optional = addressCityDeleteRepository.findById(deleteId);
        if (optional.isEmpty()) {
            throw new EmptyResultDataAccessException("編集対象が検索できません(" + deleteId + ")", 1);
        }

        AddressCityDeleteEntity entity = optional.get();
        setTableDataHistoryUtil.practiceDelete(capsuleDto.getUserDto(), entity);

        return addressCityDeleteRepository.save(entity).getAddressCityDeleteId();
    }

}
