package net.seijishikin.jp.normalize.manage.kanrensha.logic.add_xml;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblMasterAllByXmlEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblMasterAllByXmlRepository;
import net.seijishikin.jp.normalize.shuushi_doc.v05.constants.ShuushiDocV5YoushikiKbnConstants;
import net.seijishikin.jp.normalize.shuushi_doc.v05.dto.AllBookShushiV05Dto;
import net.seijishikin.jp.normalize.shuushi_doc.v05.dto.Row070300JournalAndOtherDto;
import net.seijishikin.jp.normalize.shuushi_doc.v05.dto.Row070400BorrowedMoneyDto;
import net.seijishikin.jp.normalize.shuushi_doc.v05.dto.Row070600OtherIncomeDto;


/**
 * XMLから最小マスタ登録1項目ワークテーブル挿入
 */
@Component
public class InsertWktblXmlByPublishBikouLogic {

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

        // 様式7の3:備考欄に取引相手の記載がある可能性がある
        boolean isGoNext3;
        List<Row070300JournalAndOtherDto> listRow0703 = allBookDto.getAllSheet0703JournalAndOtherDto()
                .getSheet070300JournalAndOtherDto().getList();
        if (listRow0703.isEmpty()) {
            isGoNext3 = true;
        } else {
            List<WkTblMasterAllByXmlEntity> listEntity = new ArrayList<>();
            for (Row070300JournalAndOtherDto row : listRow0703) {
                listEntity.add(
                        this.createWkTblEntity(row.getBikou(), ShuushiDocV5YoushikiKbnConstants.KIKANSHI, userDto));
            }
            isGoNext3 = this.saveEntity(listEntity);
        }

        // 様式7の4:わかるのは借入先(備考に住所の記載があればラッキー)
        boolean isGoNext4;
        List<Row070400BorrowedMoneyDto> listRow0704 = allBookDto.getAllSheet0704BorrowedMoneyDto()
                .getSheet070400BorrowedMoneyDto().getList();
        if (listRow0704.isEmpty() && isGoNext3) {
            isGoNext4 = true;
        } else {
            List<WkTblMasterAllByXmlEntity> listEntity = new ArrayList<>();
            for (Row070400BorrowedMoneyDto row : listRow0704) {
                listEntity.add(
                        this.createWkTblEntity(row.getBikou(), ShuushiDocV5YoushikiKbnConstants.SHAKUNYUKIN, userDto));
            }
            isGoNext4 = this.saveEntity(listEntity);
        }

        // 様式7の6:備考欄に取引相手の記載がある可能性がある
        boolean isGoNext6;
        List<Row070600OtherIncomeDto> listRow0706 = allBookDto.getAllSheet0706OtherIncomeDto()
                .getSheet070600OtherIncomeDto().getList();
        if (listRow0706.isEmpty() && isGoNext4) {
            isGoNext6 = true;
        } else {
            List<WkTblMasterAllByXmlEntity> listEntity = new ArrayList<>();
            for (Row070600OtherIncomeDto row : listRow0706) {
                listEntity.add(
                        this.createWkTblEntity(row.getBikou(), ShuushiDocV5YoushikiKbnConstants.SHUUNYU_SONOTA, userDto));
            }
            isGoNext6 = this.saveEntity(listEntity);
        }

        return isGoNext6;
    }

    private WkTblMasterAllByXmlEntity createWkTblEntity(final String bikou, final int youshikiKbn,
            final LeastUserDto userDto) {

        WkTblMasterAllByXmlEntity wkTblMasterAllByXmlEntity = new WkTblMasterAllByXmlEntity();
        wkTblMasterAllByXmlEntity.setBikou(bikou);
        wkTblMasterAllByXmlEntity.setYoushikiKbn((short) youshikiKbn);
        wkTblMasterAllByXmlEntity.setJudgeReason("関連者区分が未決定です;");
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
