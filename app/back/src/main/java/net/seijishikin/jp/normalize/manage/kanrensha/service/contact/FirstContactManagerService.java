package net.seijishikin.jp.normalize.manage.kanrensha.service.contact;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.contact.AddContactMessageCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.ContactManagerEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.ContactManagerRepository;

/**
 * 新規問い合わせ登録Service
 */
@Service
public class FirstContactManagerService {

    /** 運営者連絡Repository */
    @Autowired
    private ContactManagerRepository contactManagerRepository;

    /** テーブル履歴設定Util */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /**
     * 処理を行う
     * 
     * @param firstDateTime 初期登録日時
     * @param capsuleDto    問い合わせ内容設定Dto
     * @return 登録Id
     */
    @Transactional
    public Integer practice(final LocalDateTime firstDateTime, final AddContactMessageCapsuleDto capsuleDto) {

        ContactManagerEntity entity = new ContactManagerEntity();

        LeastUserDto userDto = capsuleDto.getUserDto();

        // 初回登録情報は常に複写されるようにする
        entity.setFirstTimestamp(firstDateTime);
        entity.setInquireUserId(userDto.getUserPersonId());
        entity.setInquireUserCode(userDto.getUserPersonCode());
        entity.setInquireUserName(userDto.getUserPersonName());
        entity.setInquireTitle(capsuleDto.getInquireTitle());
        entity.setInquireContent(capsuleDto.getInquireContent());

        // 問い合わせ終了情報は未設定
        // entity.setIsColsed(false);
        // entity.setCloseTimestamp(null); // 初期設定値

        // コード設定
        Integer code = 1;
        Optional<ContactManagerEntity> optional = contactManagerRepository.findFirstByOrderByContactManagerCodeDesc();
        if (!optional.isEmpty()) {
            code += optional.get().getContactManagerCode();
        }
        entity.setContactManagerCode(code);

        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setContactManagerId(0); // auto increment明記

        // タスク計画・メール送信はControllerで

        return contactManagerRepository.save(entity).getContactManagerId();
    }
}
