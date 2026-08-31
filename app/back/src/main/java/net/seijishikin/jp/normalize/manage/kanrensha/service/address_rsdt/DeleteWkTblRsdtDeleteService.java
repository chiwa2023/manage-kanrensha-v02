package net.seijishikin.jp.normalize.manage.kanrensha.service.address_rsdt;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.address_rsdt.EditWktblRsdtDeleteCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblAddressRsdtDeleteEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblAddressRsdtDeleteRepository;

/**
 * 住居差分ワークテーブル削除削除Service
 */
@Service
public class DeleteWkTblRsdtDeleteService {

    /** 住居差分ワークテーブル削除Repository */
    @Autowired
    private WkTblAddressRsdtDeleteRepository wkTblAddressRsdtDeleteRepository;

    /** テーブル履歴設定Util */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /**
     * 処理を行う
     * 
     * @param capsuleDto 処理対象Dto
     * @return 処理Id
     */
    @Transactional
    public Integer practice(final EditWktblRsdtDeleteCapsuleDto capsuleDto) {

        Integer deleteId = capsuleDto.getEditEntity().getWkTblAddressRsdtDeleteId();
        Optional<WkTblAddressRsdtDeleteEntity> optional = wkTblAddressRsdtDeleteRepository.findById(deleteId);
        if (optional.isEmpty()) {
            throw new EmptyResultDataAccessException("編集対象が検索できません(" + deleteId + ")", 1);
        }

        WkTblAddressRsdtDeleteEntity entity = optional.get();
        setTableDataHistoryUtil.practiceDelete(capsuleDto.getUserDto(), entity);

        return wkTblAddressRsdtDeleteRepository.save(entity).getWkTblAddressRsdtDeleteId();
    }

}
