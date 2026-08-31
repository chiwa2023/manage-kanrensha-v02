package net.seijishikin.jp.normalize.manage.kanrensha.logic.add_xml;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblMasterAllByXmlEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblMasterAllByXmlRepository;
import net.seijishikin.jp.normalize.shuushi_doc.v05.constants.ShuushiDocV5YoushikiEdaIncomeConstants;
import net.seijishikin.jp.normalize.shuushi_doc.v05.constants.ShuushiDocV5YoushikiKbnConstants;
import net.seijishikin.jp.normalize.shuushi_doc.v05.dto.AllBookShushiV05Dto;
import net.seijishikin.jp.normalize.shuushi_doc.v05.dto.Row070711DonateDto;
import net.seijishikin.jp.normalize.shuushi_doc.v05.dto.Row070812MediationDto;
import net.seijishikin.jp.normalize.shuushi_doc.v05.dto.Sheet071101ConsiderationPartyPerspnalDto;
import net.seijishikin.jp.normalize.shuushi_doc.v05.dto.Sheet071102ConsiderationPartyGroupDto;
import net.seijishikin.jp.normalize.shuushi_doc.v05.dto.Sheet071103ConsiderationPartyPoliticOrgDto;
import net.seijishikin.jp.normalize.shuushi_doc.v05.dto.Sheet071201MediationPartyPersonalDto;
import net.seijishikin.jp.normalize.shuushi_doc.v05.dto.Sheet071202MediationPartyGroupDto;
import net.seijishikin.jp.normalize.shuushi_doc.v05.dto.Sheet071203MediationPartyPoliOrgDto;


/**
 * XMLから最小マスタ登全項目ワークテーブル挿入(11,12パーティ)
 */
@Service
public class InsertWktblXmlByPublishKanrenshaPoliPartyLogic {

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
    @Transactional
    public boolean practice(final AllBookShushiV05Dto allBookDto, final LeastUserDto userDto) {

        // 様式7-11-1
        for (Sheet071101ConsiderationPartyPerspnalDto sheetDto : allBookDto.getAllSheet0711ConsiderationPartyDto()
                .getAllSheetKbn071101Dto().getList()) {
            this.loopRow11(sheetDto.getList(), ShuushiDocV5YoushikiEdaIncomeConstants.PERSON, userDto);
        }

        // 様式7-11-2
        for (Sheet071102ConsiderationPartyGroupDto sheetDto : allBookDto.getAllSheet0711ConsiderationPartyDto()
                .getAllSheetKbn071102Dto().getList()) {
            this.loopRow11(sheetDto.getList(), ShuushiDocV5YoushikiEdaIncomeConstants.KIGYOU_DT, userDto);
        }

        // 様式7-11-3
        for (Sheet071103ConsiderationPartyPoliticOrgDto sheetDto : allBookDto.getAllSheet0711ConsiderationPartyDto()
                .getAllSheetKbn071103Dto().getList()) {
            this.loopRow11(sheetDto.getList(), ShuushiDocV5YoushikiEdaIncomeConstants.SEIJI_DANTAI, userDto);
        }

        // 様式7-12-1
        for (Sheet071201MediationPartyPersonalDto sheetDto : allBookDto.getAllSheet0712PartyMediationDto()
                .getAllSheetKbn071201Dto().getList()) {
            this.loopRow12(sheetDto.getList(), ShuushiDocV5YoushikiEdaIncomeConstants.PERSON, userDto);
        }

        // 様式7-12-2
        for (Sheet071202MediationPartyGroupDto sheetDto : allBookDto.getAllSheet0712PartyMediationDto()
                .getAllSheetKbn071202Dto().getList()) {
            this.loopRow12(sheetDto.getList(), ShuushiDocV5YoushikiEdaIncomeConstants.KIGYOU_DT, userDto);
        }

        // 様式7-12-3
        for (Sheet071203MediationPartyPoliOrgDto sheetDto : allBookDto
                .getAllSheet0712PartyMediationDto().getAllSheetKbn071203Dto().getList()) {
            this.loopRow12(sheetDto.getList(), ShuushiDocV5YoushikiEdaIncomeConstants.SEIJI_DANTAI, userDto);
        }

        return true;
    }

    private boolean loopRow12(final List<Row070812MediationDto> listRow0812, final int youshikiEdaKn,
            final LeastUserDto userDto) {
        List<WkTblMasterAllByXmlEntity> listEntity = new ArrayList<>();
        for (Row070812MediationDto row : listRow0812) {
            listEntity.add(this.createWkTblEntity(row.getName(), row.getJuusho(), row.getShokugyou(),
                    ShuushiDocV5YoushikiKbnConstants.PARTY_ASSEN, youshikiEdaKn, userDto));
        }
        this.saveEntity(listEntity);
        return true;
    }

    private boolean loopRow11(final List<Row070711DonateDto> listRow0711, final int youshikiEdaKn,
            final LeastUserDto userDto) {
        List<WkTblMasterAllByXmlEntity> listEntity = new ArrayList<>();
        for (Row070711DonateDto row : listRow0711) {
            listEntity.add(this.createWkTblEntity(row.getKifusha(), row.getJusho(), row.getShokugyou(),
                    ShuushiDocV5YoushikiKbnConstants.PARTY, youshikiEdaKn, userDto));
        }
        this.saveEntity(listEntity);
        return true;
    }

    private WkTblMasterAllByXmlEntity createWkTblEntity(final String name, final String allAddress, final String key,
            final int youshikiKbn, final int youshikiEdaKbn, final LeastUserDto userDto) {

        WkTblMasterAllByXmlEntity wkTblMasterAllByXmlEntity = new WkTblMasterAllByXmlEntity();

        wkTblMasterAllByXmlEntity.setInputSrcName(name);
        wkTblMasterAllByXmlEntity.setKanrenshaName(name);
        wkTblMasterAllByXmlEntity.setInputSrcAddress(allAddress);
        wkTblMasterAllByXmlEntity.setAllAddress(allAddress);
        wkTblMasterAllByXmlEntity.setInputSrcKey(key);
        wkTblMasterAllByXmlEntity.setOrgDelegate(key);
        wkTblMasterAllByXmlEntity.setPersonShokugyou(key);

        wkTblMasterAllByXmlEntity.setYoushikiKbn((short) youshikiKbn);
        wkTblMasterAllByXmlEntity.setYoushikiEdaKbn((short) youshikiEdaKbn);
        wkTblMasterAllByXmlEntity.setKanrenshaKbn((short)youshikiEdaKbn);

        wkTblMasterAllByXmlEntity.setJudgeReason("別テ)");
        wkTblMasterAllByXmlEntity.setIsAffected(false);
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
