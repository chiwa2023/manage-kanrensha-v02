package net.seijishikin.jp.normalize.manage.kanrensha.service.address_rsdt;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.address_rsdt.EditAddressRsdtCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressRsdtTemplateEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.lgcode.CreateSqlAddressRsdtHistoryLogic;

/**
 * 住居削除Service
 */
@Service
public class DeleteAddressRsdtService {

    /** 住居履歴SQL作成Logic */
    @Autowired
    private CreateSqlAddressRsdtHistoryLogic createSqlAddressRsdtHistoryLogic;

    /** 住居履歴SQL作成Logic */
    @Autowired
    private EntityManager entityManager;

    /**
     * 処理を行う
     * 
     * @param capsuleDto 処理対象Dto
     * @return 処理件数
     */
    @Transactional
    public Integer practice(final EditAddressRsdtCapsuleDto capsuleDto) {

        AddressRsdtTemplateEntity entityEdit = capsuleDto.getEditEntity();
        LocalDateTime now = LocalDateTime.now();
        String timestampString = now.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        String sqlHistory = createSqlAddressRsdtHistoryLogic.practice(capsuleDto.getUserDto(), entityEdit.getLgCode(),
                entityEdit.getAddressRsdtId(), timestampString);
        Query queryHistory = entityManager.createNativeQuery(sqlHistory);
        queryHistory.executeUpdate();

        return queryHistory.executeUpdate();
    }
}
