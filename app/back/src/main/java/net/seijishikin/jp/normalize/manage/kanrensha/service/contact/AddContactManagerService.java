package net.seijishikin.jp.normalize.manage.kanrensha.service.contact;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.contact.AddContactMessageCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.ContactManagerEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.ContactManagerRepository;

/**
 * 運営者問い合わせメッセージ追加Service
 */
@Service
public class AddContactManagerService {

    /** 運営者連絡Repository */
    @Autowired
    private ContactManagerRepository contactManagerRepository;

    /** テーブル履歴設定Util */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /**
     * 処理を行う
     * 
     * @param capsuleDto 追加メッセージDto
     * @return 登録Id
     */
    @Transactional
    public ContactManagerEntity practice(final AddContactMessageCapsuleDto capsuleDto) {

        Optional<ContactManagerEntity> optional = contactManagerRepository
                .findFirstByContactManagerCodeAndIsLatestTrueOrderByContactManagerIdDesc(
                        capsuleDto.getContactManagerCode());
        if (optional.isEmpty()) {
            throw new EmptyResultDataAccessException("この最新データを見つけることができませんでした", 1);
        }

        ContactManagerEntity oldEntity = optional.get();

        ContactManagerEntity newEntity = new ContactManagerEntity();
        BeanUtils.copyProperties(oldEntity, newEntity);

        LeastUserDto userDto = capsuleDto.getUserDto();
        setTableDataHistoryUtil.practiceDelete(userDto, oldEntity);

        newEntity.setInquireContent(capsuleDto.getInquireContent());

        // 終了フラグが立っていれば終了を設定する
        if (capsuleDto.getIsColsed()) {
            newEntity.setIsClosed(true);
            newEntity.setCloseTimestamp(LocalDateTime.now());
        }

        setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
        newEntity.setContactManagerId(0); // auto increment明記

        contactManagerRepository.save(oldEntity);

        return contactManagerRepository.save(newEntity);
    }

}
