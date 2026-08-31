package net.seijishikin.jp.normalize.manage.kanrensha.service.postal;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalIrregularEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressRsdtTemplateEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.lgcode.CreateSqlInsertAddressRsdtLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.AddressPostalIrregularRepository;

/**
 * 不規則の建物に紐づく住居を作成する
 */
@Service
public class MakeBuildingRsdtByPostalIrregularService {

    /** entityManager */
    @Autowired
    private EntityManager entityManager;

    /** 郵便番号不規則データRepository */
    @Autowired
    private AddressPostalIrregularRepository addressPostalIrregularRepository;

    /** 住居テーブル挿入SQL */
    @Autowired
    private CreateSqlInsertAddressRsdtLogic createSqlInsertAddressRsdtLogic;

    /** 空文字 */
    private static final String BLANK = "";

    /** 住居2桁数 */
    private static final int RSDT2_DIGIT = 7;

    /** ゼロ文字 */
    private static final String CHAR_ZERO = "0";

    /**
     * 処理を行う
     * 
     * @param addressName 建物名(地階・階層不明でOK)
     * @param userDto     ユーザ最小限Dto
     */
    @Transactional
    public int practice(final String addressName, final LeastUserDto userDto) {

        entityManager.joinTransaction();

        // 先ほど挿入した紐づく郵便番号住所を再取得
        List<AddressPostalIrregularEntity> listDetail = addressPostalIrregularRepository
                .findByAddressNameAndIsLatestTrue(addressName);

        AddressPostalIrregularEntity templateEntity = listDetail.getFirst();

        String sqlBase = "SELECT * FROM address_rsdt_" + templateEntity.getLgCode() + " WHERE address_block = '"
                + templateEntity.getAddressPostal() + templateEntity.getAddressBlock()
                + "' AND is_latest = 1 AND address_building = '' LIMIT 1";
        Query queryBase = entityManager.createNativeQuery(sqlBase, AddressRsdtTemplateEntity.class);
        AddressRsdtTemplateEntity baseEntity = //
                (AddressRsdtTemplateEntity) queryBase.getSingleResult(); // NOPMD LawDemeter

        LocalDateTime now = LocalDateTime.now();
        String timestampString = now.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        int counter = 0;
        int size = listDetail.size();
        int maxDigit = String.valueOf(size).length();
        for (AddressPostalIrregularEntity irregularEntity : listDetail) {

            // SQl文を作成して指定住居ファイルに挿入
            String sql = createSqlInsertAddressRsdtLogic.practice(userDto,
                    this.convertRsdtBuikdingEntity(baseEntity, irregularEntity, counter, maxDigit), timestampString);
            Query query = entityManager.createNativeQuery(sql);
            query.executeUpdate();
            counter++;
        }

        entityManager.flush();

        // 何も返さないのはアレなので理論上の更新行数
        return size;
    }

    private AddressRsdtTemplateEntity convertRsdtBuikdingEntity(final AddressRsdtTemplateEntity baseEntity,
            final AddressPostalIrregularEntity irregularEntity, final int counter, final int maxDigit) {
        AddressRsdtTemplateEntity entity = new AddressRsdtTemplateEntity();
        BeanUtils.copyProperties(baseEntity, entity);

        // 郵便番号
        entity.setPostalcode1(irregularEntity.getPostalcode1());
        entity.setPostalcode2(irregularEntity.getPostalcode2());

        // 建物名
        entity.setAddressBuilding(this.createBuildingName(irregularEntity.getAddressOrg()));

        // 住居2ファイル7桁
        entity.setRsdt2Id(this.createRsdt2(counter, maxDigit));
        entity.setAddressRsdtId(0);

        return entity;
    }

    private String createBuildingName(final String orgName) {
        // 建物名+ 全角スペース + 階数表示
        String dataSpace = orgName.replace("（", "　（");
        return dataSpace.replaceAll("（", BLANK).replaceAll("）", BLANK).replaceAll("・階層不明", BLANK);
    }

    private String createRsdt2(final int counter, final int maxDigit) {

        int init = String.valueOf(counter).length();
        StringBuilder builder = new StringBuilder(String.valueOf(counter));
        // 最上階が123階のとき3桁で前ゼロ埋め(この地震大国で100階とかないと思うけど)
        for (int index = init; index < maxDigit; index++) {
            builder.insert(0, CHAR_ZERO);
        }

        // 最大7桁で残りの桁を後ろゼロ追加
        for (int index = maxDigit; index < RSDT2_DIGIT; index++) {
            builder.append(CHAR_ZERO);
        }

        return builder.toString();
    }

}
