package net.seijishikin.jp.normalize.manage.kanrensha.logic.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.UserPersonEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.UserRoleEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.UserPersonRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.UserRoleRepository;

/**
 * ユーザと利用者紐づけ挿入Logic
 */
@Component
public class InsertCombineUserRiyoushaLogic {

    /** ユーザ人物Repository */
    @Autowired
    private UserPersonRepository userPersonRepository;

    /** ユーザ権限Repository */
    @Autowired
    private UserRoleRepository userRoleRepository;

    /** テーブル履歴設定Util */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /**
     * 処理を行う
     *
     * @param riyoushaCode 利用者コード
     * @param userDto      操作者ユーザ最低限Dto
     * @return 処理結果
     */
    public Integer practcie(final String role, final Integer riyoushaCode, final LeastUserDto userDto) {

        Optional<UserPersonEntity> optional = userPersonRepository.findById(userDto.getUserPersonId());
        if (optional.isEmpty()) {
            throw new EmptyResultDataAccessException("必要なユーザが見つかりませんでした", 1);
        }
        UserPersonEntity personEntity = optional.get();

        // 常に別プロセスで紐づけなし同roleを登録した後に、関連者を追加したので変更処理をする
        List<UserRoleEntity> listOld = userRoleRepository.findByEmailAndRoleAndIsLatestTrue(personEntity.getEmail(),
                role);
        if (listOld.isEmpty()) {
            throw new EmptyResultDataAccessException("該当する権限登録がありません", 1);
        }
        if (1 < listOld.size()) {  // NOPMD LiteralIntCondition
            throw new DuplicateKeyException("該当する権限が重複しています");
        }

        // 現データを履歴に
        UserRoleEntity oldEntity = listOld.get(0);
        setTableDataHistoryUtil.practiceDelete(userDto, oldEntity);
        // 現コードが初期状態であることの確認はほぼ実装ミスの検出用
        final String BLANK = "";
        if (!BLANK.equals(oldEntity.getKanrenshaCode())) {
            throw new IllegalStateException("現在の権限データにデータに不整合があります");
        }
        if (0 != oldEntity.getRiyoushaCode()) {
            throw new IllegalStateException("現在の権限データにデータに不整合があります");
        }
        userRoleRepository.save(oldEntity);

        // 最新を追加
        UserRoleEntity newEntity = new UserRoleEntity();
        BeanUtils.copyProperties(oldEntity, newEntity);
        newEntity.setRiyoushaCode(riyoushaCode);

        setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
        newEntity.setUserRoleId(0); // auto increment明記

        return userRoleRepository.save(newEntity).getUserRoleId();
    }

}
