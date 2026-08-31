package net.seijishikin.jp.normalize.manage.kanrensha.service.postal;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.postal.SavePostalCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.AddressPostalRepository;

/**
 * 郵便番号編集Service
 */
@Service
public class EditPostalCodeService {

    /** 郵便番号正規データRepository */
    @Autowired
    private AddressPostalRepository addressPostalRepository;

    /** テーブル履歴設定Util */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /**
     * 処理を行う
     * 
     * @param capsuleDto 編集内容Dto
     * @return 更新Id
     */
    @Transactional
    public Integer practice(final SavePostalCapsuleDto capsuleDto) {

        AddressPostalEntity entityEdit = capsuleDto.getAddressPostalEntity();
        Integer editId = entityEdit.getAddressPostalId();

        LeastUserDto userDto = capsuleDto.getUserDto();
        // 編集前データを履歴にする(新規の場合は履歴にする作業が不要)
        if (0 != editId) {
            this.changeHistory(editId, userDto);
        }

        // 編集後データを最新にする
        setTableDataHistoryUtil.practiceInsert(userDto, entityEdit);
        entityEdit.setAddressPostalId(0); // auto increment明記

        return addressPostalRepository.save(entityEdit).getAddressPostalId();

    }

    private void changeHistory(final Integer editId, final LeastUserDto userDto) {

        Optional<AddressPostalEntity> optional = addressPostalRepository.findById(editId);
        if (optional.isEmpty()) {
            throw new EmptyResultDataAccessException("編集対象が検索できません(" + editId + ")", 1);
        }

        // 履歴にする
        AddressPostalEntity entityHistory = optional.get();
        setTableDataHistoryUtil.practiceDelete(userDto, entityHistory);
        addressPostalRepository.save(entityHistory);

    }

}
