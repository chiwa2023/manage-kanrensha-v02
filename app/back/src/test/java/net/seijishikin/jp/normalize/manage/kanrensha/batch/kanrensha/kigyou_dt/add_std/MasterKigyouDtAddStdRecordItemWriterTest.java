package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.kigyou_dt.add_std; // NOPMD ExcessiveImport

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
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaKigyouDtAccessEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaKigyouDtAddressEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaKigyouDtMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaKigyouDtPropertyEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaKigyouDtMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaKigyouDtMasterResultEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory01Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaKigyouDtAccessRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaKigyouDtAddressRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaKigyouDtMasterRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaKigyouDtPropertyRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaKigyouDtMasterResultRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaKigyouDtHistory01Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * MasterKigyouDtAddStdRecordItemWriter単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("MasterKigyouDtAddStdRecordItemWriterTest.sql")
class MasterKigyouDtAddStdRecordItemWriterTest {

    /** テスト対象 */
    @Autowired
    private MasterKigyouDtAddStdRecordItemWriter masterKigyouDtAddStdRecordItemWriter;

    /** 関連者企業・団体履歴Repository(01) */
    @Autowired
    private KanrenshaKigyouDtHistory01Repository kanrenshaKigyouDtHistory01Repository;

    /** 関連者企業・団体マスタRepository */
    @Autowired
    private KanrenshaKigyouDtMasterRepository kanrenshaKigyouDtMasterRepository;

    /** 関連者企業・団体マスタ住所Repository */
    @Autowired
    private KanrenshaKigyouDtAccessRepository kanrenshaKigyouDtAccessRepository;

    /** 関連者企業・団体マスタ住所Repository */
    @Autowired
    private KanrenshaKigyouDtAddressRepository kanrenshaKigyouDtAddressRepository;

    /** 関連者企業・団体マスタ(その他属性)Repository */
    @Autowired
    private KanrenshaKigyouDtPropertyRepository kanrenshaKigyouDtPropertyRepository;

    /** 企業団体ワークテーブル処理結果Repository */
    @Autowired
    private WkTblKanrenshaKigyouDtMasterResultRepository wkTblKanrenshaKigyouDtMasterResultRepository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        WkTblKanrenshaKigyouDtMasterEntity entity00 = new WkTblKanrenshaKigyouDtMasterEntity();

        entity00.setKanrenshaName("超元素製造組合");
        entity00.setAllAddress("北海道架空市湖畔町");
        entity00.setKigyouDtDelegate("組合長　花子");
        entity00.setHoujinNo("11-222-3333");

        entity00.setAddressPostal("郵便番号住所");
        entity00.setAddressBlock("番地住所");
        entity00.setAddressBuilding("建物住所");
        entity00.setPostalcode1("987");
        entity00.setPostalcode2("6543");
        entity00.setLgCode("012");
        entity00.setMachiazaId("123");
        entity00.setBlkId("234");
        entity00.setPrcId("989");
        entity00.setRsdtId("345");
        entity00.setRsdt2Id("678");

        entity00.setEmail("aaa@bbbb.net");
        entity00.setMyPortalUrl("https://bbb.net/");
        entity00.setPhon1("567");
        entity00.setPhon2("8901");
        entity00.setPhon3("2345");
        entity00.setSnsAccount("@abcds");
        entity00.setSnsServiceName("弱小ブログ");

        entity00.setOrgNameKana("だんたいめい");
        entity00.setIsShiten(true);
        entity00.setOrgDelegateCode("111-222-333");
        entity00.setHoujinSbts("201");
        entity00.setIsForeign(true);

        entity00.setIsAffected(true);
        entity00.setIsFinish(false);
        entity00.setJudgeReason("理由");

        List<WkTblKanrenshaKigyouDtMasterEntity> listLoad = new ArrayList<>();
        listLoad.add(entity00);

        // Chunkを作成してセット
        Chunk<? extends WkTblKanrenshaKigyouDtMasterEntity> items = new Chunk<>(listLoad);

        masterKigyouDtAddStdRecordItemWriter.beforeStep(this.getStepExecution());
        masterKigyouDtAddStdRecordItemWriter.write(items);

