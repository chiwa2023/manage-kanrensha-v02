package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.person.add_std; // NOPMD ExcessiveImport

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonAccessEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonAddressEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonPropertyEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaPersonMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaPersonMasterResultEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaPersonHistory01Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaPersonAccessRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaPersonAddressRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaPersonMasterRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaPersonPropertyRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaPersonMasterResultRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaPersonHistory01Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * MasterPersonAddStdRecordItemWriter単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("MasterPersonAddStdRecordItemWriterTest.sql")
class MasterPersonAddStdRecordItemWriterTest {

    /** テスト対象 */
    @Autowired
    private MasterPersonAddStdRecordItemWriter masterPersonAddStdRecordItemWriter;

    /** 関連者個人マスタ標準判定結果Repository */
    @Autowired
    private WkTblKanrenshaPersonMasterResultRepository wkTblKanrenshaPersonMasterResultRepository;

    /** 関連者個人マスタRepository */
    @Autowired
    private KanrenshaPersonMasterRepository kanrenshaPersonMasterRepository;

    /** 関連者個人マスタ住所Repository */
    @Autowired
    private KanrenshaPersonAddressRepository kanrenshaPersonAddressRepository;

    /** 関連者個人マスタ住所Repository */
    @Autowired
    private KanrenshaPersonAccessRepository kanrenshaPersonAccessRepository;

    /** 関連者個人マスタ(その他属性)Repository */
    @Autowired
    private KanrenshaPersonPropertyRepository kanrenshaPersonPropertyRepository;

    /** 関連者個人マスタ履歴Repository(01) */
    @Autowired
    private KanrenshaPersonHistory01Repository kanrenshaPersonHistory01Repository;

