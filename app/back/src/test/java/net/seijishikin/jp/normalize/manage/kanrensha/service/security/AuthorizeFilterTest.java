package net.seijishikin.jp.normalize.manage.kanrensha.service.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletMapping;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.JwtDecoder;

import jakarta.servlet.FilterChain;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.MappingMatch;
import net.seijishikin.jp.normalize.common_tool.utils.GetObjectMapperWithTimeModuleUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.sequrity.JwtTokenDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.sequrity.RefreshPasswordCapsuleDto;

/**
 * AuthorizeFilter単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class AuthorizeFilterTest {

    /** JwtDecoder */
    @Autowired
    private JwtDecoder jwtDecoder;

    /** JwtService */
    @Autowired
    private JwtService jwtService;

    @Test
    void testNoAuth() throws Exception {

        HttpServletRequest request = new MockHttpServletRequest();
        HttpServletResponse response = new MockHttpServletResponse();

        // ヘッダにX-AUTHが存在しないので、次のfilterChainに処理を渡して何もしない
        MockHttpServletMapping mapping = new MockHttpServletMapping("/login", "/login", "aaa", MappingMatch.PATH);
        request.setAttribute(RequestDispatcher.INCLUDE_MAPPING, mapping);

        FilterChain filterChain = new MockFilterChain();

        AuthorizeFilter authorizeFilter = new AuthorizeFilter(jwtDecoder);
        assertDoesNotThrow(() -> authorizeFilter.doFilterInternal(request, response, filterChain));
    }

    @Test
    void test() throws Exception {

        String userName = "aaa";
        List<String> listRole = new ArrayList<>();
        listRole.add("admin");
        JwtTokenDto jwtTokenDto = jwtService.generateToken(userName, listRole);

        final String path = "/edit-user/refresh-password";
        
        // TODO 仮でパスワード更新にしているが、他によいAPIがあれば入れ替える
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("X-AUTH-TOKEN", "Bearer " + jwtTokenDto.getAccessToken());
        MockHttpServletMapping mapping = new MockHttpServletMapping(path,
                path, userName, MappingMatch.PATH);
        request.setAttribute(RequestDispatcher.INCLUDE_MAPPING, mapping);
        RefreshPasswordCapsuleDto capsuleDto = new RefreshPasswordCapsuleDto();
        
        ObjectMapper objectMapper = GetObjectMapperWithTimeModuleUtil.practice();
        request.setContentType(MediaType.APPLICATION_JSON_VALUE);
        request.setContent(objectMapper.writeValueAsBytes(capsuleDto));

        FilterChain filterChain = new MockFilterChain();
        AuthorizeFilter authorizeFilter = new AuthorizeFilter(jwtDecoder);
        HttpServletResponse response = new MockHttpServletResponse();
        authorizeFilter.doFilterInternal(request, response, filterChain);

        // ログイン状態なのでログインユーザ名が取得できる
        assertEquals(userName, SecurityContextHolder.getContext().getAuthentication().getName());
    }

}
