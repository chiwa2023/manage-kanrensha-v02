package net.seijishikin.jp.normalize.manage.kanrensha.service.address_rsdt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.hibernate.exception.SQLGrammarException;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.address_rsdt.SearchAddressRsdtCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.address_rsdt.SearchAddressRsdtResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressRsdtTemplateEntity;

/**
 * SearchAddressRsdtService1単体テスト
 */
@SpringJUnitConfig
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("SearchAddressRsdtServiceTest.sql")
class SearchAddressRsdtServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private SearchAddressRsdtService searchAddressRsdtService;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        // 存在しないテーブルを指定した場合
        assertThrows(SQLGrammarException.class,
                () -> searchAddressRsdtService.practice(new SearchAddressRsdtCapsuleDto()));

        final String lgCode = "131016";

        SearchAddressRsdtCapsuleDto capsuleDto0 = new SearchAddressRsdtCapsuleDto();
        capsuleDto0.setLimit(20);
        capsuleDto0.setAllCount(39);
        capsuleDto0.setPageNumber(7);
        capsuleDto0.setSearchLgCode(lgCode);
        capsuleDto0.setSearchNaturalWords("名もなき道");

        SearchAddressRsdtResultDto resultDto0 = searchAddressRsdtService.practice(capsuleDto0);

        assertEquals(0, resultDto0.getAllCount());
        assertEquals(capsuleDto0.getLimit(), resultDto0.getLimit());
        assertTrue(resultDto0.getListEntity().isEmpty());

        // データ取得
        SearchAddressRsdtCapsuleDto capsuleDto1 = new SearchAddressRsdtCapsuleDto();
        capsuleDto1.setLimit(20);
        capsuleDto1.setAllCount(39);
        capsuleDto1.setPageNumber(7);
        capsuleDto1.setSearchLgCode(lgCode);
        capsuleDto1.setSearchNaturalWords("丁目");

        SearchAddressRsdtResultDto resultDto1 = searchAddressRsdtService.practice(capsuleDto1);
        assertEquals(3, resultDto1.getAllCount());
        assertEquals(capsuleDto1.getLimit(), resultDto1.getLimit());
        assertEquals(0, resultDto1.getPageNumber());

        List<AddressRsdtTemplateEntity> listAns1 = resultDto1.getListEntity();

        assertEquals(3, listAns1.size());
        assertEquals(623, listAns1.get(0).getAddressRsdtId());
        assertEquals(624, listAns1.get(1).getAddressRsdtId());
        assertEquals(625, listAns1.get(2).getAddressRsdtId());
    }
}
