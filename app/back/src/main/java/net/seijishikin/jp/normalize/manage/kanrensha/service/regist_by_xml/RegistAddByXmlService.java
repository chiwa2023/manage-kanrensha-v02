package net.seijishikin.jp.normalize.manage.kanrensha.service.regist_by_xml;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblMasterAllByXmlEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.add_xml.ConvertWkTblXmlToMasterKigyouDtLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.add_xml.ConvertWkTblXmlToMasterPersonLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.add_xml.ConvertWkTblXmlToMasterSeijidantaiLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblMasterAllByXmlRepository;

/**
 * XMLからマスタ登録ワークテーブル編集Service
 */
@Service
public class RegistAddByXmlService {

    /** ワークテーブルXMLマスタ登録Repository */
    @Autowired
    private WkTblMasterAllByXmlRepository wkTblMasterAllByXmlRepository;

    /** テーブル履歴セットRepository */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /** XMLワークテーブル個人最小変換Logic */
    @Autowired
    private ConvertWkTblXmlToMasterPersonLogic convertWkTblXmlToMasterPersonLogic;

    /** XMLワークテーブル企業最小変換Logic */
    @Autowired
    private ConvertWkTblXmlToMasterKigyouDtLogic convertWkTblXmlToMasterKigyouDtLogic;

    /** XMLワークテーブル政治団体最小変換Logic */
    @Autowired
    private ConvertWkTblXmlToMasterSeijidantaiLogic convertWkTblXmlToMasterSeijidantaiLogic;

    /**
     * 処理を行う
     *
     * @param entityInput 編集対象Entity
     * @param userDto     ユーザ最小限Dto
     * @return 新たなId
     */
    @Transactional
    public WkTblMasterAllByXmlEntity practice(final WkTblMasterAllByXmlEntity entityInput,
            final LeastUserDto userDto) {

        Optional<WkTblMasterAllByXmlEntity> optional = wkTblMasterAllByXmlRepository
                .findById(entityInput.getWkTblMasterAllByXmlId());

        // 万が一元データが探せない場合は処理中断
        if (optional.isEmpty()) {
            return new WkTblMasterAllByXmlEntity();
        }

        // 編集データに過去との違いがない場合はこのデータだけをスキップ
        WkTblMasterAllByXmlEntity entitySrc = optional.get();
        if (this.isNotChangeValue(entitySrc, entityInput)) {
            WkTblMasterAllByXmlEntity entityAns = new WkTblMasterAllByXmlEntity();
            entityAns.setWkTblMasterAllByXmlId(-1);
            return entityAns;
        }

        // 関連者区分を決めたら個人・企業・政治団体各最小マスタワークテーブルに転換
        Integer newId = 0;
        switch (entityInput.getKanrenshaKbn()) {
            case 1: // 個人
                newId = convertWkTblXmlToMasterPersonLogic.practice(entityInput, userDto);
                // 影響発生させて項目として終了
                entityInput.setIsFinish(true);
                entityInput.setIsAffected(true);
                break;
            case 2: // 企業団体
                newId = convertWkTblXmlToMasterKigyouDtLogic.practice(entityInput, userDto);
                // 影響発生させて項目として終了
                entityInput.setIsFinish(true);
                entityInput.setIsAffected(true);
                break;
            case 3: // 政治団体 SUPPRESS CHECKSTYLE MagicNumber
                newId = convertWkTblXmlToMasterSeijidantaiLogic.practice(entityInput, userDto);
                // 影響発生させて項目として終了
                entityInput.setIsFinish(true);
                entityInput.setIsAffected(true);
                break;

            default:
                // 外部で更新した体を取って削除フラグを立てる
                newId = -1;
                break;
        }

        // 更新できたら必ず旧データを削除
        Integer wkTblId = 0;
        if (newId != 0) {
            setTableDataHistoryUtil.practiceDelete(userDto, entitySrc);
            wkTblId = wkTblMasterAllByXmlRepository.save(entitySrc).getWkTblMasterAllByXmlId();
        }

        if (0 != wkTblId) {
            entityInput.setWkTblMasterAllByXmlId(0); // 履歴を積むのでauto_increment
            final Short zero = Short.valueOf("0");
            if (zero.equals(entityInput.getKanrenshaKbn())) {
                // 団体区分が0の時は引き続きXMLワークテーブルにい続ける
                setTableDataHistoryUtil.practiceInsert(userDto, entityInput);
            } else {
                entityInput.setIsAffected(true);
                entityInput.setIsFinish(true);
                entityInput.setIsDisabled(true);
                entityInput.setJudgeReason("最小マスタへ移動済;");
                setTableDataHistoryUtil.practiceDelete(userDto, entityInput);
            }
            return wkTblMasterAllByXmlRepository.save(entityInput);
        }

        return new WkTblMasterAllByXmlEntity();
    }

    /**
     * エンティティ同士の比較処理をする
     *
     * @param src  比較対象1(データベース登録)
     * @param copy 比較対象2(データベース登録)
     * @return 比較して違いがなければtrue
     */
    private boolean isNotChangeValue( // SUPPRESS CHECKSTYLE NPath
            final WkTblMasterAllByXmlEntity src, final WkTblMasterAllByXmlEntity copy) {

        if (!src.getIsFinish().equals(copy.getIsFinish())) {
            return false;
        }
        if (!src.getKanrenshaName().equals(copy.getKanrenshaName())) {
            return false;
        }
        if (!src.getAllAddress().equals(copy.getAllAddress())) {
            return false;
        }
        if (!src.getPersonShokugyou().equals(copy.getPersonShokugyou())) {
            return false;
        }
        if (!src.getOrgDelegate().equals(copy.getOrgDelegate())) {
            return false;
        }
        if (!src.getDantaiKbn().equals(copy.getDantaiKbn())) {
            return false;
        }
        if (!src.getHoujinNo().equals(copy.getHoujinNo())) { // NOPMD SimplyReturn
            return false;
        }

        return true;
    }

}
