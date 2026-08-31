package net.seijishikin.jp.normalize.manage.kanrensha.logic.add_xml;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.kigyou_dt.add_min.KanrenshaKigyouDtAddMiniCsvProcessor;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaKigyouDtAddMinEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblMasterAllByXmlEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaKigyouDtAddMinRepository;


/**
 * XMLから登録ワークテーブルから企業最小登録ワークテーブルに変換する
 */
@Component
public class ConvertWkTblXmlToMasterKigyouDtLogic {

    /** 仕様チェックProcessor(企業最小) */
    @Autowired
    private KanrenshaKigyouDtAddMiniCsvProcessor kanrenshaKigyouDtAddMiniCsvProcessor;

    /** 企業マスタ最小登録ワークテーブルRepository */
    @Autowired
    private WkTblKanrenshaKigyouDtAddMinRepository wkTblKanrenshaKigyouDtAddMinRepository;

    /** テーブル履歴セットRepository */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /**
     * 処理を行う
     *
     * @param allByXmlEntity XMLから登録ワークテーブルEntity
     * @param userdto        ユーザ最小限Dto
     * @return 処理後Id
     */
    public int practice(final WkTblMasterAllByXmlEntity allByXmlEntity, final LeastUserDto userdto) {

        WkTblKanrenshaKigyouDtAddMinEntity minEntity = new WkTblKanrenshaKigyouDtAddMinEntity();

        minEntity.setKanrenshaName(allByXmlEntity.getKanrenshaName());
        minEntity.setAllAddress(allByXmlEntity.getAllAddress());
        minEntity.setKigyouDtDelegate(allByXmlEntity.getOrgDelegate());
        minEntity.setHoujinNo(allByXmlEntity.getHoujinNo());

        // ユーザさんが変更しないと決断したらデータ整合チェックはしないで意図をそのまま通す
        final String notUseText = "使用しないに変更;";
        if (!notUseText.equals(minEntity.getJudgeReason())) {
            minEntity = kanrenshaKigyouDtAddMiniCsvProcessor.check(minEntity);
        }

        setTableDataHistoryUtil.practiceInsert(userdto, minEntity);

        // コードを取得
        Integer code = 1;
        Optional<WkTblKanrenshaKigyouDtAddMinEntity> optional = wkTblKanrenshaKigyouDtAddMinRepository
                .findFirstByOrderByWkTblKanrenshaKigyouDtAddMinCodeDesc();
        if (!optional.isEmpty()) {
            code += optional.get().getWkTblKanrenshaKigyouDtAddMinCode();
        }
        minEntity.setWkTblKanrenshaKigyouDtAddMinCode(code);

        return wkTblKanrenshaKigyouDtAddMinRepository.save(minEntity).getWkTblKanrenshaKigyouDtAddMinId();
    }
}
