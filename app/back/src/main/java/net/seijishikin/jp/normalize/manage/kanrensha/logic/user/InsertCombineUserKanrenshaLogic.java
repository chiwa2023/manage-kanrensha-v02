package net.seijishikin.jp.normalize.manage.kanrensha.logic.user;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
//import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;


/**
 * ユーザ関連者紐づけ
 */
@Component
public class InsertCombineUserKanrenshaLogic {

//    /** ユーザ利用者紐づけRepository */
//    @Autowired
//    private UserKanrenshaCombineRepository userKanrenshaCombineRepository;

//    /** テーブル履歴設定Util */
//    @Autowired
//    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /**
     * 処理を行う
     *
     * @param userCode      ユーザコード
     * @param kanrenshaKbn  関連者区分
     * @param kanrenshaCode 関連者コード
     * @param userDto       操作者ユーザ最低限Dto
     * @return 処理結果
     */
    public Integer practcie(final Integer userCode, final Short kanrenshaKbn, final String kanrenshaCode,
            final LeastUserDto userDto) {

//        UserKanrenshaCombineEntity combineEntity = new UserKanrenshaCombineEntity();
//        combineEntity.setKanrenshaKbn(kanrenshaKbn);
//        combineEntity.setUseUserCode(userCode);
//        combineEntity.setKanrenshaCode(kanrenshaCode);
//
//        setTableDataHistoryUtil.practiceInsert(userDto, combineEntity);
//        combineEntity.setUserKanrenshaCombineId(0); // auto_increment明示
//
//        return userKanrenshaCombineRepository.save(combineEntity).getUserKanrenshaCombineId();
        
        return null;
    }

}
