package net.seijishikin.jp.normalize.manage.kanrensha.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * SampleTableRepository単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class SampleTableRepositoryTest {

    /** テスト対象 */
    @Autowired
    private SampleTableRepository sampleTableRepository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        // DDL通りテーブルが作成されていれば紐づいたテーブル名が取得できる
        List<String> list = sampleTableRepository.findMyself();
        assertEquals(1, list.size());
        assertEquals("sample_table", list.get(0));
    }

}
