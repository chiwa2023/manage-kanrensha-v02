package net.seijishikin.jp.normalize.manage.kanrensha.controller.houjin_no;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

/**
 * MockSendHoujinNoControllerのテスト
 */
@SpringBootTest
@AutoConfigureMockMvc
class MockSendHoujinNoControllerTest {

    /** MockMvc */
    @Autowired
    private MockMvc mockMvc;

    /**
     * 「あ」を含む名称での取得テスト
     * 
     * @throws Exception 例外
     */
    @Test
    @WithMockUser
    void testGetHoujinNo_ContainsA() throws Exception {
        mockMvc.perform(get("/4/name")
                .param("id", "testId")
                .param("name", "あいうえお")
                .param("type", "12"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/csv;charset=UTF-8"))
                .andExpect(content().string("\"test1\",\"dummy1\"\r\n"));
    }

    /**
     * 「い」を含む名称での取得テスト
     * 
     * @throws Exception 例外
     */
    @Test
    @WithMockUser
    void testGetHoujinNo_ContainsI() throws Exception {
        mockMvc.perform(get("/4/name")
                .param("id", "testId")
                .param("name", "い")
                .param("type", "12"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/csv;charset=UTF-8"))
                .andExpect(content().string("\"test2\",\"dummy2\"\r\n"));
    }

    /**
     * 「う」を含む名称での取得テスト
     * 
     * @throws Exception 例外
     */
    @Test
    @WithMockUser
    void testGetHoujinNo_ContainsU() throws Exception {
        mockMvc.perform(get("/4/name")
                .param("id", "testId")
                .param("name", "う")
                .param("type", "12"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/csv;charset=UTF-8"))
                .andExpect(content().string("\"test3\",\"dummy3\"\r\n"));
    }

    /**
     * その他（あいうを含まない）名称での取得テスト
     * 
     * @throws Exception 例外
     */
    @Test
    @WithMockUser
    void testGetHoujinNo_Other() throws Exception {
        mockMvc.perform(get("/4/name")
                .param("id", "testId")
                .param("name", "かきくけこ")
                .param("type", "12"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/csv;charset=UTF-8"))
                .andExpect(content().string("\"test0\",\"dummy0\"\r\n"));
    }
}
