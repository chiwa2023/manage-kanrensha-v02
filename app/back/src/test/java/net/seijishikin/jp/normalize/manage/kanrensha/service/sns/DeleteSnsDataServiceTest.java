package net.seijishikin.jp.normalize.manage.kanrensha.service.sns;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.sns.EditSnsServiceCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.SnsServiceEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.SnsServiceRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * DeleteSnsDataService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("DeleteSnsDataServiceTest.sql")
@Transactional
class DeleteSnsDataServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private DeleteSnsDataService deleteSnsDataService;

    /** SNSサービスRepository */
    @Autowired
    private SnsServiceRepository snsServiceRepository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        EditSnsServiceCapsuleDto capsuleDto = new EditSnsServiceCapsuleDto();
        capsuleDto.setUserDto(CreateLeastUserForTestUtil.practice());
        final Integer deleteId = 496;
        capsuleDto.setSnsServiceEntity(snsServiceRepository.findById(deleteId).get());

        deleteSnsDataService.practice(capsuleDto);

        SnsServiceEntity entityDelete = snsServiceRepository.findById(deleteId).get();
        assertFalse(entityDelete.getIsLatest());
        assertEquals(2L, snsServiceRepository.count()); // 新規に追加されたデータはない

        EditSnsServiceCapsuleDto capsuleDtoException = new EditSnsServiceCapsuleDto();
        SnsServiceEntity entityNotLoad = new SnsServiceEntity();
        entityNotLoad.setSnsServiceId(124); // 存在しないId
        capsuleDtoException.setSnsServiceEntity(entityNotLoad);

        assertThrows(EmptyResultDataAccessException.class, () -> deleteSnsDataService.practice(capsuleDtoException));
    }

}
