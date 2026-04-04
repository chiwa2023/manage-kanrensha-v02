package net.seijishikin.jp.normalize.manage.kanrensha.logic.add_xml;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblMasterAllByXmlEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblMasterAllByXmlRepository;
import net.seijishikin.jp.normalize.shuushi_doc.v05.constants.ShuushiDocV5YoushikiEdaIncomeConstants;
import net.seijishikin.jp.normalize.shuushi_doc.v05.constants.ShuushiDocV5YoushikiKbnConstants;
import net.seijishikin.jp.normalize.shuushi_doc.v05.dto.AllBookShushiV05Dto;
import net.seijishikin.jp.normalize.shuushi_doc.v05.dto.Row070711DonateDto;
import net.seijishikin.jp.normalize.shuushi_doc.v05.dto.Row070812MediationDto;


/**
 * XMLから最小マスタ登全項目ワークテーブル挿入(07,08寄付)
 */
@Service
public class InsertWktblXmlByPublishKanrenshaDonateLogic {

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

        // 様式7の7-1:寄付者の氏名、住所、職業
        for (Row070711DonateDto rowDto : allBookDto.getAllSheet0707DonateDto().getAllSheetKbn070701Dto()
                .getSheet070701DonatePersonDto().getList()) {
            listEntity.add(this.createWkTblEntity(rowDto.getKifusha(), rowDto.getJusho(), rowDto.getShokugyou(),
                    ShuushiDocV5YoushikiKbnConstants.DONATE,
                    ShuushiDocV5YoushikiEdaIncomeConstants.PERSON, userDto));

        }
        this.saveEntity(listEntity);
        listEntity.clear();

        // 様式7の7-2:寄付者の団体名称、住所、代表者名
        for (Row070711DonateDto rowDto : allBookDto.getAllSheet0707DonateDto().getAllSheetKbn070702Dto()
                .getSheet070702DonateGroupDto().getList()) {
            listEntity.add(this.createWkTblEntity(rowDto.getKifusha(), rowDto.getJusho(), rowDto.getShokugyou(),
                    ShuushiDocV5YoushikiKbnConstants.DONATE,
                    ShuushiDocV5YoushikiEdaIncomeConstants.KIGYOU_DT, userDto));
        }
        this.saveEntity(listEntity);
        listEntity.clear();

        // 様式7の7-3:寄付者の団体名称、住所、代表者名
        for (Row070711DonateDto rowDto : allBookDto.getAllSheet0707DonateDto().getAllSheetKbn070703Dto()
                .getSheet070703DonatePoliticOrgDto().getList()) {
            listEntity.add(this.createWkTblEntity(rowDto.getKifusha(), rowDto.getJusho(), rowDto.getShokugyou(),
                    ShuushiDocV5YoushikiKbnConstants.DONATE,
                    ShuushiDocV5YoushikiEdaIncomeConstants.SEIJI_DANTAI, userDto));
        }
        this.saveEntity(listEntity);
        listEntity.clear();

        // 様式7の8-1:寄付者の氏名、住所、職業
        for (Row070812MediationDto rowDto : allBookDto.getAllSheet0708MediationDto().getAllSheetKbn070801Dto()
                .getSheet070801MediationPersonDto().getList()) {
            listEntity.add(this.createWkTblEntity(rowDto.getName(), rowDto.getJuusho(), rowDto.getShokugyou(),
                    ShuushiDocV5YoushikiKbnConstants.DONATE_ASSEN,
                    ShuushiDocV5YoushikiEdaIncomeConstants.PERSON, userDto));
        }
        this.saveEntity(listEntity);
        listEntity.clear();

        // 様式7の8-2:寄付者の団体名称、住所、代表者名
        for (Row070812MediationDto rowDto : allBookDto.getAllSheet0708MediationDto().getAllSheetKbn070802Dto()
                .getSheet070802MediationGroupDto().getList()) {
            listEntity.add(this.createWkTblEntity(rowDto.getName(), rowDto.getJuusho(), rowDto.getShokugyou(),
                    ShuushiDocV5YoushikiKbnConstants.DONATE_ASSEN,
                    ShuushiDocV5YoushikiEdaIncomeConstants.KIGYOU_DT, userDto));
        }
        this.saveEntity(listEntity);
        listEntity.clear();

        // 様式7の8-3:寄付者の団体名称、住所、代表者名
        for (Row070812MediationDto rowDto : allBookDto.getAllSheet0708MediationDto().getAllSheetKbn070803Dto()
                .getSheet070803MediationPoliticOrgDto().getList()) {
            listEntity.add(this.createWkTblEntity(rowDto.getName(), rowDto.getJuusho(), rowDto.getShokugyou(),
                    ShuushiDocV5YoushikiKbnConstants.DONATE_ASSEN,
                    ShuushiDocV5YoushikiEdaIncomeConstants.SEIJI_DANTAI, userDto));
        }
        this.saveEntity(listEntity);
        listEntity.clear();

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
        wkTblMasterAllByXmlEntity.setKanrenshaKbn((short) youshikiEdaKbn);

        wkTblMasterAllByXmlEntity.setJudgeReason("別テ)");
        wkTblMasterAllByXmlEntity.setIsAffected(false);
        wkTblMasterAllByXmlEntity.setIsFinish(false);
        setTableDataHistoryUtil.practiceInsert(userDto, wkTblMasterAllByXmlEntity);

        return wkTblMasterAllByXmlEntity;
    }

    private boolean saveEntity(final List<WkTblMasterAllByXmlEntity> listEntity) {
        if (listEntity.isEmpty()) {
            return true;
        }
        
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
