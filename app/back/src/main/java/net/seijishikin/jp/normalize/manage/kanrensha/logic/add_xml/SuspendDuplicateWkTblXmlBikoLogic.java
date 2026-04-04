package net.seijishikin.jp.normalize.manage.kanrensha.logic.add_xml;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.xml.XmlBikouUniquekeyDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblMasterAllByXmlEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblMasterAllByXmlRepository;
import net.seijishikin.jp.normalize.shuushi_doc.v05.constants.ShuushiDocV5YoushikiKbnConstants;


/**
 * 備考1項目の様式の重複データを処理対象外と中断させる
 */
@Component
public class SuspendDuplicateWkTblXmlBikoLogic {

    /** XMLから最小マスタ登録ワークテーブルRepository */
    @Autowired
    private WkTblMasterAllByXmlRepository wkTblMasterAllByXmlRepository;

    /** テーブル履歴設定Util */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /** 抽出様式区分リスト */
    private final List<Integer> listYoushikiKbn = new ArrayList<>();

    /**
     * コンストラクタ
     */
    public SuspendDuplicateWkTblXmlBikoLogic() {
        listYoushikiKbn.add((int)ShuushiDocV5YoushikiKbnConstants.KIKANSHI);
        listYoushikiKbn.add((int)ShuushiDocV5YoushikiKbnConstants.SHAKUNYUKIN);
        listYoushikiKbn.add((int)ShuushiDocV5YoushikiKbnConstants.KOUFUKIN);
    }

    /**
     * 処理を行う
     *
     * @param userDto ユーザ最小限Dto
     */
    public void practice(final LeastUserDto userDto) {

        Integer userCode = userDto.getUserPersonCode();

        List<XmlBikouUniquekeyDto> listKeyGroup = wkTblMasterAllByXmlRepository.findDuplicateUniqueKeyBikou(userCode);

        for (XmlBikouUniquekeyDto uniqueDto : listKeyGroup) {
            List<WkTblMasterAllByXmlEntity> list = wkTblMasterAllByXmlRepository
                    .findByBikouAndYoushikiKbnInAndInsertUserCodeOrderByWkTblMasterAllByXmlIdAsc(uniqueDto.getBikou(),
                            listYoushikiKbn, userCode);
            list.remove(0); // 1行だけは処理実行行として残す
            for (WkTblMasterAllByXmlEntity entity : list) {
                setTableDataHistoryUtil.practiceDelete(userDto, entity); // 削除
                entity.setIsLatest(SetTableDataHistoryUtil.DELETE_STATE);
                entity.setIsFinish(true);
                entity.setIsDisabled(true);
                entity.setJudgeReason("アップロードファイル内で重複しているデータです");
            }
            wkTblMasterAllByXmlRepository.saveAllAndFlush(list);
        }

    }

}
