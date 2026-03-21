package net.seijishikin.jp.normalize.manage.kanrensha.service.sns;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.FormatNaturalSearchTextUtil;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.sns.EditSnsServiceCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.SnsServiceEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.SnsServiceRepository;

/**
 * SNSサービス保存Service
 */
@Service
public class SaveSnsDataService {

    /** SNSサービスRepository */
    @Autowired
    private SnsServiceRepository snsServiceRepository;

    /** SNSサービスRepository */
    @Autowired
    private FormatNaturalSearchTextUtil formatNaturalSearchTextUtil;

    /** テーブル履歴設定utility */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /**
     * 処理を行う
     * 
     * @param capsuleDto 保存条件Dto
     * @return 処理後Id
     */
    @Transactional
    public Integer practice(final EditSnsServiceCapsuleDto capsuleDto) {

        SnsServiceEntity snsServiceEntity = capsuleDto.getSnsServiceEntity();
        snsServiceEntity.setSearchText(formatNaturalSearchTextUtil.practice(snsServiceEntity.getSnsServiceName()));

        if (0 == snsServiceEntity.getSnsServiceId()) {
            // idの設定がない場合は追加
            return this.add(snsServiceEntity, capsuleDto.getUserDto());
        } else {
            // idの設定がある場合は更新
            return this.change(snsServiceEntity, capsuleDto.getUserDto());
        }
    }

    private Integer add(final SnsServiceEntity snsServiceEntity, final LeastUserDto userDto) {

        Integer code = 1;

        Optional<SnsServiceEntity> optional = snsServiceRepository.findFirstByOrderBySnsServiceCodeDesc();
        if (!optional.isEmpty()) {
            code += optional.get().getSnsServiceCode();
        }
        snsServiceEntity.setSnsServiceCode(code);

        setTableDataHistoryUtil.practiceInsert(userDto, snsServiceEntity);
        snsServiceEntity.setSnsServiceId(0); // auto increment明記

        return snsServiceRepository.save(snsServiceEntity).getSnsServiceId();
    }

    private Integer change(final SnsServiceEntity snsServiceEntity, final LeastUserDto userDto) {

        Optional<SnsServiceEntity> optional = snsServiceRepository.findById(snsServiceEntity.getSnsServiceId());

        if (optional.isEmpty()) {
            throw new EmptyResultDataAccessException("データが存在しません", 1);
        }

        SnsServiceEntity snsEntityOld = optional.get();
        setTableDataHistoryUtil.practiceDelete(userDto, snsEntityOld);

        setTableDataHistoryUtil.practiceInsert(userDto, snsServiceEntity);
        snsServiceEntity.setSnsServiceId(0); // auto increment明記

        snsServiceRepository.save(snsEntityOld);

        return snsServiceRepository.save(snsServiceEntity).getSnsServiceId();
    }

}
