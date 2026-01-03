package net.seijishikin.jp.normalize.manage.kanrensha.controller.user;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.sequrity.JwtTokenDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.sequrity.LoginUserResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.user.NewComerDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.UserPersonEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.UserPersonRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.service.security.AddUserService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.security.JwtService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;
import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;

/**
 * ユーザ追加Controller
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/add-user")
public class AddUserController {

    /** 認証Manager */
    @Autowired
    private AuthenticationManager authenticationManager;

    /** JWTトークン生成Service */
    @Autowired
    private JwtService jwtService;

    /** ユーザRepository */
    @Autowired
    private UserPersonRepository userPersonRepository;

    /** ユーザ追加サービス */
    @Autowired
    private AddUserService addUserService;

    /** StackTrace保存Service */
    @Autowired
    private SaveStackTraceService saveStackTraceService;

    /**
     * 処理を行う
     *
     * @param newComerDto 新規ユーザDto
     * @return ログインDto
     */
    @PostMapping("/user")
    public ResponseEntity<LoginUserResultDto> practice(final @RequestBody NewComerDto newComerDto) {
        NewComerDto responseDto = addUserService.practice(newComerDto);

        try {
            // ログイン処理
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(responseDto.getMailAddress(), responseDto.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // トークン生成処理
            JwtTokenDto jwtToken = jwtService.generateToken(authentication);

            LoginUserResultDto resultDto = new LoginUserResultDto();
            resultDto.setJwtTokenDto(jwtToken);

            // ユーザ情報は別途取得する
            Optional<UserPersonEntity> optional = userPersonRepository.findLatestByMail(responseDto.getMailAddress());
            LeastUserDto personDto = new LeastUserDto();
            if (!optional.isEmpty()) {
                BeanUtils.copyProperties(optional.get(), personDto);
            }

            List<String> listAuh = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());
            personDto.setListRoles(listAuh);

            resultDto.setUserDto(personDto);

            return ResponseEntity.status(HttpStatus.OK).body(resultDto);

        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (Exception exception) { // NOPMD 業務的な理由から積極的に許容
            // exceptionが出所不明のためstack traceを取る
            LocalDate now = LocalDate.now();
            saveStackTraceService.practice(exception, now.getYear(), 0);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
