package net.seijishikin.jp.normalize.manage.kanrensha.logic.add_xml;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblMasterAllByXmlEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaKigyouDtAddMinRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaPersonAddMinRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaSeijidantaiAddMinRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblMasterAllByXmlRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * MoveWktblXmlToMasterMinLogic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("MoveWktblXmlToMasterMinLogicTest.sql")
class MoveWktblXmlToMasterMinLogicTest {

    /** テスト対象 */
    @Autowired
    private MoveWktblXmlToMasterMinLogic moveWktblXmlToMasterMinLogic;

    /** XMLから最小マスタ登録ワークテーブルRepository */
    @Autowired
    private WkTblMasterAllByXmlRepository wkTblMasterAllByXmlRepository;

    /** 個人最小マスタ登録ワークテーブルRepository */
    @Autowired
    private WkTblKanrenshaPersonAddMinRepository wkTblKanrenshaPersonAddMinRepository;

    /** 企業団体最小マスタ登録ワークテーブルRepository */
    @Autowired
    private WkTblKanrenshaKigyouDtAddMinRepository wkTblKanrenshaKigyouDtAddMinRepository;

    /** 政治団体最小マスタ登録ワークテーブルRepository */
    @Autowired
    private WkTblKanrenshaSeijidantaiAddMinRepository wkTblKanrenshaSeijidantaiAddMinRepository;

    /** 重複データ編集 */
    @Autowired
    private SuspendDuplicateWkTblXmlDecideKanrenshaLogic suspendDuplicateWkTblXmlDecideKanrenshaLogic;

    /** 判定理由(移管) */
    private static final String REASON = "最小マスタへ移動済;";

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {
        // CHECKSTYLE:OFF

        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        // サンプルデータに重複があるので先に編集中断(触るのは関連者区分決定データだけなので、該当データだけ処理)
        suspendDuplicateWkTblXmlDecideKanrenshaLogic.practice(userDto);

        moveWktblXmlToMasterMinLogic.practce(userDto);

        WkTblMasterAllByXmlEntity entity01 = wkTblMasterAllByXmlRepository.findById(311).get();
        assertEquals(false, entity01.getIsLatest());
        assertEquals(true, entity01.getIsFinish());
        assertEquals(true, entity01.getIsAffected());
        assertEquals(REASON, entity01.getJudgeReason());

        WkTblMasterAllByXmlEntity entity02 = wkTblMasterAllByXmlRepository.findById(312).get();
        assertEquals(false, entity02.getIsLatest());
        assertEquals(true, entity02.getIsFinish());
        assertEquals(true, entity02.getIsAffected());
        assertEquals(REASON, entity02.getJudgeReason());

        WkTblMasterAllByXmlEntity entity03 = wkTblMasterAllByXmlRepository.findById(313).get();
        assertEquals(false, entity03.getIsLatest());
        assertEquals(true, entity03.getIsFinish());
        assertEquals(true, entity03.getIsAffected());
        assertEquals(REASON, entity03.getJudgeReason());

        WkTblMasterAllByXmlEntity entity04 = wkTblMasterAllByXmlRepository.findById(314).get();
        assertEquals(false, entity04.getIsLatest());
        assertEquals(true, entity04.getIsFinish());
        assertEquals(true, entity04.getIsAffected());
        assertEquals(REASON, entity04.getJudgeReason());

        WkTblMasterAllByXmlEntity entity05 = wkTblMasterAllByXmlRepository.findById(315).get();
        assertEquals(false, entity05.getIsLatest());
        assertEquals(true, entity05.getIsFinish());
        assertEquals(true, entity05.getIsAffected());
        assertEquals(REASON, entity05.getJudgeReason());

        // 各テーブルに移管成功(各パラメータの整合については別Logicでテスト)
        assertEquals(2, wkTblKanrenshaPersonAddMinRepository.count());
        assertEquals(2, wkTblKanrenshaKigyouDtAddMinRepository.count());
        assertEquals(1, wkTblKanrenshaSeijidantaiAddMinRepository.count());
    }

}
