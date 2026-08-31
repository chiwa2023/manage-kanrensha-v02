package net.seijishikin.jp.normalize.manage.kanrensha.service.address_rsdt;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.address_rsdt.EditWktblRsdtChangeCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblAddressRsdtChangeEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblAddressRsdtChangeRepository;

/**
 * 住居差分ワークテーブル変更編集Service
 */
@Service
public class EditWkTblRsdtChangeService {

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
     * @return 処理件数
     */
    @Transactional
    public Integer practice(final EditWktblRsdtChangeCapsuleDto capsuleDto) {

        WkTblAddressRsdtChangeEntity entityEdit = capsuleDto.getEditEntity();
        Integer editId = entityEdit.getWkTblAddressRsdtChangeId();

        LeastUserDto userDto = capsuleDto.getUserDto();
        // 編集前データを履歴にする(新規の場合は履歴にする作業が不要)
        if (0 != editId) {
            this.changeHistory(editId, userDto);
        }

        // 編集後データを最新にする
        setTableDataHistoryUtil.practiceInsert(userDto, entityEdit);
        entityEdit.setWkTblAddressRsdtChangeId(0); // auto increment明記

        return wkTblAddressRsdtChangeRepository.save(entityEdit).getWkTblAddressRsdtChangeId();
    }

    private void changeHistory(final Integer editId, final LeastUserDto userDto) {

        Optional<WkTblAddressRsdtChangeEntity> optional = wkTblAddressRsdtChangeRepository.findById(editId);
        if (optional.isEmpty()) {
            throw new EmptyResultDataAccessException("編集対象が検索できません(" + editId + ")", 1);
        }

        // 履歴にする
        WkTblAddressRsdtChangeEntity entityHistory = optional.get();
        setTableDataHistoryUtil.practiceDelete(userDto, entityHistory);
        wkTblAddressRsdtChangeRepository.save(entityHistory);

    }

}
