package net.seijishikin.jp.normalize.manage.kanrensha.service.works_approval;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;

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

import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonPropertyEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaPersonMasterRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaPersonPropertyRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * SaveApprovalShokugyouService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("SaveApprovalShokugyouServiceTest.sql")
class SaveApprovalShokugyouServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private SaveApprovalShokugyouService saveApprovalShokugyouService;

    /** 関連者個人属性Repository */
    @Autowired
    private KanrenshaPersonPropertyRepository kanrenshaPersonPropertyRepository;

    /** 関連者個人属性Repository */
    @Autowired
    private KanrenshaPersonMasterRepository kanrenshaPersonMasteryRepository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        List<KanrenshaPersonPropertyEntity> listShokugyou = kanrenshaPersonPropertyRepository.findAll();

        final String newGyoushu = "小売";
        listShokugyou.get(0).setGyoushu(newGyoushu);
        // 画面上は変更できない姓名に関する事項を変更しても変更対象ではない
        final String notGyoushu = "職業";
        listShokugyou.get(1).setFirstName(notGyoushu);

        listShokugyou.get(2).setShokugyouUserWrite("素浪人");
        listShokugyou.get(2).setKigyouDtNo("9988");

        Integer affectedRow = saveApprovalShokugyouService.practice(listShokugyou,
                CreateLeastUserForTestUtil.practice());

        assertEquals(2, affectedRow, "追加行数");

        List<KanrenshaPersonPropertyEntity> listResult = kanrenshaPersonPropertyRepository.findAll();
        assertEquals(5, listResult.size());

        // 該当データは履歴に変更(変更前データが残っている)
        assertEquals(false, listResult.get(0).getIsLatest());
        assertNotEquals(newGyoushu, listResult.get(0).getGyoushu());

        // 該当でない場合はなにも変更されていない
        assertEquals(true, listResult.get(1).getIsLatest());
        assertNotEquals(notGyoushu, listResult.get(1).getFirstName());

        assertEquals(false, listResult.get(2).getIsLatest());

        // 最後の新規行は最新で指定された値が入っている
        assertEquals(true, listResult.get(3).getIsLatest());
        assertEquals(newGyoushu, listResult.get(3).getGyoushu());
        assertEquals(true, listResult.get(4).getIsLatest());
        assertEquals("素浪人", listResult.get(4).getShokugyouUserWrite());

        // 属性の最後の変更データはマスタに登録する個人職業が一致しているのでマスタの更新をしに行かない
        List<KanrenshaPersonMasterEntity> listMaster = kanrenshaPersonMasteryRepository.findAll();
        assertEquals(3, listMaster.size());
    }
}
