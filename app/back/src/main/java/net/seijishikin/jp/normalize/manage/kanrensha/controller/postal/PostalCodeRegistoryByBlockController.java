package net.seijishikin.jp.normalize.manage.kanrensha.controller.postal;

import java.time.Year;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.netty.handler.codec.http.HttpResponseStatus;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.postal.AddressRsdtResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.postal.PostalCodeCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressRsdtTemplateEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.service.postal.GetPostalCodeRegistoryByBlockService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;

/**
 * 番地まで住所から詳細住所住居取得Service
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/postal-search")
public class PostalCodeRegistoryByBlockController {

    /** 番地まで住所から詳細住所住居取得Service */
    @Autowired
    private GetPostalCodeRegistoryByBlockService getPostalCodeRegistoryByBlockService;

    /** 例外記録Service */
    @Autowired
    private SaveStackTraceService saveStackTraceService;

    /**
     * 処理を行う
     * 
     * @param capsuleDto 検索条件Dto
     * @return レスポンス
     */
    @PostMapping("/rsdt-detail-block")
    public ResponseEntity<AddressRsdtResultDto> practice(final @RequestBody PostalCodeCapsuleDto capsuleDto) {

        try {
            AddressRsdtTemplateEntity templateEntity = getPostalCodeRegistoryByBlockService
                    .practice(capsuleDto.getLgCode(), capsuleDto.getSelectedBlock());
            AddressRsdtResultDto resultDto = new AddressRsdtResultDto();

            if (Objects.isNull(templateEntity)) {
                // 予測できる理由で取得できないときはエラーで落とさない
                resultDto.setIsFailure(true);
                resultDto.setMessage("正常に住居詳細が取得できませんでした");
                return ResponseEntity.status(HttpResponseStatus.ACCEPTED.code()).body(resultDto);
            } else {
                resultDto.setAddressRsdtEntity(templateEntity);
                return ResponseEntity.status(HttpResponseStatus.OK.code()).body(resultDto);
            }

        } catch (Exception exception) { // NOPMD 業務的な理由から積極的に許容
            saveStackTraceService.practice(exception, Year.now().getValue(), 0);
            return ResponseEntity.status(HttpResponseStatus.INTERNAL_SERVER_ERROR.code()).build();
        }

    }

}
