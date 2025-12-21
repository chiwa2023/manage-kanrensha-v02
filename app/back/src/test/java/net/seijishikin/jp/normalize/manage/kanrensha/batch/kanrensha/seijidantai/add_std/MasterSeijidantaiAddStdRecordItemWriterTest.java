package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.seijidantai.add_std; // NOPMD ExcessiveImport

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.test.MetaDataInstanceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaSeijidantaiAccessEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaSeijidantaiAddressEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaSeijidantaiMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaSeijidantaiPropertyEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaSeijidantaiMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaSeijidantaiMasterResultEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaSeijidantaiHistory01Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaSeijidantaiAccessRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaSeijidantaiAddressRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaSeijidantaiMasterRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaSeijidantaiPropertyRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaSeijidantaiMasterResultRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaSeijidantaiHistory01Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * MasterSeijidantaiAddStdRecordItemWriter単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("MasterSeijidantaiAddStdRecordItemWriterTest.sql")
class MasterSeijidantaiAddStdRecordItemWriterTest {

    /** テスト対象 */
    @Autowired
    private MasterSeijidantaiAddStdRecordItemWriter masterSeijidantaiAddStdRecordItemWriter;

    /** 関連者個人マスタ標準判定結果Repository */
    @Autowired
    private WkTblKanrenshaSeijidantaiMasterResultRepository wkTblKanrenshaSeijidantaiMasterResultRepository;

    /** 関連者政治団体履歴Repository(01) */
    @Autowired
    private KanrenshaSeijidantaiHistory01Repository kanrenshaSeijidantaiHistory01Repository;

    /** 関連者個人マスタRepository */
    @Autowired
    private KanrenshaSeijidantaiMasterRepository kanrenshaSeijidantaiMasterRepository;

    /** 関連者個人マスタ住所Repository */
    @Autowired
    private KanrenshaSeijidantaiAddressRepository kanrenshaSeijidantaiAddressRepository;

    /** 関連者個人マスタ住所Repository */
    @Autowired
    private KanrenshaSeijidantaiAccessRepository kanrenshaSeijidantaiAccessRepository;

    /** 関連者個人マスタ(その他属性)Repository */
    @Autowired
    private KanrenshaSeijidantaiPropertyRepository kanrenshaSeijidantaiPropertyRepository;

