package net.seijishikin.jp.normalize.manage.kanrensha.service.postal;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.postal.SaveWktblPostalCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblPostalEditEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblPostalEditRepository;

/**
 * 郵便番号差分ワークテーブル更新Service
 */
@Service
public class UpdateWkTblPostalcodeService {

    /** ワークテーブル郵便番号差分Repository */
    @Autowired
    private WkTblPostalEditRepository wkTblPostalEditRepository;

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
    public Integer practice(final SaveWktblPostalCapsuleDto capsuleDto) {

        WkTblPostalEditEntity entityEdit = capsuleDto.getEditEntity();
        Integer editId = entityEdit.getWkTblPostalEditId();

        LeastUserDto userDto = capsuleDto.getUserDto();
        // 編集前データを履歴にする(新規の場合は履歴にする作業が不要)
        if (0 != editId) {
            this.changeHistory(editId, userDto);
        }

        // 編集後データを削除でない場合のみ最新にする
        if (!capsuleDto.getIsDelete()) {
            setTableDataHistoryUtil.practiceInsert(userDto, entityEdit);
            entityEdit.setWkTblPostalEditId(0); // auto increment明記
        }

        return wkTblPostalEditRepository.save(entityEdit).getWkTblPostalEditId();

    }

    private void changeHistory(final Integer editId, final LeastUserDto userDto) {

        Optional<WkTblPostalEditEntity> optional = wkTblPostalEditRepository.findById(editId);
        if (optional.isEmpty()) {
            throw new EmptyResultDataAccessException("編集対象が検索できません(" + editId + ")", 1);
        }

        // 履歴にする
        WkTblPostalEditEntity entityHistory = optional.get();
        setTableDataHistoryUtil.practiceDelete(userDto, entityHistory);
        wkTblPostalEditRepository.save(entityHistory);
    }

}
