package net.seijishikin.jp.normalize.manage.kanrensha.service.postal;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressRsdtTemplateEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.lgcode.CreateSqlAddressRsdtHistoryLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.lgcode.CreateSqlInsertAddressRsdtLogic;

/**
 * 郵便番号変更Service
 */
@Service
public class MovePostalcodeRsdtService {

    /** 住居挿入SQl作成Logic */
    @Autowired
    private CreateSqlInsertAddressRsdtLogic createSqlInsertAddressRsdtLogic;

    /** テーブル履歴設定Util */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /** 住居履歴SQl作成Logic */
    @Autowired
    private CreateSqlAddressRsdtHistoryLogic createSqlAddressRsdtHistoryLogic;

    /**
     * 処理を凍なう
     * 
     * @param entityManager EntityManager
     * @param userDto       ユーザ最小限
     * @param lgCode        地方自治体コード
     * @param postalOld1    郵便番号1旧
     * @param postalOld2    郵便番号2旧
     * @param postalNew1    郵便番号1新
     * @param postalNew2    郵便番号2新
     */
    @SuppressWarnings("unchecked")
    public void practice(final EntityManager entityManager, final LeastUserDto userDto, // NOPMD CreanerAPI
            final String lgCode, final String postalOld1, final String postalOld2, final String postalNew1,
            final String postalNew2) {
        // ここではトランザクションを張りたくない

        LocalDateTime now = LocalDateTime.now();
        String timestampString = now.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        String sqlSelect = "SELECT * FROM address_rsdt_" + lgCode + "  WHERE postalcode1 = '" + postalOld1
                + "' AND postalcode2 = '" + postalOld2 + "' AND is_latest = 1";
        Query querySelect = entityManager.createNativeQuery(sqlSelect, AddressRsdtTemplateEntity.class);

        List<AddressRsdtTemplateEntity> list = (List<AddressRsdtTemplateEntity>) querySelect.getResultList();

        for (AddressRsdtTemplateEntity entity : list) {

            setTableDataHistoryUtil.practiceDelete(userDto, entity);
            entityManager.detach(entity); // HierarteによるEntityの監視を切り飛ばす

            Query queryHistory = entityManager.createNativeQuery(createSqlAddressRsdtHistoryLogic.practice(userDto,
                    lgCode, entity.getAddressRsdtId(), timestampString));
            queryHistory.executeUpdate();

            Query queryLatest = entityManager.createNativeQuery(createSqlInsertAddressRsdtLogic.practice(userDto,
                    this.createNewPostalEntity(entity, userDto, postalNew1, postalNew2), timestampString));
            queryLatest.executeUpdate();
        }

    }

    private AddressRsdtTemplateEntity createNewPostalEntity(final AddressRsdtTemplateEntity srcEntity,
            final LeastUserDto userDto, final String postalNew1, final String postalNew2) {

        AddressRsdtTemplateEntity entity = new AddressRsdtTemplateEntity();
        BeanUtils.copyProperties(srcEntity, entity);
        entity.setPostalcode1(postalNew1);
        entity.setPostalcode2(postalNew2);

        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setAddressRsdtId(0); // auto increment明記

        return entity;
    }
}
