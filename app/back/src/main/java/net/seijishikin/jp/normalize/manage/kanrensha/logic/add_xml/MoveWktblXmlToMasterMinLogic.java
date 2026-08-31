package net.seijishikin.jp.normalize.manage.kanrensha.logic.add_xml;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblMasterAllByXmlEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblMasterAllByXmlRepository;

/**
 * XML登録ワークテーブルから各関連者ワークテーブルへ移管Logic
 */
@Component
public class MoveWktblXmlToMasterMinLogic {

    /** XMLから最小マスタ登録ワークテーブルRepository */
    @Autowired
    private WkTblMasterAllByXmlRepository wkTblMasterAllByXmlRepository;

    /** テーブル履歴セットRepository */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /** XML追加ワークテーブルからマスタ最小個人ワークテーブル変換Logic */
    @Autowired
    private ConvertWkTblXmlToMasterPersonLogic convertWkTblXmlToMasterPersonLogic;

    /** XML追加ワークテーブルからマスタ最小企業・団体ワークテーブル変換Logic */
    @Autowired
    private ConvertWkTblXmlToMasterKigyouDtLogic convertWkTblXmlToMasterKigyouDtLogic;

    /** XML追加ワークテーブルからマスタ最小政治団体ワークテーブル変換Logic */
    @Autowired
    private ConvertWkTblXmlToMasterSeijidantaiLogic convertWkTblXmlToMasterSeijidantaiLogic;

    /** 関連者区分未定 */
    private static final int KANRENSHA_MITEI = 0;

    /**
     * 処理を行う
     *
     * @param userDto ユーザ最小限Dto
     * @return 処理完了
     */
    public boolean practce(final LeastUserDto userDto) {

        for (WkTblMasterAllByXmlEntity baseEntity : wkTblMasterAllByXmlRepository
                .findByInsertUserCodeAndKanrenshaKbnNotAndIsLatest(userDto.getUserPersonCode(), KANRENSHA_MITEI,
                        SetTableDataHistoryUtil.INSERT_STATE)) {
            int newId = 0;
            switch (baseEntity.getKanrenshaKbn()) {
                case 1:
                    newId = convertWkTblXmlToMasterPersonLogic.practice(baseEntity, userDto);
                    break;
                case 2:
                    newId = convertWkTblXmlToMasterKigyouDtLogic.practice(baseEntity, userDto);
                    break;
                case 3: // SUPPRESS CHECKSTYLE MagicNumber
                    newId = convertWkTblXmlToMasterSeijidantaiLogic.practice(baseEntity, userDto);
                    break;
                default:
                    throw new IllegalArgumentException("Unexpected value: " + baseEntity.getKanrenshaKbn());
            }
            if (0 != newId) {
                baseEntity.setIsAffected(true);
                baseEntity.setIsFinish(true);
                baseEntity.setIsDisabled(true);
                baseEntity.setJudgeReason("最小マスタへ移動済;");
                setTableDataHistoryUtil.practiceDelete(userDto, baseEntity);
                wkTblMasterAllByXmlRepository.saveAndFlush(baseEntity);
            }
        }

        return true;
    }

}
