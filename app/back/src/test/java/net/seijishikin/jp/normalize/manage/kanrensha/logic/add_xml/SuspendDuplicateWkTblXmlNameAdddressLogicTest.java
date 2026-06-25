package net.seijishikin.jp.normalize.manage.kanrensha.logic.add_xml;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblMasterAllByXmlEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblMasterAllByXmlRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;


/**
 * SuspendDuplicateWkTblXmlNameAdddressLogic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("SuspendDuplicateWkTblXmlNameAdddressLogicTest.sql")
@Transactional
class SuspendDuplicateWkTblXmlNameAdddressLogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private SuspendDuplicateWkTblXmlNameAdddressLogic suspendDuplicateWkTblXmlNameAdddressLogic;

    /** XMLから最小マスタ登録ワークテーブルRepository */
    @Autowired
    private WkTblMasterAllByXmlRepository wkTblMasterAllByXmlRepository;

    /** 判定理由 */
    private static final String REASON = "アップロードファイル内で重複しているデータです";

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        suspendDuplicateWkTblXmlNameAdddressLogic.practice(CreateLeastUserForTestUtil.practice());

        // 処理内容変更
        WkTblMasterAllByXmlEntity entity00 = wkTblMasterAllByXmlRepository.findById(240).get();
        assertEquals(false, entity00.getIsLatest());
        assertEquals(false, entity00.getIsAffected());
        assertEquals(true, entity00.getIsFinish());
        assertEquals(true, entity00.getIsDisabled());
        assertEquals(REASON, entity00.getJudgeReason());

        WkTblMasterAllByXmlEntity entity01 = wkTblMasterAllByXmlRepository.findById(241).get();
        assertEquals(false, entity01.getIsLatest());
        assertEquals(false, entity01.getIsAffected());
        assertEquals(true, entity01.getIsFinish());
        assertEquals(true, entity01.getIsDisabled());
        assertEquals(REASON, entity01.getJudgeReason());

        WkTblMasterAllByXmlEntity entity02 = wkTblMasterAllByXmlRepository.findById(242).get();
        assertEquals(false, entity02.getIsLatest());
        assertEquals(false, entity02.getIsAffected());
        assertEquals(true, entity02.getIsFinish());
        assertEquals(true, entity02.getIsDisabled());
        assertEquals(REASON, entity02.getJudgeReason());

        // 無用に他領域のデータは触らない
        WkTblMasterAllByXmlEntity entity10 = wkTblMasterAllByXmlRepository.findById(140).get();
        assertEquals(true, entity10.getIsLatest());
        assertEquals(false, entity10.getIsFinish());
        assertEquals(false, entity10.getIsDisabled());

        WkTblMasterAllByXmlEntity entity20 = wkTblMasterAllByXmlRepository.findById(340).get();
        assertEquals(true, entity20.getIsLatest());
        assertEquals(false, entity20.getIsFinish());
        assertEquals(false, entity20.getIsDisabled());

    }

}
