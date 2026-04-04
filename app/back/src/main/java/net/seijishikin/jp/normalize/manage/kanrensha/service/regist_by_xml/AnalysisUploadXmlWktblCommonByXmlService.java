package net.seijishikin.jp.normalize.manage.kanrensha.service.regist_by_xml;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.add_xml.RegistDataByXmlCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.storage_file.StorageFileDto;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.add_xml.InsertWktblXmlByPublishBikouLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.add_xml.InsertWktblXmlByPublishKanrenshaDonateLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.add_xml.InsertWktblXmlByPublishKanrenshaPoliPartyLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.add_xml.InsertWktblXmlByPublishNameAddressKeihiLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.add_xml.InsertWktblXmlByPublishNameAddressSeijiKatsudouLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.add_xml.MoveWktblXmlToMasterMinLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.add_xml.SuspendDuplicateWkTblXmlBikoLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.add_xml.SuspendDuplicateWkTblXmlDecideKanrenshaLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.add_xml.SuspendDuplicateWkTblXmlNameAdddressLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.file.GetAbsolutePathLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblMasterAllByXmlRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.WriteLogService;
import net.seijishikin.jp.normalize.shuushi_doc.v05.dto.AllBookShushiV05Dto;

/**
 * アップロード済XMLファイル解析ワークテーブル複写Service
 */
@Service
public class AnalysisUploadXmlWktblCommonByXmlService {

    /** XMLから最小マスタ登録ワークテーブルRepository */
    @Autowired
    private WkTblMasterAllByXmlRepository wkTblMasterAllByXmlRepository;

    /** 絶対パス取得Logic */
    @Autowired
    private GetAbsolutePathLogic getAbsolutePathLogic;

    /** 関連者備考1項目だけワークテーブル挿入Logic */
    @Autowired
    private InsertWktblXmlByPublishBikouLogic insertWktblXmlByPublishBikouLogic;

    /** 関連者名前と住所ワークテーブル経費挿入Logic */
    @Autowired
    private InsertWktblXmlByPublishNameAddressKeihiLogic insertWktblXmlByPublishNameAddressKeihiLogic;

    /** 関連者備考名前と住所ワークテーブル政治活動費挿入Logic */
    @Autowired
    private InsertWktblXmlByPublishNameAddressSeijiKatsudouLogic insertWktblXmlByPublishNameAddressSeijiKatsudouLogic;

    /** 関連者関連者区分決定ワークテーブル寄付挿入Logic */
    @Autowired
    private InsertWktblXmlByPublishKanrenshaDonateLogic insertWktblXmlByPublishKanrenshaDonateLogic;

    /** 関連者関連者区分決定ワークテーブルパーティ挿入Logic */
    @Autowired
    private InsertWktblXmlByPublishKanrenshaPoliPartyLogic insertWktblXmlByPublishKanrenshaPoliPartyLogic;

    /** 備考1項目重複処理対象が移行Logic */
    @Autowired
    private SuspendDuplicateWkTblXmlBikoLogic suspendDuplicateWkTblXmlBikoLogic;

    /** 備考1項目重複処理対象が移行Logic */
    @Autowired
    private SuspendDuplicateWkTblXmlNameAdddressLogic suspendDuplicateWkTblXmlNameAdddressLogic;

    /** 備考1項目重複処理対象が移行Logic */
    @Autowired
    private SuspendDuplicateWkTblXmlDecideKanrenshaLogic suspendDuplicateWkTblXmlDecideKanrenshaLogic;

    /** 各最小マスタワークテーブルに移管Logic */
    @Autowired
    private MoveWktblXmlToMasterMinLogic moveWktblXmlToMasterMinLogic;

    /** ログ書き出しService */
    @Autowired
    private WriteLogService writeLogService;

    /**
     * 処理を行う
     *
     * @throws JsonProcessingException JSOn変換例外
     */
    @Async
    @Transactional
    public void practice(final RegistDataByXmlCapsuleDto capsuleDto) {

        // TODO アップロード仮保存から本保存に複写

        // 公式XML読み取り
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.setDefaultPropertyInclusion(Include.ALWAYS);
        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);

        StorageFileDto fileDto = capsuleDto.getStorageFileDto();
        Path path = getAbsolutePathLogic.practice(fileDto.getSavedDir(), fileDto.getFileName());

        try {
            AllBookShushiV05Dto allBookDto = xmlMapper.readValue(Files.readAllBytes(path), new TypeReference<>() {
            });

            LeastUserDto userDto = capsuleDto.getUserDto();

            // ワークテーブル初期化
            wkTblMasterAllByXmlRepository.deleteByInsertUserCode(userDto.getUserPersonCode());

            // 記載2項目
            // 様式7の5:支部の名称とに事務所の所在地の記述
            // 様式7の14(2-4):氏名と事務所の所在地
            // 様式7の16:氏名と事務所の所在地
            // 様式7の15(1-13):氏名と事務所の所在地
            if (!capsuleDto.getIsNotNameAddress()) {
                insertWktblXmlByPublishNameAddressKeihiLogic.practice(allBookDto, userDto);
                insertWktblXmlByPublishNameAddressSeijiKatsudouLogic.practice(allBookDto, userDto);

                // 単純な重複を除去(2項目)
                suspendDuplicateWkTblXmlNameAdddressLogic.practice(userDto);
            }

            // 記載1項目
            // 様式7の3:備考欄に取引相手の記載がある可能性がある
            // 様式7の4:わかるのは借入先(備考に住所の記載があればラッキー)
            // 様式7の6:備考欄に取引相手の記載がある可能性がある
            if (!capsuleDto.getIsNotBiko()) {
                insertWktblXmlByPublishBikouLogic.practice(allBookDto, userDto);

                // 単純な重複を除去(1項目)
                suspendDuplicateWkTblXmlBikoLogic.practice(userDto);
            }

            // 記載3項目(最後にはすべて各関連者ワークテーブルに移管するので最後に登録でいいや)
            // 様式7の7-1:寄付者の氏名、住所、職業
            // 様式7の7-2:寄付者の団体名称、住所、代表者名
            // 様式7の7-3:寄付者の団体名称、住所、代表者名
            // 様式7の8-1:寄付者の氏名、住所、職業
            // 様式7の8-2:寄付者の団体名称、住所、代表者名
            // 様式7の8-3:寄付者の団体名称、住所、代表者名
            insertWktblXmlByPublishKanrenshaDonateLogic.practice(allBookDto, userDto);

            // 様式7の11-1:パーティ券の氏名、住所、職業
            // 様式7の11-2:パーティ券の団体名称、住所、代表者名
            // 様式7の11-3:パーティ券の団体名称、住所、代表者名
            // 様式7の12-1:パーティ券の氏名、住所、職業
            // 様式7の12-2:パーティ券の団体名称、住所、代表者名
            // 様式7の12-3:パーティ券の団体名称、住所、代表者名
            insertWktblXmlByPublishKanrenshaPoliPartyLogic.practice(allBookDto, userDto);

            // 様式7の9:相手が匿名であることが大前提である様式
            // 様式7の10:パーティ券購入者の明細を記載しないで良い様式

            // 単純な重複を除去(3項目)
            suspendDuplicateWkTblXmlDecideKanrenshaLogic.practice(userDto);

            // 関連者区分が決定しているデータは各ワークテーブルに移管
            moveWktblXmlToMasterMinLogic.practce(userDto);

        } catch (IOException e) {
            // TODO: handle exception
            // ログ記載
            writeLogService.practiceError("", e);
        }
    }
}
