package net.seijishikin.jp.normalize.manage.kanrensha.logic.add_xml; // NOPMD

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblMasterAllByXmlEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblMasterAllByXmlRepository;
import net.seijishikin.jp.normalize.shuushi_doc.v05.constants.ShuushiDocV5YoushikiEdaOutcomeSeijiKatsudouConstants;
import net.seijishikin.jp.normalize.shuushi_doc.v05.constants.ShuushiDocV5YoushikiKbnConstants;
import net.seijishikin.jp.normalize.shuushi_doc.v05.dto.AllBookShushiV05Dto;
import net.seijishikin.jp.normalize.shuushi_doc.v05.dto.Row071415OrdinaryExpensesDto;
import net.seijishikin.jp.normalize.shuushi_doc.v05.dto.Sheet071501OrganizationalActivityDto;
import net.seijishikin.jp.normalize.shuushi_doc.v05.dto.Sheet071502ElectionRelatedExpensesDto;
import net.seijishikin.jp.normalize.shuushi_doc.v05.dto.Sheet071503MagazinePublicationDto;
import net.seijishikin.jp.normalize.shuushi_doc.v05.dto.Sheet071504AdvertisingExpensesDto;
import net.seijishikin.jp.normalize.shuushi_doc.v05.dto.Sheet071505PartyHostingFeeDto;
import net.seijishikin.jp.normalize.shuushi_doc.v05.dto.Sheet071506OtherBusinessExpensesDto;
import net.seijishikin.jp.normalize.shuushi_doc.v05.dto.Sheet071507ResearchExpensesDto;
import net.seijishikin.jp.normalize.shuushi_doc.v05.dto.Sheet071508DonationsGrantsDto;
import net.seijishikin.jp.normalize.shuushi_doc.v05.dto.Sheet071509OtherExpensesDto;


/**
 * XMLから最小マスタ登録名称住所ワークテーブル挿入(15政治活動費)
 */
@Component
public class InsertWktblXmlByPublishNameAddressSeijiKatsudouLogic {

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
    public boolean practice(final AllBookShushiV05Dto allBookDto, // SUPPRESS CHECKSTYLE NPath NOPMD
            final LeastUserDto userDto) {

        // 様式7の15(1-13):氏名と事務所の所在地
        for (Sheet071501OrganizationalActivityDto dto : allBookDto.getAllSheet0715ExpenseDto()
                .getAllSheetKbn071501Dto().getList()) {
            this.loopRow(dto.getList(), ShuushiDocV5YoushikiKbnConstants.SEIJIKATSUDOUHI,
                    ShuushiDocV5YoushikiEdaOutcomeSeijiKatsudouConstants.SOSHIKI_KATSUDOU, userDto);
        }

        // 枝区分項目2
        for (Sheet071502ElectionRelatedExpensesDto dto : allBookDto.getAllSheet0715ExpenseDto()
                .getAllSheetKbn071502Dto().getList()) {
            this.loopRow(dto.getList(), ShuushiDocV5YoushikiKbnConstants.SEIJIKATSUDOUHI,
                    ShuushiDocV5YoushikiEdaOutcomeSeijiKatsudouConstants.SENKYO_KANKEI, userDto);
        }

        // 枝区分項目3
        for (Sheet071503MagazinePublicationDto dto : allBookDto.getAllSheet0715ExpenseDto()
                .getAllSheetKbn071503Dto().getList()) {
            this.loopRow(dto.getList(), ShuushiDocV5YoushikiKbnConstants.SEIJIKATSUDOUHI,
                    ShuushiDocV5YoushikiEdaOutcomeSeijiKatsudouConstants.KIKANSHI_HAKKOU, userDto);
        }

        // 枝区分項目4
        for (Sheet071504AdvertisingExpensesDto dto : allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071504Dto()
                .getList()) {
            this.loopRow(dto.getList(), ShuushiDocV5YoushikiKbnConstants.SEIJIKATSUDOUHI,
                    ShuushiDocV5YoushikiEdaOutcomeSeijiKatsudouConstants.SENDEN, userDto);
        }

        // 枝区分項目5
        for (Sheet071505PartyHostingFeeDto dto : allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071505Dto()
                .getList()) {

            this.loopRow(dto.getList(), ShuushiDocV5YoushikiKbnConstants.SEIJIKATSUDOUHI,
                    ShuushiDocV5YoushikiEdaOutcomeSeijiKatsudouConstants.PARTY_KAISAI, userDto);
        }

        // 枝区分項目6
        for (Sheet071506OtherBusinessExpensesDto dto : allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071506Dto()
                .getList()) {
            this.loopRow(dto.getList(), ShuushiDocV5YoushikiKbnConstants.SEIJIKATSUDOUHI,
                    ShuushiDocV5YoushikiEdaOutcomeSeijiKatsudouConstants.OTHER_JIGYOU, userDto);
        }

        // 枝区分項目7
        for (Sheet071507ResearchExpensesDto dto : allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071507Dto()
                .getList()) {
            this.loopRow(dto.getList(), ShuushiDocV5YoushikiKbnConstants.SEIJIKATSUDOUHI,
                    ShuushiDocV5YoushikiEdaOutcomeSeijiKatsudouConstants.CHOUSA_KENKYU, userDto);
        }

        // 枝区分項目8
        for (Sheet071508DonationsGrantsDto dto : allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071508Dto()
                .getList()) {
            this.loopRow(dto.getList(), ShuushiDocV5YoushikiKbnConstants.SEIJIKATSUDOUHI,
                    ShuushiDocV5YoushikiEdaOutcomeSeijiKatsudouConstants.KIFU_KOUFUKIN, userDto);
        }

        // 枝区分項目9
        for (Sheet071509OtherExpensesDto dto : allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071509Dto()
                .getList()) {
            this.loopRow(dto.getList(), ShuushiDocV5YoushikiKbnConstants.SEIJIKATSUDOUHI,
                    ShuushiDocV5YoushikiEdaOutcomeSeijiKatsudouConstants.SONOTA_KEIHI, userDto);
        }

        return true;
    }

    private boolean loopRow(final List<Row071415OrdinaryExpensesDto> listRow07145, final int youshikiKbn,
            final int youshikiEdaKn, final LeastUserDto userDto) {

        if (listRow07145.isEmpty()) {
            return true;
        }

        List<WkTblMasterAllByXmlEntity> listEntity = new ArrayList<>();
        for (Row071415OrdinaryExpensesDto row : listRow07145) {
            listEntity.add(this.createWkTblEntity(row.getName(), row.getJusho(), youshikiKbn, youshikiEdaKn, userDto));
        }
        return this.saveEntity(listEntity);
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
        Integer code = 1;

        Optional<WkTblMasterAllByXmlEntity> optional = wkTblMasterAllByXmlRepository
                .findFirstByOrderByWkTblMasterAllByXmlCodeDesc();
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
