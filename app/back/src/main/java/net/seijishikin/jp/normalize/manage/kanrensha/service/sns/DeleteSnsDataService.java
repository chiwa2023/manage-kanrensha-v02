package net.seijishikin.jp.normalize.manage.kanrensha.service.sns;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.sns.EditSnsServiceCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.SnsServiceEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.SnsServiceRepository;

/**
 * SNSサービス削除Service
 */
@Service
public class DeleteSnsDataService {

    /** SNSサービスRepository */
    @Autowired
    private SnsServiceRepository snsServiceRepository;

    /** テーブル履歴設定utility */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /**
     * 処理を行う
     * 
     * @param capsuleDto 削除条件Dto
     * @return 削除Id
     */
    @Transactional
    public Integer practice(final EditSnsServiceCapsuleDto capsuleDto) {

        // 指定されたEntityを履歴にして上書き

        Optional<SnsServiceEntity> optional = snsServiceRepository
                .findById(capsuleDto.getSnsServiceEntity().getSnsServiceId());

        if (optional.isEmpty()) {
            throw new EmptyResultDataAccessException("データが存在しません", 1);
        }
        SnsServiceEntity entity = optional.get();

        setTableDataHistoryUtil.practiceDelete(capsuleDto.getUserDto(), entity);

        return snsServiceRepository.save(entity).getSnsServiceId();
    }

}