        // 履歴テーブル本体に正常登録
        List<KanrenshaKigyouDtHistory01Entity> listHistory = kanrenshaKigyouDtHistory01Repository.findAll();
        assertEquals(1, listHistory.size());
        KanrenshaKigyouDtHistory01Entity entity10 = listHistory.get(0);
        assertEquals(entity00.getKanrenshaName(), entity10.getAllName());
        assertEquals(entity00.getAllAddress(), entity10.getAllAddress());
        assertEquals(entity00.getKigyouDtDelegate(), entity10.getOrgDelegateName());
        assertTrue(entity10.getKigyouDtKanrenshaCode().startsWith("1-1222-33-33"));
        assertEquals("超元素製造組合北海道架空市湖畔町組合長花子", entity10.getSearchText());

        // マスタ本体に正常登録
        List<KanrenshaKigyouDtMasterEntity> listMaster = kanrenshaKigyouDtMasterRepository.findAll();
        assertEquals(1, listMaster.size());
        KanrenshaKigyouDtMasterEntity entity11 = listMaster.get(0);
        assertEquals(entity00.getKanrenshaName(), entity11.getKanrenshaName());
        assertEquals(entity00.getAllAddress(), entity11.getAllAddress());
        assertEquals(entity00.getKigyouDtDelegate(), entity11.getKigyouDtDelegate());
        assertEquals(entity00.getHoujinNo(), entity11.getHoujinNo());
        assertEquals(entity10.getKigyouDtKanrenshaCode(), entity11.getKigyouDtKanrenshaCode());

        // マスタ住所に正常登録
        List<KanrenshaKigyouDtAddressEntity> listAddress = kanrenshaKigyouDtAddressRepository.findAll();
        assertEquals(1, listAddress.size());
        KanrenshaKigyouDtAddressEntity entity12 = listAddress.get(0);
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
        List<KanrenshaKigyouDtAccessEntity> listAccess = kanrenshaKigyouDtAccessRepository.findAll();
        assertEquals(1, listAccess.size());
        KanrenshaKigyouDtAccessEntity entity13 = listAccess.get(0);
        assertEquals(entity00.getPhon1(), entity13.getPhon1());
        assertEquals(entity00.getPhon2(), entity13.getPhon2());
        assertEquals(entity00.getPhon3(), entity13.getPhon3());
        assertEquals(entity00.getEmail(), entity13.getEmail());
        assertEquals(entity00.getMyPortalUrl(), entity13.getMyPortalUrl());
        assertEquals(entity00.getSnsServiceName(), entity13.getSnsServiceName());
        assertEquals(entity00.getSnsAccount(), entity13.getSnsAccount());

        // マスタ属性に正常登録
        List<KanrenshaKigyouDtPropertyEntity> listProperty = kanrenshaKigyouDtPropertyRepository.findAll();
        assertEquals(1, listProperty.size());
        KanrenshaKigyouDtPropertyEntity entity15 = listProperty.get(0);
        assertEquals(true, entity15.getIsShiten());
        assertEquals(entity00.getOrgDelegateCode(), entity15.getOrgDelegateCode());
        assertEquals(entity00.getOrgNameKana(), entity15.getOrgNameKana());
        assertEquals(entity00.getHoujinSbts(), entity15.getHoujinSbts());
        assertEquals(entity00.getIsForeign(), entity15.getIsForeign());

        // 処理結果に正常登録
        List<WkTblKanrenshaKigyouDtMasterResultEntity> listResult = wkTblKanrenshaKigyouDtMasterResultRepository
                .findAll();
        assertEquals(1, listResult.size());
        assertEquals(entity00.getWkTblKanrenshaKigyouDtMasterId(),
                listResult.get(0).getWkTblKanrenshaKigyouDtMasterId());
    }

    private StepExecution getStepExecution() {
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        JobParameters jobParameters = new JobParametersBuilder().addLong("userId", (long) userDto.getUserPersonId())
                .addLong("userCode", (long) userDto.getUserPersonCode())
                .addString("userName", userDto.getUserPersonName()).toJobParameters();
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

}
