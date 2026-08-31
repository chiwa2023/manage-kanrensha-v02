package net.seijishikin.jp.normalize.manage.kanrensha.service.riyousha;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha.AcceptRiyoushaCombineCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.RiyoushaCombineOrgEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.RiyoushaCombineOrgTempEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.year.y2026.TaskPlan2026Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.RiyoushaCombineOrgRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.RiyoushaCombineOrgTempRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2026.TaskPlan2026Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * AcceptRiyoushaOrgCombineService単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("AcceptRiyoushaOrgCombineServiceTest.sql")
class AcceptRiyoushaOrgCombineServiceTest {
    // CHECKSTYLE:OFF MAgicNumber

    /** テスト対象 */
    @Autowired
    private AcceptRiyoushaOrgCombineService acceptRiyoushaOrgCombineService;

    /** 利用者組織紐づけ個人Respoitory */
    @Autowired
    private RiyoushaCombineOrgTempRepository riyoushaCombineOrgTempRepository;

    /** 利用者組織紐づけ個人Respoitory */
    @Autowired
    private RiyoushaCombineOrgRepository riyoushaCombineOrgRepository;

    /** タスク計画Respoitory(2026) */
    @Autowired
    private TaskPlan2026Repository taskPlan2026Repository;

    @Test
    @Tag("TableTruncate")
    void test() {

        AcceptRiyoushaCombineCapsuleDto capsuleDto0 = new AcceptRiyoushaCombineCapsuleDto();
        capsuleDto0.setUserDto(CreateLeastUserForTestUtil.practice());
        capsuleDto0.setOrgTempId(2983);

        // 仮テーブルから元登録が取得できない
        LocalDateTime endDatetime = LocalDateTime.of(2026, 4, 11, 0, 1, 2);
        assertThrows(EmptyResultDataAccessException.class, ()-> acceptRiyoushaOrgCombineService.practice(capsuleDto0, endDatetime));

        // タスク登録もない、承諾もないときは仮テーブルを履歴にして終了
        AcceptRiyoushaCombineCapsuleDto capsuleDto1 = new AcceptRiyoushaCombineCapsuleDto();
        capsuleDto1.setUserDto(CreateLeastUserForTestUtil.practice());
        final int orgTempId1 = 755;
        capsuleDto1.setOrgTempId(orgTempId1);
        capsuleDto1.setIsAsscept(false);
        FrameworkMessageAndResultDto resultDto1 = acceptRiyoushaOrgCombineService.practice(capsuleDto1, endDatetime);
        assertFalse(resultDto1.getIsFailure());
        RiyoushaCombineOrgTempEntity tempEntity1 = riyoushaCombineOrgTempRepository.findById(orgTempId1).get();
        assertFalse(tempEntity1.getIsLatest());

        // タスクがなく承諾した場合、仮テーブルを履歴にし、本テーブルに仮テーブルの内容を複写
        AcceptRiyoushaCombineCapsuleDto capsuleDto2 = new AcceptRiyoushaCombineCapsuleDto();
        final int orgTempId2 = 756;
        capsuleDto2.setOrgTempId(orgTempId2);
        capsuleDto2.setIsAsscept(true);
        FrameworkMessageAndResultDto resultDto2 = acceptRiyoushaOrgCombineService.practice(capsuleDto2, endDatetime);
        assertFalse(resultDto2.getIsFailure());
        RiyoushaCombineOrgTempEntity tempEntity2 = riyoushaCombineOrgTempRepository.findById(orgTempId2).get();
        assertFalse(tempEntity2.getIsLatest());
        RiyoushaCombineOrgEntity ansEntity2 = riyoushaCombineOrgRepository.findAll().getLast();
        assertEquals(tempEntity2.getOrgName(), ansEntity2.getOrgName());
        assertEquals(tempEntity2.getOrgRiyoushaCode(), ansEntity2.getOrgRiyoushaCode());
        assertEquals(tempEntity2.getPersonRiyoushaCode(), ansEntity2.getPersonRiyoushaCode());
        assertEquals(tempEntity2.getPersonRiyoushaName(), ansEntity2.getPersonRiyoushaName());
        assertEquals(tempEntity2.getPersonCode(), ansEntity2.getPersonCode());
        assertEquals(tempEntity2.getRiyoushaRole(), ansEntity2.getRiyoushaRole());

        // 承諾した場合、仮テーブルを履歴にし、本テーブルに仮テーブルの内容を複写、タスクの指定があれば正常終了とする
        AcceptRiyoushaCombineCapsuleDto capsuleDto3 = new AcceptRiyoushaCombineCapsuleDto();
        final int orgTempId3 = 757;
        capsuleDto3.setUserDto(CreateLeastUserForTestUtil.practice());
        capsuleDto3.setOrgTempId(orgTempId3);
        capsuleDto3.setIsAsscept(true);
        capsuleDto3.setTaskYear(2026);
        capsuleDto3.setTaskPlanId(426);
        FrameworkMessageAndResultDto resultDto3 = acceptRiyoushaOrgCombineService.practice(capsuleDto3, endDatetime);
        assertFalse(resultDto3.getIsFailure());
        RiyoushaCombineOrgTempEntity tempEntity3 = riyoushaCombineOrgTempRepository.findById(orgTempId3).get();
        assertFalse(tempEntity3.getIsLatest());
        RiyoushaCombineOrgEntity ansEntity3 = riyoushaCombineOrgRepository.findAll().getLast();
        assertEquals(tempEntity3.getOrgName(), ansEntity3.getOrgName());
        assertEquals(tempEntity3.getOrgRiyoushaCode(), ansEntity3.getOrgRiyoushaCode());
        assertEquals(tempEntity3.getPersonRiyoushaCode(), ansEntity3.getPersonRiyoushaCode());
        assertEquals(tempEntity3.getPersonRiyoushaName(), ansEntity3.getPersonRiyoushaName());
        assertEquals(tempEntity3.getPersonCode(), ansEntity3.getPersonCode());
        assertEquals(tempEntity3.getRiyoushaRole(), ansEntity3.getRiyoushaRole());
        TaskPlan2026Entity planEntityOld = taskPlan2026Repository.findById(capsuleDto3.getTaskPlanId()).get();
        assertEquals(false, planEntityOld.getIsFinished());
        assertEquals(false, planEntityOld.getIsLatest());
        TaskPlan2026Entity planEntityNew = taskPlan2026Repository.findAll().getLast();
        assertEquals(true, planEntityNew.getIsLatest());
        assertEquals(true, planEntityNew.getIsFinished());
        assertEquals(endDatetime, planEntityNew.getEndDateimte());
    }

}
