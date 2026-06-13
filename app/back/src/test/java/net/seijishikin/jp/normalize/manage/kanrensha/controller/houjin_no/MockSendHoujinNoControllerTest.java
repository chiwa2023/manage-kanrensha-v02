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

    /** 接続先 */
    private static final String ACCESS_URL = "/4/name";

    /** パラメータキー(type) */
    private static final String PARAM_KEY_TYPE = "type";

    /** パラメータキー(name) */
    private static final String PARAM_KEY_NAME = "name";

    /** パラメータ(testId) */
    private static final String PARAM_TEST_ID = "testId";

    /** ContentType */
    private static final String CONTENT_TYPE = "text/csv;charset=UTF-8";

    /**
     * 「あ」を含む名称での取得テスト
     * 
     * @throws Exception 例外
     */
    @Test
    @WithMockUser
    void testGetHoujinNo_ContainsA() throws Exception {
        mockMvc.perform(get(ACCESS_URL).param("id", PARAM_TEST_ID).param(PARAM_KEY_NAME, "あいうえお").param(PARAM_KEY_TYPE, "12"))
                .andExpect(status().isOk()).andExpect(content().contentType(CONTENT_TYPE))
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
        mockMvc.perform(get(ACCESS_URL).param("id", PARAM_TEST_ID).param(PARAM_KEY_NAME, "い").param(PARAM_KEY_TYPE, "12"))
                .andExpect(status().isOk()).andExpect(content().contentType(CONTENT_TYPE))
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
        mockMvc.perform(get(ACCESS_URL).param("id", PARAM_TEST_ID).param(PARAM_KEY_NAME, "う").param(PARAM_KEY_TYPE, "12"))
                .andExpect(status().isOk()).andExpect(content().contentType(CONTENT_TYPE))
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
        mockMvc.perform(get(ACCESS_URL).param("id", PARAM_TEST_ID).param(PARAM_KEY_NAME, "さしすせそ").param(PARAM_KEY_TYPE, "12"))
                .andExpect(status().isOk()).andExpect(content().contentType(CONTENT_TYPE))
                .andExpect(content().string("\"test0\",\"dummy0\"\r\n"));
    }

    /**
     * その他（あいうを含まない）名称での取得テスト
     * 
     * @throws Exception 例外
     */
    @Test
    @WithMockUser
    void test403() throws Exception {
        mockMvc.perform(get(ACCESS_URL).param("id", PARAM_TEST_ID).param(PARAM_KEY_NAME, "か").param(PARAM_KEY_TYPE, "12"))
                .andExpect(status().isForbidden());
    }

    /**
     * その他（あいうを含まない）名称での取得テスト
     * 
     * @throws Exception 例外
     */
    @Test
    @WithMockUser
    void test404() throws Exception {
        mockMvc.perform(get(ACCESS_URL).param("id", PARAM_TEST_ID).param(PARAM_KEY_NAME, "きつつき").param(PARAM_KEY_TYPE, "12"))
                .andExpect(status().isNotFound());
    }

    /**
     * その他（あいうを含まない）名称での取得テスト
     * 
     * @throws Exception 例外
     */
    @Test
    @WithMockUser
    void test500() throws Exception {
        mockMvc.perform(get(ACCESS_URL).param("id", PARAM_TEST_ID).param(PARAM_KEY_NAME, "くじゃく").param(PARAM_KEY_TYPE, "12"))
                .andExpect(status().isInternalServerError());
    }

}
