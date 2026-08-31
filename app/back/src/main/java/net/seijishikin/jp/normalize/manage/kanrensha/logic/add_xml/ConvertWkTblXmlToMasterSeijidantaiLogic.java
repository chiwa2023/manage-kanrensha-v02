package net.seijishikin.jp.normalize.manage.kanrensha.logic.add_xml;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.seijidantai.add_min.KanrenshaSeijidantaiAddMiniCsvProcessor;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaSeijidantaiAddMinEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblMasterAllByXmlEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaSeijidantaiAddMinRepository;


/**
 * XMLから登録ワークテーブルから政治団体最小登録ワークテーブルに変換する
 */
@Component
public class ConvertWkTblXmlToMasterSeijidantaiLogic {

    /** 仕様チェックProcessor(政治団体最小) */
    @Autowired
    private KanrenshaSeijidantaiAddMiniCsvProcessor kanrenshaSeijidantaiAddMiniCsvProcessor;

    /** 政治団体マスタ最小登録ワークテーブルRepository */
    @Autowired
    private WkTblKanrenshaSeijidantaiAddMinRepository wkTblKanrenshaSeijidantaiAddMinRepository;

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

        WkTblKanrenshaSeijidantaiAddMinEntity minEntity = new WkTblKanrenshaSeijidantaiAddMinEntity();

        minEntity.setKanrenshaName(allByXmlEntity.getKanrenshaName());
        minEntity.setAllAddress(allByXmlEntity.getAllAddress());
        minEntity.setSeijidantaiDelegate(allByXmlEntity.getOrgDelegate());
        minEntity.setDantaiKbn(allByXmlEntity.getDantaiKbn());

        // ユーザさんが変更しないと決断したらデータ整合チェックはしないで意図をそのまま通す
        final String notUseText = "使用しないに変更;";
        if (!notUseText.equals(minEntity.getJudgeReason())) {
            minEntity = kanrenshaSeijidantaiAddMiniCsvProcessor.check(minEntity);
        }

        setTableDataHistoryUtil.practiceInsert(userdto, minEntity);

        // コードを取得
        Integer code = 1;
        Optional<WkTblKanrenshaSeijidantaiAddMinEntity> optional = wkTblKanrenshaSeijidantaiAddMinRepository
                .findFirstByOrderByWkTblKanrenshaSeijidantaiAddMinCodeDesc();
        if (!optional.isEmpty()) {
            code += optional.get().getWkTblKanrenshaSeijidantaiAddMinCode();
        }
        minEntity.setWkTblKanrenshaSeijidantaiAddMinCode(code);

        return wkTblKanrenshaSeijidantaiAddMinRepository.save(minEntity).getWkTblKanrenshaSeijidantaiAddMinId();
    }

}