    @Test
    @Tag("TableTruncate")
    void test() {
        // CHECKSTYLE:OFF MagicNumber

        WkTblKanrenshaPersonMasterEntity entity00 = new WkTblKanrenshaPersonMasterEntity();
        entity00.setWkTblKanrenshaPersonMasterId(323);
        entity00.setKanrenshaName("迂回献金　太郎");
        entity00.setAllAddress("北海道架空市湖畔町");
        entity00.setPersonShokugyou("団体役員");

        entity00.setAddressPostal("郵便番号住所");
        entity00.setAddressBlock("番地住所");
        entity00.setAddressBuilding("建物住所");
        entity00.setPostalcode1("987");
        entity00.setPostalcode2("6543");
        entity00.setLgCode("012");
        entity00.setMachiazaId("123");
        entity00.setBlkId("234");
        entity00.setPrcId("234");
        entity00.setRsdtId("345");
        entity00.setRsdt2Id("678");

        entity00.setLastName("迂回献金");
        entity00.setFirstName("太郎");
        entity00.setMiddleName("ミカエル");
        entity00.setLastNameKana("たろう");
        entity00.setFirstNameKana("うかいけんきん");
        entity00.setMiddleNameKana("みかえる");
        entity00.setGyoushu("水産業");
        entity00.setYakushoku("団体役員");
        entity00.setShokugyouUserWrite("正義の味方");
        entity00.setKigyouDtNo("1-2345");
        entity00.setKigyouDtAddress("和歌山県実在市山麓町");
        entity00.setKigyouDtName("超元素製造組合");

        entity00.setEmail("aaa@bbbb.net");
        entity00.setIsForeign(true);
        entity00.setMyPortalUrl("https://bbb.net/");
        entity00.setPhon1("567");
        entity00.setPhon2("8901");
        entity00.setPhon3("2345");
        entity00.setSnsAccount("@abcds");
        entity00.setSnsServiceName("弱小ブログ");

        entity00.setIsForeign(false);

        entity00.setIsAffected(true);
        entity00.setIsFinish(false);
        entity00.setJudgeReason("");

        List<WkTblKanrenshaPersonMasterEntity> listLoad = new ArrayList<>();
        listLoad.add(entity00);

        // Chunkを作成してセット
        Chunk<? extends WkTblKanrenshaPersonMasterEntity> items = new Chunk<>(listLoad);

        masterPersonAddStdRecordItemWriter.beforeStep(this.getStepExecution());
        masterPersonAddStdRecordItemWriter.write(items);

        // 履歴テーブル本体に正常登録
        List<KanrenshaPersonHistory01Entity> listHistory = kanrenshaPersonHistory01Repository.findAll();
        assertEquals(1, listHistory.size());
        KanrenshaPersonHistory01Entity entity10 = listHistory.get(0);
        assertEquals(entity00.getKanrenshaName(), entity10.getAllName());
        assertEquals(entity00.getAllAddress(), entity10.getAllAddress());
        assertEquals(entity00.getPersonShokugyou(), entity10.getPersonShokugyou());
        assertNotEquals("", entity10.getPersonKanrenshaCode());
        assertEquals("迂回献金太郎北海道架空市湖畔町団体役員", entity10.getSearchText());

        // マスタ本体に正常登録
        List<KanrenshaPersonMasterEntity> listMaster = kanrenshaPersonMasterRepository.findAll();
        assertEquals(1, listMaster.size());
        KanrenshaPersonMasterEntity entity11 = listMaster.get(0);
        assertEquals(entity00.getKanrenshaName(), entity11.getKanrenshaName());
        assertEquals(entity00.getAllAddress(), entity11.getAllAddress());
        assertEquals(entity00.getPersonShokugyou(), entity11.getPersonShokugyou());
        assertEquals("迂回献金太郎", entity11.getCompareNameText());
        assertEquals(entity10.getPersonKanrenshaCode(), entity11.getPersonKanrenshaCode());

        // マスタ住所に正常登録
        List<KanrenshaPersonAddressEntity> listAddress = kanrenshaPersonAddressRepository.findAll();
        assertEquals(1, listAddress.size());
        KanrenshaPersonAddressEntity entity12 = listAddress.get(0);
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
        List<KanrenshaPersonAccessEntity> listAccess = kanrenshaPersonAccessRepository.findAll();
        assertEquals(1, listAccess.size());
        KanrenshaPersonAccessEntity entity13 = listAccess.get(0);
        assertEquals(entity00.getPhon1(), entity13.getPhon1());
        assertEquals(entity00.getPhon2(), entity13.getPhon2());
        assertEquals(entity00.getPhon3(), entity13.getPhon3());
        assertEquals(entity00.getEmail(), entity13.getEmail());
        assertEquals(entity00.getMyPortalUrl(), entity13.getMyPortalUrl());
        assertEquals(entity00.getSnsServiceName(), entity13.getSnsServiceName());
        assertEquals(entity00.getSnsAccount(), entity13.getSnsAccount());

        // マスタ属性に正常登録
        List<KanrenshaPersonPropertyEntity> listProperty = kanrenshaPersonPropertyRepository.findAll();
        assertEquals(1, listProperty.size());
        KanrenshaPersonPropertyEntity entity15 = listProperty.get(0);

        assertEquals(entity00.getLastName(), entity15.getLastName());
        assertEquals(entity00.getFirstName(), entity15.getFirstName());
        assertEquals(entity00.getMiddleName(), entity15.getMiddleName());
        assertEquals(entity00.getLastNameKana(), entity15.getLastNameKana());
        assertEquals(entity00.getFirstNameKana(), entity15.getFirstNameKana());
        assertEquals(entity00.getMiddleNameKana(), entity15.getMiddleNameKana());
        assertEquals(entity00.getGyoushu(), entity15.getGyoushu());
        assertEquals(entity00.getYakushoku(), entity15.getYakushoku());
        assertEquals(entity00.getShokugyouUserWrite(), entity15.getShokugyouUserWrite());
        assertEquals(entity00.getKigyouDtNo(), entity15.getKigyouDtNo());
        assertEquals(entity00.getKigyouDtAddress(), entity15.getKigyouDtAddress());
        assertEquals(entity00.getKigyouDtName(), entity15.getKigyouDtName());
        assertEquals(true, entity15.getIsShokyouEdit());
        assertEquals(false, entity15.getIsShokyouAccept());

        assertEquals(entity00.getIsForeign(), entity15.getIsForeign());

        // 処理結果に正常登録
        List<WkTblKanrenshaPersonMasterResultEntity> listResult = wkTblKanrenshaPersonMasterResultRepository.findAll();
        assertEquals(1, listResult.size());
        assertEquals(entity00.getWkTblKanrenshaPersonMasterId(), listResult.get(0).getWkTblKanrenshaPersonMasterId());
    }

    private StepExecution getStepExecution() {
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        JobParameters jobParameters = new JobParametersBuilder().addLong("userId", (long) userDto.getUserPersonId())
                .addLong("userCode", (long) userDto.getUserPersonCode())
                .addString("userName", userDto.getUserPersonName()).toJobParameters();
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

}
