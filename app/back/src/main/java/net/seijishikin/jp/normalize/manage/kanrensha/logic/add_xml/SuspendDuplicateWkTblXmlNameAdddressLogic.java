package net.seijishikin.jp.normalize.manage.kanrensha.logic.add_xml;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.xml.XmlNameAddressUniquekeyDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblMasterAllByXmlEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblMasterAllByXmlRepository;
import net.seijishikin.jp.normalize.shuushi_doc.v05.constants.ShuushiDocV5YoushikiKbnConstants;


/**
 * 名前と住所2項目の様式の重複データを処理対象外と中断させる
 */
@Component
public class SuspendDuplicateWkTblXmlNameAdddressLogic {

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
    public SuspendDuplicateWkTblXmlNameAdddressLogic() {
        listYoushikiKbn.add((int)ShuushiDocV5YoushikiKbnConstants.KEIJO_KEIHI);
        listYoushikiKbn.add((int)ShuushiDocV5YoushikiKbnConstants.SEIJIKATSUDOUHI);
        listYoushikiKbn.add((int)ShuushiDocV5YoushikiKbnConstants.KOUFUKIN_SHISHUTSU); 
        listYoushikiKbn.add((int)ShuushiDocV5YoushikiKbnConstants.KOUFUKIN);
    }

    /**
     * 処理を行う
     *
     * @param userDto ユーザ最小限Dto
     */
    public void practice(final LeastUserDto userDto) {

        Integer userCode = userDto.getUserPersonCode();

        List<XmlNameAddressUniquekeyDto> listKeyGroup = wkTblMasterAllByXmlRepository
                .findDuplicateUniqueKeyNameAddress(userCode);

        for (XmlNameAddressUniquekeyDto uniqueDto : listKeyGroup) {
            List<WkTblMasterAllByXmlEntity> list = wkTblMasterAllByXmlRepository
                    .findByInputSrcNameAndInputSrcAddressAndYoushikiKbnInAndInsertUserCodeOrderByWkTblMasterAllByXmlIdAsc(
                            uniqueDto.getInputSrcName(), uniqueDto.getInputSrcAddress(), listYoushikiKbn, userCode);
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
