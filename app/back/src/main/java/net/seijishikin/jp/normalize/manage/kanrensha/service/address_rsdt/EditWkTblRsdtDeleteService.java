package net.seijishikin.jp.normalize.manage.kanrensha.service.address_rsdt;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.address_rsdt.EditWktblRsdtDeleteCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblAddressRsdtDeleteEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblAddressRsdtDeleteRepository;

/**
 * 住居差分ワークテーブル削除編集Service
 */
@Service
public class EditWkTblRsdtDeleteService {

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
     * @return 処理件数
     */
    @Transactional
    public Integer practice(final EditWktblRsdtDeleteCapsuleDto capsuleDto) {

        WkTblAddressRsdtDeleteEntity entityEdit = capsuleDto.getEditEntity();
        Integer editId = entityEdit.getWkTblAddressRsdtDeleteId();

        LeastUserDto userDto = capsuleDto.getUserDto();
        // 編集前データを履歴にする(新規の場合は履歴にする作業が不要)
        if (0 != editId) {
            this.changeHistory(editId, userDto);
        }

        // 編集後データを最新にする
        setTableDataHistoryUtil.practiceInsert(userDto, entityEdit);
        entityEdit.setWkTblAddressRsdtDeleteId(0); // auto increment明記

        return wkTblAddressRsdtDeleteRepository.save(entityEdit).getWkTblAddressRsdtDeleteId();
    }

    private void changeHistory(final Integer editId, final LeastUserDto userDto) {

        Optional<WkTblAddressRsdtDeleteEntity> optional = wkTblAddressRsdtDeleteRepository.findById(editId);
        if (optional.isEmpty()) {
            throw new EmptyResultDataAccessException("編集対象が検索できません(" + editId + ")", 1);
        }

        // 履歴にする
        WkTblAddressRsdtDeleteEntity entityHistory = optional.get();
        setTableDataHistoryUtil.practiceDelete(userDto, entityHistory);
        wkTblAddressRsdtDeleteRepository.save(entityHistory);

    }

}
