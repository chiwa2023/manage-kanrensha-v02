package net.seijishikin.jp.normalize.manage.kanrensha.service.address_rsdt;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.address_rsdt.EditWktblRsdtChangeCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblAddressRsdtChangeEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblAddressRsdtChangeRepository;

/**
 * 住居差分ワークテーブル変更削除Service
 */
@Service
public class DeleteWkTblRsdtChangeService {

    /** 住居差分ワークテーブル変更Repository */
    @Autowired
    private WkTblAddressRsdtChangeRepository wkTblAddressRsdtChangeRepository;

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
    public Integer practice(final EditWktblRsdtChangeCapsuleDto capsuleDto) {

        Integer deleteId = capsuleDto.getEditEntiy().getWkTblAddressRsdtChangeId();
        Optional<WkTblAddressRsdtChangeEntity> optional = wkTblAddressRsdtChangeRepository.findById(deleteId);
        if (optional.isEmpty()) {
            throw new EmptyResultDataAccessException("編集対象が検索できません(" + deleteId + ")", 1);
        }

        WkTblAddressRsdtChangeEntity entity = optional.get();
        setTableDataHistoryUtil.practiceDelete(capsuleDto.getUserDto(), entity);

        return wkTblAddressRsdtChangeRepository.save(entity).getWkTblAddressRsdtChangeId();
    }

}
