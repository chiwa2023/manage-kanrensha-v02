package net.seijishikin.jp.normalize.manage.kanrensha.service.postal;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.postal.SavePostalCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.AddressPostalRepository;

/**
 * 郵便番号削除Service
 */
@Service
public class DeletePostalCodeService {

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

        Integer deleteId = capsuleDto.getAddressPostalEntity().getAddressPostalId();
        Optional<AddressPostalEntity> optional = addressPostalRepository.findById(deleteId);
        if (optional.isEmpty()) {
            throw new EmptyResultDataAccessException("編集対象が検索できません(" + deleteId + ")", 1);
        }

        AddressPostalEntity entity = optional.get();
        setTableDataHistoryUtil.practiceDelete(capsuleDto.getUserDto(), entity);

        return addressPostalRepository.save(entity).getAddressPostalId();

    }

}
