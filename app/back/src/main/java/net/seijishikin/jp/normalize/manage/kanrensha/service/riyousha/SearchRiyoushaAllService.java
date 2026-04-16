package net.seijishikin.jp.normalize.manage.kanrensha.service.riyousha;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha.SearchRiyoushaAdminCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha.SearchRiyoushaAllCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha.SearchRiyoushaAllResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha.SearchRiyoushaManagerCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha.SearchRiyoushaPartnerApiCapsuleDto;

/**
 * 利用者(APIユーザ・運営者・管理者)全検索Service
 */
@Service
public class SearchRiyoushaAllService {

    /** 利用者API検索Service */
    @Autowired
    private SearchRiyoushaPartnerAppiService searchRiyoushaPartnerAppiService;

    /** 利用者運営者検索Service */
    @Autowired
    private SearchRiyoushaManagerService searchRiyoushaManagerService;

    /** 利用者管理者検索Service */
    @Autowired
    private SearchRiyoushaAdminService searchRiyoushaAdminService;

    /**
     * 処理を行う
     *
     * @param capsuleDto 検索条件Dto
     * @return 利用者検索結果Dto
     */
    public SearchRiyoushaAllResultDto practice(final SearchRiyoushaAllCapsuleDto capsuleDto) {

        // 検索条件を各capsuleDtoに複写
        this.copyCapsuleDto(capsuleDto);

        SearchRiyoushaAllResultDto resultDto = new SearchRiyoushaAllResultDto();

        if (capsuleDto.getIsPartnerApiSearch()) {

            resultDto.setSearchRiyoushaPartnerApiResultDto(
                    searchRiyoushaPartnerAppiService.practice(capsuleDto.getSearchRiyoushaPartnerApiCapsuleDto()));
        }

        if (capsuleDto.getIsManagerSearch()) {
            resultDto.setSearchRiyoushaManagerResultDto(
                    searchRiyoushaManagerService.practice(capsuleDto.getSearchRiyoushaManagerCapsuleDto()));
        }

        if (capsuleDto.getIsAdminSearch()) {
            resultDto.setSearchRiyoushaAdminResultDto(
                    searchRiyoushaAdminService.practice(capsuleDto.getSearchRiyoushaAdminCapsuleDto()));
        }

        return resultDto;
    }

    private void copyCapsuleDto(final SearchRiyoushaAllCapsuleDto capsuleDto) {

        SearchRiyoushaPartnerApiCapsuleDto capsuleDtoPartner = new SearchRiyoushaPartnerApiCapsuleDto();
        BeanUtils.copyProperties(capsuleDto, capsuleDtoPartner);
        capsuleDto.setSearchRiyoushaPartnerApiCapsuleDto(capsuleDtoPartner);

        SearchRiyoushaManagerCapsuleDto capsuleDtoManager = new SearchRiyoushaManagerCapsuleDto();
        BeanUtils.copyProperties(capsuleDto, capsuleDtoManager);
        capsuleDto.setSearchRiyoushaManagerCapsuleDto(capsuleDtoManager);

        SearchRiyoushaAdminCapsuleDto capsuleDtoAdmin = new SearchRiyoushaAdminCapsuleDto();
        BeanUtils.copyProperties(capsuleDto, capsuleDtoAdmin);
        capsuleDto.setSearchRiyoushaAdminCapsuleDto(capsuleDtoAdmin);
    }

}
