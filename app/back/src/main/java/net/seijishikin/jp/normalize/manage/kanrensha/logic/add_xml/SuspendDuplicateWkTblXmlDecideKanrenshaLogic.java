package net.seijishikin.jp.normalize.manage.kanrensha.logic.add_xml;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.xml.XmlKanrenshaUniquekeyDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblMasterAllByXmlEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblMasterAllByXmlRepository;
import net.seijishikin.jp.normalize.shuushi_doc.v05.constants.ShuushiDocV5YoushikiKbnConstants;

/**
 * 名前、住所、職業(団体代表者)3項目の様式の重複データを処理対象外と中断させる
 */
@Component
public class SuspendDuplicateWkTblXmlDecideKanrenshaLogic {

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
    public SuspendDuplicateWkTblXmlDecideKanrenshaLogic() {
        listYoushikiKbn.add((int) ShuushiDocV5YoushikiKbnConstants.DONATE);
        listYoushikiKbn.add((int) ShuushiDocV5YoushikiKbnConstants.DONATE_ASSEN);
        listYoushikiKbn.add((int) ShuushiDocV5YoushikiKbnConstants.PARTY);
        listYoushikiKbn.add((int) ShuushiDocV5YoushikiKbnConstants.PARTY_ASSEN);
    }

    /**
     * 処理を行う
     *
     * @param userDto ユーザ最小限Dto
     */
    public void practice(final LeastUserDto userDto) {

        Integer userCode = userDto.getUserPersonCode();

        List<XmlKanrenshaUniquekeyDto> listKeyGroup = wkTblMasterAllByXmlRepository
                .findDuplicateUniqueKeyDecideKanrensha(userCode);

        for (XmlKanrenshaUniquekeyDto uniqueDto : listKeyGroup) {
            List<WkTblMasterAllByXmlEntity> list = wkTblMasterAllByXmlRepository
                    .findByInputSrcNameAndInputSrcAddressAndInputSrcKeyAndYoushikiKbnInAndInsertUserCodeOrderByWkTblMasterAllByXmlIdAsc(
                            uniqueDto.getInputSrcName(), uniqueDto.getInputSrcAddress(), uniqueDto.getInputSrcKey(),
                            listYoushikiKbn, userCode);
            list.remove(0); // 1行だけは処理実行行として残す
            for (WkTblMasterAllByXmlEntity entity : list) {
                setTableDataHistoryUtil.practiceDelete(userDto, entity); // 削除
                entity.setIsFinish(true);
                entity.setIsDisabled(true);
                entity.setJudgeReason("アップロードファイル内で重複しているデータです");
            }
            wkTblMasterAllByXmlRepository.saveAllAndFlush(list);
        }

    }

}
