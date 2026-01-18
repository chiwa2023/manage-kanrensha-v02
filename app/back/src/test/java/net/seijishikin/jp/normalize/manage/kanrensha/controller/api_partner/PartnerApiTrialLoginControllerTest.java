package net.seijishikin.jp.normalize.manage.kanrensha.controller.api_partner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;

/**
 * PartnerApiTrialLoginController単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("PartnerApiTrialLoginControllerTest.sql")
class PartnerApiTrialLoginControllerTest {

    /** MockMvc */
    @Autowired
    private MockMvc mockMvc;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        // ログイン成功token
        String token = "eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiIxMjM0NSIsInJvbGUiOiJwYXJ0bmVyX2FwaSIsImV4cCI6MTc4MDYxNzYwMCwiaWF0IjoxNzY0ODkyODAwfQ.VoFSuPF27ajroEvLahAVJtKJ6HOpfHMoQBQaoOwD-B9dLvq6UVyLStUTnwq3n1CQ4aYv0QoQvoyCFb0UBBTjmxVV_auahofrUjkQ8KM-N1MCKQBuhCNO4EZ0CrFDjoHZR5znWP7A03rLshjTim-4xsySPK7B62epbPm1bi9yQw69IlQQUD5FiOeWyDo_AtBtUI00tD7LPZE1c2hTmimge0bpCbTZf4KAw6E6PeBQHIk9N_jX7oKctMyyogWXvysK7d2rpbrwyA4YIPMZIi3jxcqqa19FEdv39-SI_RxBnVMQ2E2JoqOPw8aVp__tF037r_JnuTv-afgJONf53AkxFA";

        HttpHeaders httpHeaders = new HttpHeaders(); // NOPMD
        httpHeaders.add("X-AUTH-TOKEN", "Bearer " + token);
        httpHeaders.add("User-Agent", "Netscape");
        httpHeaders.add("X-Forwarded-For", "127.0.0.1"); // NOPMD

        String path = PathRouteConstants.ROOT + "/api-for-partner/trial-login";

        // サーバステータスがOK(200)
        assertEquals(HttpStatus.OK.value(), mockMvc // NOPMD LawOfDemeter
                .perform(post(path)
                        // .content(objectMapper.writeValueAsString(capsuleDto))
                        // .contentType(MediaType.APPLICATION_JSON_VALUE))
                        .headers(httpHeaders))
                .andExpect(status().isOk()).andReturn().getResponse().getStatus());
    }

    @Test
    @Tag("TableTruncate")
    void testOverExpiredAt() throws Exception {

        // 失効token
        String token = "eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiI5ODc2NTQzIiwicm9sZSI6InBhcnRuZXJfYXBpIiwiZXhwIjoxNzgwODc1OTEyLCJpYXQiOjE3NjUxNTExMTJ9.LH1YEXdoQkWAE4CLzeQnzooibEpe7ZkLR5Ksm7koUPS3Jyjtf7JEuc8WWjcJIZmzZn4i7SoC1PvM-qtpp3EwmTF7aQ-l5V39oImNK3_0Mn2yF605NbHWgy07f0YXRDB3V-1TrbMEfknKYRDMQQf3r98Im30N28WYbEwZT93K7rI4Jtu1Mn4jAcKJS67XHBgKQsbx5JtHB2lljt8v_FJQRE-mqh0-Lk0l6owmivJBxTOC06M-rC_7L66N4aLvsX9pABZxQz6hDxdLhDqESm7ZZMFNgcp6jF11_MwVZ1LTpyY82X2jOsTO532b70SRnVAY5ztcXHb4VJH4tzIwOaj0gw";

        HttpHeaders httpHeaders = new HttpHeaders(); // NOPMD
        httpHeaders.add("X-AUTH-TOKEN", "Bearer " + token);
        httpHeaders.add("User-Agent", "Netscape");
        httpHeaders.add("X-Forwarded-For", "127.0.0.1"); // NOPMD

        String path = PathRouteConstants.ROOT + "/api-for-partner/trial-login";

        // サーバステータスがUnautorized(401)
        assertEquals(HttpStatus.UNAUTHORIZED.value(), mockMvc // NOPMD LawOfDemeter
                .perform(post(path)
                        // .content(objectMapper.writeValueAsString(capsuleDto))
                        // .contentType(MediaType.APPLICATION_JSON_VALUE))
                        .headers(httpHeaders))
                .andExpect(status().isUnauthorized()).andReturn().getResponse().getStatus());
    }

}
