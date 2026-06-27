package net.seijishikin.jp.normalize.manage.kanrensha.controller.yotei;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.seijishikin.jp.normalize.common_tool.utils.GetObjectMapperWithTimeModuleUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.yotei.EditTimerYoteiCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.TimerYoteiEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.TimerYoteiRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * UpdateTimerYoteiController単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("../../service/yotei/UpdateTimerYoteiServiceTest.sql")
@Transactional
class UpdateTimerYoteiControllerTest {
    // CHECKSTYLE:OFF MagicNumber

    /** WebApplicationContext */
    @Autowired
    private WebApplicationContext context;

    /** MockMvc */
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context) //
                .apply(SecurityMockMvcConfigurers.springSecurity()).build();
    }

    /** 予約実行Repository */
    @Autowired
    private TimerYoteiRepository timerYoteiRepository;

    @Test
    @Tag("TableTruncate")
    @WithMockUser
    void test() throws Exception {

        EditTimerYoteiCapsuleDto capsuleDto1 = new EditTimerYoteiCapsuleDto();
        capsuleDto1.setUserDto(CreateLeastUserForTestUtil.practice());
        final Integer callId = 123;
        TimerYoteiEntity updateEntity = timerYoteiRepository.findById(callId).get();

        updateEntity.setYoyakuTaskKbn((short) 9292);
        updateEntity.setTimerYoteiName("中断タスク");
        updateEntity.setIsPause(false);
        updateEntity.setIsPeriod(false);
        updateEntity.setIsRepeat(false);
        final LocalDateTime time0 = LocalDateTime.of(1998, 1, 2, 3, 4, 5);
        updateEntity.setEndTimestamp(time0);
        final LocalDateTime time1 = LocalDateTime.of(1998, 2, 3, 4, 5, 6);
        updateEntity.setSabunTimestamp(time1);
        final LocalDateTime time2 = LocalDateTime.of(1998, 2, 3, 4, 5, 6);
        updateEntity.setPreviousTimestamp(time2);
        final LocalDateTime time3 = LocalDateTime.of(1998, 3, 4, 5, 6, 7);
        updateEntity.setNextTimestamp(time3);

        updateEntity.setYearPeriod(301);
        updateEntity.setMonthPeriod(302);
        updateEntity.setDayPeriod(303);
        updateEntity.setHourPeriod(304);
        updateEntity.setYearPointed(305);
        updateEntity.setMonthPointed(306);
        updateEntity.setDayPointed(307);
        updateEntity.setHourPointed(308);

        capsuleDto1.setTimerYoteiEntity(updateEntity);

        String path = PathRouteConstants.ROOT + "/timer-yotei/update";

        ObjectMapper objectMapper = GetObjectMapperWithTimeModuleUtil.practice();

        assertEquals(HttpStatus.OK.value(), mockMvc // NOPMD LawOfDemeter
                .perform(post(path).content(objectMapper.writeValueAsString(capsuleDto1)) //
                        .contentType(MediaType.APPLICATION_JSON_VALUE)) //
                .andExpect(status().isOk()).andReturn().getResponse().getStatus());
    }

}
