package net.seijishikin.jp.normalize.manage.kanrensha.logic.postal;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressRsdtBaseEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblPostalCommonEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.AddressPostalRepository;

/**
 * （）住所が単一であるデータから修正郵便番号データを抽出する
 */
@Component
public class SelectSingleAddressLogic {

    /** EntityManager */
    @Autowired
    private EntityManager entityManager;

    /** 郵便番号Repository */
    @Autowired
    private AddressPostalRepository addressPostalRepository;

    /** テーブル履歴設定Util */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /**
     * 処理を行う
     *
     * @param worksEntity 郵便番号作業Entity
     * @return 郵便番号リスト
     */
    @SuppressWarnings("unchecked")
    public List<AddressPostalEntity> practice(final WkTblPostalCommonEntity worksEntity,final LeastUserDto userDto) {
        String baseAddress = worksEntity.getAddressName();
        String org = worksEntity.getAddressOrg();
        int posStart = org.indexOf("（");
        int posEnd = org.indexOf("）");
        String place = org.substring(posStart + 1, posEnd);

        // 共通部分(address_name)で始まり、（）内の語句までが一致するデータをピックアップ
        // ()までとカッコ内の地名が離れていることがあるので単純にかっこを除去するだけでは不可
        String sql = "SELECT * FROM address_rsdt_" + worksEntity.getLgCode() + " WHERE address_block LIKE '"
                + baseAddress + "%' AND address_block LIKE '%" + place + "%'";

        Query query = entityManager.createNativeQuery(sql, AddressRsdtBaseEntity.class);

        List<AddressRsdtBaseEntity> listRsdt = (List<AddressRsdtBaseEntity>) query.getResultList();

        List<AddressPostalEntity> list = new ArrayList<>();
        if (listRsdt.isEmpty()) {
            // 住居テーブルにデータが存在しないときは、本来起きないことが起きているとして空リスト
            return list;
        } else {
            List<String> listNameAddress = new ArrayList<>();
            // 条件に合致する住所を抽出
            for (AddressRsdtBaseEntity entity : listRsdt) {
                String data = entity.getAddressBlock();
                String name = data.substring(0, data.indexOf(place) + place.length());

                // 共通部分を展開
                if (!listNameAddress.contains(name)) {
                    listNameAddress.add(name);
                }
            }

            // 該当する郵便番号(実際は1件のはず)を抽出する
            List<AddressPostalEntity> listPostal = addressPostalRepository
                    .findByPostalcode1AndPostalcode2OrderByAddressNameAsc(worksEntity.getPostalcode1(),
                            worksEntity.getPostalcode2());

            List<AddressPostalEntity> listAnswer = new ArrayList<>();

            for (AddressPostalEntity entity : listPostal) {
                for (String address : listNameAddress) {
                    AddressPostalEntity entityChange = new AddressPostalEntity(); // NOPMD
                    BeanUtils.copyProperties(entity, entityChange);
                    entityChange.setAddressName(address);
                    entityChange.setIsGyoseikuData(true);
                    setTableDataHistoryUtil.practiceInsert(userDto, worksEntity);
                    listAnswer.add(entityChange);
                }
            }
            return listAnswer;
        }
    }

}
