package net.seijishikin.jp.normalize.manage.kanrensha.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.ViewCombineAliveRiyoushaEntity;

/**
 * ViewCombineAliveRiyoushaRepository単体テスト
 */
@SpringJUnitConfig
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
// @Transactional
@Sql("ViewCombineAliveRiyoushaRepositoryTest.sql")
class ViewCombineAliveRiyoushaRepositoryTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private ViewCombineAliveRiyoushaRepository viewCombineAliveRiyoushaRepository;

    @Test
    void test() {

        List<ViewCombineAliveRiyoushaEntity> list = viewCombineAliveRiyoushaRepository.findAll();

        assertEquals(5L, list.size());

        final String admin = "admin";
        final String manager = "manager";
        final String partner = "partner_api";

        ViewCombineAliveRiyoushaEntity entity0 = list.get(0);
        assertEquals("aaa@politician.balanse.report.net", entity0.getEmail());
        assertEquals(284, entity0.getRiyoushaId());
        assertEquals(manager, entity0.getRoleBase());
        assertEquals(manager, entity0.getRoleHas());
        assertEquals("管理者 マリア花子3", entity0.getAllName());
        assertEquals("999宮崎県実在市山麓町3丁目6の9星形ビル444管理者マリア花子かんりしゃまりあはなこ", entity0.getSearchText());

        ViewCombineAliveRiyoushaEntity entity1 = list.get(1);
        assertEquals(327, entity1.getRiyoushaId());
        assertEquals(partner, entity1.getRoleBase());
        assertEquals(admin, entity1.getRoleHas());
        ViewCombineAliveRiyoushaEntity entity2 = list.get(2);
        assertEquals(327, entity2.getRiyoushaId());
        assertEquals(partner, entity2.getRoleBase());
        assertEquals(manager, entity2.getRoleHas());

        ViewCombineAliveRiyoushaEntity entity3 = list.get(3);
        assertEquals(327, entity3.getRiyoushaId());
        assertEquals(partner, entity3.getRoleBase());
        assertEquals(partner, entity3.getRoleHas());

        ViewCombineAliveRiyoushaEntity entity4 = list.get(4);
        assertEquals(325, entity4.getRiyoushaId());
        assertEquals(partner, entity4.getRoleBase());
        assertEquals(partner, entity4.getRoleHas());

    }

}
