package net.seijishikin.jp.normalize.manage.kanrensha.logic.postal;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressRsdtBaseEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblPostalCommonEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.address.registory.WriteLogAddressFormatLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.AddressPostalRepository;

/**
 * 住居テーブルに(その他)データが存在するか確認する
 */
@Component
public class CheckExistPostalCodeByOtherLogic {

    /** EntityManager */
    @Autowired
    private EntityManager entityManager;

    /** 郵便番号Repository */
    @Autowired
    private AddressPostalRepository addressPostalRepository;

    /** Logger */
    @Autowired
    private WriteLogAddressFormatLogic writeLogAddressFormatLogic;

    /**
     * 処理を行う
     *
     * @param worksEntity 郵便番号作業Entity
     * @return 郵便番号リスト
     */
    public List<AddressPostalEntity> practice(final WkTblPostalCommonEntity worksEntity) {

        // (その他)の前までの住所が共通である住所が存在すればOK
        String sql = "SELECT * FROM address_rsdt_" + worksEntity.getLgCode() + " WHERE address_block LIKE '"
                + worksEntity.getAddressName() + "%' LIMIT 1";
        Query query = entityManager.createNativeQuery(sql, AddressRsdtBaseEntity.class);

        List<AddressPostalEntity> list = new ArrayList<>();
        if (query.getResultList().isEmpty()) {
            // 住居テーブルにデータが存在しないときは、本来起きないことが起きているとして空リスト
            writeLogAddressFormatLogic.practice(WriteLogAddressFormatLogic.ERROR, "その他変換で郵便番号ファイルにあるがアドレスレジストリにありません",
                    worksEntity.getAddressName(),worksEntity.getLgCode());
            return list;
        } else {
            return addressPostalRepository.findByPostalcode1AndPostalcode2OrderByAddressNameAsc(
                    worksEntity.getPostalcode1(), worksEntity.getPostalcode2());
        }
    }
}