    @Test
    @Tag("TableTruncate")
    void test() {

        WkTblKanrenshaSeijidantaiMasterEntity entity00 = new WkTblKanrenshaSeijidantaiMasterEntity();

        // 政治団体名
        entity00.setKanrenshaName("ちゃらんぽらん政治団体");
        // 政治団体全住所
        entity00.setAllAddress("北海道架空市山麓町");
        // 政治団体代表者
        entity00.setSeijidantaiDelegate("代表者　　太郎");
        // 政治団体区分
        entity00.setDantaiKbn("05");
        // 政治団体番号
        entity00.setPoliOrgNo("98987");
        // 住所郵便番号
        entity00.setAddressPostal("北海道架空市山麓町");
        // 住所番地
        entity00.setAddressBlock("6丁目32番地");
        // 住所建物
        entity00.setAddressBuilding("三角ビル4F");
        // 郵便番号1
        entity00.setPostalcode1("012");
        // 郵便番号2
        entity00.setPostalcode2("3456");
        // 地方自治体コード
        entity00.setLgCode("987654");
        // 町字コード
        entity00.setMachiazaId("876");
        // 街区コード
        entity00.setBlkId("765");
        // 地番コード
        entity00.setPrcId("960");
        // 住居コード
        entity00.setRsdtId("654");
        // 住居2コード
        entity00.setRsdt2Id("543");
        // 電話番号1
        entity00.setPhon1("012");
        // 電話番号2
        entity00.setPhon2("34566");
        // 電話番号3
        entity00.setPhon3("7890");
        // 電子メール
        entity00.setEmail("aaa@bbb.com");
        // 所有(公式)url
        entity00.setMyPortalUrl("https://bbb.com/aaa");
        // SNSサービス名称
        entity00.setSnsServiceName("弱小SNS");
        // SNSサービスアカウント
        entity00.setSnsAccount("@aaa_bbb");
        // 関連者団体名称かな
        entity00.setOrgNameKana("ちゃらんぽらんせいじだんたい");
        // 団体代表者関連者コード
        entity00.setOrgDelegateCode("3333-444444");
        // 会計責任者関連者個人コード
        entity00.setAccountMgrCode("4444-5555555");
        // 会計責任者関連者個人氏名
        entity00.setAccountMgrName("会計責任者　花子");

        entity00.setIsAffected(true);
        entity00.setIsFinish(false);
        entity00.setJudgeReason("理由");

        List<WkTblKanrenshaSeijidantaiMasterEntity> listLoad = new ArrayList<>();
        listLoad.add(entity00);

        // Chunkを作成してセット
        Chunk<? extends WkTblKanrenshaSeijidantaiMasterEntity> items = new Chunk<>(listLoad);

        masterSeijidantaiAddStdRecordItemWriter.beforeStep(this.getStepExecution());
        masterSeijidantaiAddStdRecordItemWriter.write(items);

        // 履歴テーブル本体に正常登録
        List<KanrenshaSeijidantaiHistory01Entity> listHistory = kanrenshaSeijidantaiHistory01Repository.findAll();
        assertEquals(1, listHistory.size());
        KanrenshaSeijidantaiHistory01Entity entity10 = listHistory.get(0);
        assertEquals(entity00.getKanrenshaName(), entity10.getAllName());
        assertEquals(entity00.getAllAddress(), entity10.getAllAddress());
        assertEquals(entity00.getSeijidantaiDelegate(), entity10.getOrgDelegateName());
        assertEquals(entity00.getOrgDelegateCode(), entity10.getOrgDelegateCode());
        assertTrue(entity10.getSeijidantaiKanrenshaCode().startsWith("989-87"));
        assertEquals("ちゃらんぽらん政治団体北海道架空市山麓町代表者太郎", entity10.getSearchText());

        // マスタ本体に正常登録
        List<KanrenshaSeijidantaiMasterEntity> listMaster = kanrenshaSeijidantaiMasterRepository.findAll();
        assertEquals(1, listMaster.size());
        KanrenshaSeijidantaiMasterEntity entity11 = listMaster.get(0);
        assertEquals(entity00.getKanrenshaName(), entity11.getKanrenshaName());
        assertEquals(entity00.getAllAddress(), entity11.getAllAddress());
        assertEquals(entity00.getSeijidantaiDelegate(), entity11.getSeijidantaiDelegate());
        assertEquals(entity00.getDantaiKbn(), entity11.getDantaiKbn());
        assertEquals(entity00.getPoliOrgNo(), entity11.getPoliOrgNo());
        assertTrue(entity11.getSeijidantaiKanrenshaCode().startsWith("989-87"));

        // マスタ住所に正常登録
        List<KanrenshaSeijidantaiAddressEntity> listAddress = kanrenshaSeijidantaiAddressRepository.findAll();
        assertEquals(1, listAddress.size());
        KanrenshaSeijidantaiAddressEntity entity12 = listAddress.get(0);
        assertEquals(entity00.getAddressPostal(), entity12.getAddressPostal());
        assertEquals(entity00.getAddressBlock(), entity12.getAddressBlock());
        assertEquals(entity00.getAddressBuilding(), entity12.getAddressBuilding());
        assertEquals(entity00.getPostalcode1(), entity12.getPostalcode1());
        assertEquals(entity00.getPostalcode2(), entity12.getPostalcode2());
        assertEquals(entity00.getLgCode(), entity12.getLgCode());
        assertEquals(entity00.getMachiazaId(), entity12.getMachiazaId());
        assertEquals(entity00.getBlkId(), entity12.getBlkId());
        assertEquals(entity00.getPrcId(), entity12.getPrcId());
        assertEquals(entity00.getRsdtId(), entity12.getRsdtId());
        assertEquals(entity00.getRsdt2Id(), entity12.getRsdt2Id());
        assertEquals(true, entity12.getIsPostalEdit());
        assertEquals(true, entity12.getIsBlockEdit());
        assertEquals(true, entity12.getIsBuildingEdit());
        assertEquals(false, entity12.getIsPostalAccept());
        assertEquals(false, entity12.getIsBlockAccept());
        assertEquals(false, entity12.getIsBuildingAccept());

        // マスタ連絡先に正常登録
        List<KanrenshaSeijidantaiAccessEntity> listAccess = kanrenshaSeijidantaiAccessRepository.findAll();
        assertEquals(1, listAccess.size());
        KanrenshaSeijidantaiAccessEntity entity13 = listAccess.get(0);
        assertEquals(entity00.getPhon1(), entity13.getPhon1());
        assertEquals(entity00.getPhon2(), entity13.getPhon2());
        assertEquals(entity00.getPhon3(), entity13.getPhon3());
        assertEquals(entity00.getEmail(), entity13.getEmail());
        assertEquals(entity00.getMyPortalUrl(), entity13.getMyPortalUrl());
        assertEquals(entity00.getSnsServiceName(), entity13.getSnsServiceName());
        assertEquals(entity00.getSnsAccount(), entity13.getSnsAccount());

        // マスタ属性に正常登録
        List<KanrenshaSeijidantaiPropertyEntity> listProperty = kanrenshaSeijidantaiPropertyRepository.findAll();
        assertEquals(1, listProperty.size());
        KanrenshaSeijidantaiPropertyEntity entity15 = listProperty.get(0);
        assertEquals(entity00.getOrgNameKana(), entity15.getOrgNameKana());
        assertEquals(entity00.getOrgDelegateCode(), entity15.getOrgDelegateCode());
        assertEquals(entity00.getAccountMgrCode(), entity15.getAccountMgrCode());
        assertEquals(entity00.getAccountMgrName(), entity15.getAccountMgrName());

        // 処理結果に正常登録
        List<WkTblKanrenshaSeijidantaiMasterResultEntity> listResult = wkTblKanrenshaSeijidantaiMasterResultRepository
                .findAll();
        assertEquals(1, listResult.size());
        assertEquals(entity00.getWkTblKanrenshaSeijidantaiMasterId(),
                listResult.get(0).getWkTblKanrenshaSeijidantaiMasterId());
    }

    private StepExecution getStepExecution() {
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        JobParameters jobParameters = new JobParametersBuilder().addLong("userId", (long) userDto.getUserPersonId())
                .addLong("userCode", (long) userDto.getUserPersonCode())
                .addString("userName", userDto.getUserPersonName()).toJobParameters();
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

}
