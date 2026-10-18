package net.seijishikin.jp.normalize.manage.kanrensha.controller.api_partner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

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
 * ConvertPartnerApiTokenToAccessTokenController単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("PartnerApiTrialLoginControllerTest.sql")
class ConvertPartnerApiTokenToAccessTokenControllerTest {

    /** MockMvc */
    @Autowired
    private MockMvc mockMvc;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        // ログイン成功token(実施前に有効期間はチェック)
        String token = "eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJwcHBwIiwicm9sZSI6InBhcnRuZXJfYXBpIiwiZXhwIjoxODA4MDUyNjIxLCJpYXQiOjE3OTIzMjc4MjF9.iRm7ZKAe9EzZZDLquQqwugZ1i5M_cjPS7zW_AXpUlkjE1S7XlQNgAIDd1YcnVANLfPJeouWpHh3M7CP8cdLr7zL8reyIeWNUsMje4WcQpxZHpyqWjlZ8tXW--K6KnvF5Pci5ZPGtYVQdMStVYdfCSvLAj-I9HWkCR_CMfWZQxq3wmxcxPpi5GqxhjvTkyve6BanttPzFeZzEyBb5qfonlxVM6FdqJGjVeFp_IWMf43D9ioMvPiliSipLMKt5-ojSK0BadC-slst4ooxgG7y5Sz1XV-yDPSlAqsvEuyYGA9zWL3jLe5wepbrkh4Vt17L3QusdmV1rSQZCYANKDFA6uw";

        HttpHeaders httpHeaders = new HttpHeaders(); // NOPMD
        httpHeaders.add("X-AUTH-TOKEN", "Bearer " + token);
        httpHeaders.add("User-Agent", "Netscape");
        httpHeaders.add("X-Forwarded-For", "127.0.0.1"); // NOPMD

        String path = PathRouteConstants.ROOT + "/api-for-partner/convert";

        // サーバステータスがOK(200)
        assertEquals(HttpStatus.OK.value(), mockMvc // NOPMD LawOfDemeter
                .perform(post(path)
                        // .content(objectMapper.writeValueAsString(capsuleDto))
                        // .contentType(MediaType.APPLICATION_JSON_VALUE))
                        .headers(httpHeaders))
                .andExpect(status().isOk()).andReturn().getResponse().getStatus());
    }

}
