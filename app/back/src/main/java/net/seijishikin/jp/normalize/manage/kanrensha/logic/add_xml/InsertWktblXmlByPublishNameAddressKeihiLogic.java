package net.seijishikin.jp.normalize.manage.kanrensha.logic.add_xml;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblMasterAllByXmlEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblMasterAllByXmlRepository;
import net.seijishikin.jp.normalize.shuushi_doc.v05.constants.ShuushiDocV5YoushikiEdaOutcomeKeihiConstants;
import net.seijishikin.jp.normalize.shuushi_doc.v05.constants.ShuushiDocV5YoushikiKbnConstants;
import net.seijishikin.jp.normalize.shuushi_doc.v05.dto.AllBookShushiV05Dto;
import net.seijishikin.jp.normalize.shuushi_doc.v05.dto.Row070500RelatedToGrantsDto;
import net.seijishikin.jp.normalize.shuushi_doc.v05.dto.Row071415OrdinaryExpensesDto;
import net.seijishikin.jp.normalize.shuushi_doc.v05.dto.Row071600ExpendituresGrantsDto;


/**
 * XMLから最小マスタ登録名称住所ワークテーブル挿入(14経費他)
 */
@Component
public class InsertWktblXmlByPublishNameAddressKeihiLogic {

    /** XMLから最小マスタ登録Repositry */
    @Autowired
    private WkTblMasterAllByXmlRepository wkTblMasterAllByXmlRepository;

    /** テーブル履歴設定Utility */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /**
     * 処理を行う
     *
     * @param allBookDto 政治資金収支報告書XMlDto
     * @param userDto    ユーザ最小限Dto
     * @return true(悲観ロックをしているので処理が直列でなければならない対策)
     */
    public boolean practice(final AllBookShushiV05Dto allBookDto, final LeastUserDto userDto) {
        List<WkTblMasterAllByXmlEntity> listEntity = new ArrayList<>();

        // 様式7の5:支部の名称とに事務所の所在地の記述
        for (Row070500RelatedToGrantsDto row : allBookDto.getAllSheet0705IncomeRelatedToGrantsDto()
                .getSheet070500IncomeRelatedToGrantsDto().getList()) {
            listEntity.add(this.createWkTblEntity(row.getHonbuShibuName(), row.getJimushoJuusho(),
                    ShuushiDocV5YoushikiKbnConstants.KOUFUKIN, 0, userDto));
        }
        this.saveEntity(listEntity);
        listEntity.clear();

        // 様式7の14(1-3):氏名と事務所の所在地
        for (Row071415OrdinaryExpensesDto row : allBookDto.getAllSheet0714ConstsDto().getAllSheetKbn071401Dto()
                .getSheet071401UtilityCostsDto().getList()) {
            listEntity.add(
                    this.createWkTblEntity(row.getName(), row.getJusho(), ShuushiDocV5YoushikiKbnConstants.KEIJO_KEIHI,
                            ShuushiDocV5YoushikiEdaOutcomeKeihiConstants.KOUNETSUSUI, userDto));
        }
        this.saveEntity(listEntity);
        listEntity.clear();

        // 様式7の14の3
        for (Row071415OrdinaryExpensesDto row : allBookDto.getAllSheet0714ConstsDto().getAllSheetKbn071402Dto()
                .getSheet071402EquipmentCostsDto().getList()) {
            listEntity.add(
                    this.createWkTblEntity(row.getName(), row.getJusho(), ShuushiDocV5YoushikiKbnConstants.KEIJO_KEIHI,
                            ShuushiDocV5YoushikiEdaOutcomeKeihiConstants.BIHIN_SHOUMOUHIN, userDto));
        }
        this.saveEntity(listEntity);
        listEntity.clear();

        // 様式7の14の4
        for (Row071415OrdinaryExpensesDto row : allBookDto.getAllSheet0714ConstsDto().getAllSheetKbn071403Dto()
                .getSheet071403OfficeExpensesDto().getList()) {
            listEntity.add(
                    this.createWkTblEntity(row.getName(), row.getJusho(), ShuushiDocV5YoushikiKbnConstants.KEIJO_KEIHI,
                            ShuushiDocV5YoushikiEdaOutcomeKeihiConstants.JIMUSHO, userDto));
        }
        this.saveEntity(listEntity);
        listEntity.clear();

        // 様式7の16:氏名と事務所の所在地
        for (Row071600ExpendituresGrantsDto row : allBookDto.getAllSheet0716RelatedToGrantsDtoDto()
                .getSheet071600ExpendituresRelatedToGrantsDto().getList()) {
            listEntity.add(this.createWkTblEntity(row.getHonShibuName(), row.getJusho(), //
                    ShuushiDocV5YoushikiKbnConstants.KOUFUKIN_SHISHUTSU, 0, userDto)); // SUPPRESS CHECKSTYLE MagicNumber
        }
        this.saveEntity(listEntity);
        listEntity.clear();

        return true;
    }

    private WkTblMasterAllByXmlEntity createWkTblEntity(final String name, final String allAddress,
            final int youshikiKbn, final int youshikiEdaKbn, final LeastUserDto userDto) {

        WkTblMasterAllByXmlEntity wkTblMasterAllByXmlEntity = new WkTblMasterAllByXmlEntity();

        wkTblMasterAllByXmlEntity.setInputSrcName(name);
        wkTblMasterAllByXmlEntity.setKanrenshaName(name);
        wkTblMasterAllByXmlEntity.setInputSrcAddress(allAddress);
        wkTblMasterAllByXmlEntity.setAllAddress(allAddress);
        wkTblMasterAllByXmlEntity.setYoushikiKbn((short) youshikiKbn);
        wkTblMasterAllByXmlEntity.setYoushikiEdaKbn((short) youshikiEdaKbn);

        wkTblMasterAllByXmlEntity.setJudgeReason("関連者区分が未決定です;");
        wkTblMasterAllByXmlEntity.setIsAffected(true);
        wkTblMasterAllByXmlEntity.setIsFinish(false);
        setTableDataHistoryUtil.practiceInsert(userDto, wkTblMasterAllByXmlEntity);

        return wkTblMasterAllByXmlEntity;
    }

    private boolean saveEntity(final List<WkTblMasterAllByXmlEntity> listEntity) {
        if (listEntity.isEmpty()) {
            return true;
        }

        Optional<WkTblMasterAllByXmlEntity> optional = wkTblMasterAllByXmlRepository
                .findFirstByOrderByWkTblMasterAllByXmlCodeDesc();
        Integer code = 1;
        if (!optional.isEmpty()) {
            code += optional.get().getWkTblMasterAllByXmlCode();
        }

        for (WkTblMasterAllByXmlEntity entity : listEntity) {
            entity.setWkTblMasterAllByXmlCode(code);
            code++;
        }

        wkTblMasterAllByXmlRepository.saveAllAndFlush(listEntity);
        return true;
    }

}
