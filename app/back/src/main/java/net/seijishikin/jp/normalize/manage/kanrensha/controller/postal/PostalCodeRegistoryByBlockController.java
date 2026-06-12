package net.seijishikin.jp.normalize.manage.kanrensha.controller.postal;

import java.time.Year;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatus;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.address_rsdt.AddressRsdtResultDto;
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

        AddressRsdtResultDto resultDto = new AddressRsdtResultDto();
        try {
            AddressRsdtTemplateEntity templateEntity = getPostalCodeRegistoryByBlockService
                    .practice(capsuleDto.getLgCode(), capsuleDto.getSelectedBlock());

            if (Objects.isNull(templateEntity)) {
                // 予測できる理由で取得できないときはエラーで落とさない
                resultDto.setIsFailure(true);
                resultDto.setMessage("正常に住居詳細が取得できませんでした");
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(resultDto);
            } else {
                resultDto.setAddressRsdtEntity(templateEntity);
                return ResponseEntity.status(HttpStatus.OK).body(resultDto);
            }

        } catch (Exception exception) { // NOPMD 業務的な理由から積極的に許容
            saveStackTraceService.practice(exception, Year.now().getValue(), 0);
            resultDto.setIsFailure(true);
            resultDto.setMessage(exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultDto);
        }

    }

}
