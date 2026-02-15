package net.seijishikin.jp.normalize.manage.kanrensha.service.riyousha;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.common_tool.dto.DtoEntityInitialValueInterface;
import net.seijishikin.jp.normalize.common_tool.dto.FrameworkCapsuleDto;
import net.seijishikin.jp.normalize.common_tool.dto.select_options.SelectOptionIntegerDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.RiyoushaCombineOrgEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.RiyoushaCombineOrgRepository;

/**
 * ユーザに紐づけられた組織をselectboxの選択肢形式で取得する
 */
@Service
public class GetRiyoushaOrgCombinOptionsService {

    /** 利用者個人組織紐づけRepository */
    @Autowired
    private RiyoushaCombineOrgRepository riyoushaCombineOrgRepository;

    /**
     * 処理を行う
     * 
     * @param capsuleDto 検索条件Dto
     * @return 選択肢リスト
     */
    public List<SelectOptionIntegerDto> practice(final FrameworkCapsuleDto capsuleDto) {

        List<SelectOptionIntegerDto> list = new ArrayList<>();
        // 必ず新規を入れる
        list.add(new SelectOptionIntegerDto(DtoEntityInitialValueInterface.INIT_INTEGER, "新規"));

        List<RiyoushaCombineOrgEntity> listEntity = riyoushaCombineOrgRepository
                .findByPersonRiyoushaCodeAndIsLatestTrue(capsuleDto.getUserDto().getUserPersonCode());

        for (RiyoushaCombineOrgEntity entity : listEntity) {
            list.add(new SelectOptionIntegerDto(entity.getOrgRiyoushaCode(), entity.getOrgName()));
        }

        return list;

    }

}
