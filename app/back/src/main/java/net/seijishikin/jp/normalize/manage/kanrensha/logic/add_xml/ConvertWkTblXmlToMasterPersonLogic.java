package net.seijishikin.jp.normalize.manage.kanrensha.logic.add_xml;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.person.add_min.KanrenshaPersonAddMiniCsvProcessor;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaPersonAddMinEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblMasterAllByXmlEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaPersonAddMinRepository;


/**
 * XMLから登録ワークテーブルから個人最小登録ワークテーブルに変換する
 */
@Component
public class ConvertWkTblXmlToMasterPersonLogic {

    /** 仕様チェックProcessor(個人最小) */
    @Autowired
    private KanrenshaPersonAddMiniCsvProcessor kanrenshaPersonAddMiniCsvProcessor;

    /** 個人マスタ最小登録ワークテーブルRepository */
    @Autowired
    private WkTblKanrenshaPersonAddMinRepository wkTblKanrenshaPersonAddMinRepository;

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

        WkTblKanrenshaPersonAddMinEntity minEntity = new WkTblKanrenshaPersonAddMinEntity();

        minEntity.setKanrenshaName(allByXmlEntity.getKanrenshaName());
        minEntity.setAllAddress(allByXmlEntity.getAllAddress());
        minEntity.setPersonShokugyou(allByXmlEntity.getPersonShokugyou());

        // ユーザさんが変更しないと決断したらデータ整合チェックはしないで意図をそのまま通す
        final String notUseText = "使用しないに変更;";
        if (!notUseText.equals(minEntity.getJudgeReason())) {
            minEntity = kanrenshaPersonAddMiniCsvProcessor.check(minEntity);
        }
        setTableDataHistoryUtil.practiceInsert(userdto, minEntity);

        // コードを取得
        Integer code = 1;
        Optional<WkTblKanrenshaPersonAddMinEntity> optional = wkTblKanrenshaPersonAddMinRepository
                .findFirstByOrderByWkTblKanrenshaPersonAddMinCodeDesc();
        if (!optional.isEmpty()) {
            code += optional.get().getWkTblKanrenshaPersonAddMinCode();
        }
        minEntity.setWkTblKanrenshaPersonAddMinCode(code);

        return wkTblKanrenshaPersonAddMinRepository.save(minEntity).getWkTblKanrenshaPersonAddMinId();
    }

}
