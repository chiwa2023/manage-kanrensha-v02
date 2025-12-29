package net.seijishikin.jp.normalize.common_tool.entity;

import java.time.LocalDateTime;

import net.seijishikin.jp.normalize.common_tool.dto.DtoEntityInitialValueInterface;

/**
 * テーブル履歴を管理するためのInterface
 */
public interface AllTabeDataHistoryInterface extends DtoEntityInitialValueInterface {
	
    /**
     * 最新該否を取得する
     *
     * @return 最新該否
     */
    Boolean getIsLatest();

    /**
     * 最新該否を設定する
     *
     * @param isLatest 最新該否
     */
    void setIsLatest(Boolean isLatest);

    /**
     * 挿入ユーザIdを取得する
     *
     * @return 挿入ユーザId
     */
    Integer getInsertUserId();

    /**
     * 挿入ユーザIdを設定する
     *
     * @param insertUserId 挿入ユーザId
     */
    void setInsertUserId(Integer insertUserId);

    /**
     * 挿入ユーザコードを取得する
     *
     * @return 挿入ユーザコード
     */
    Integer getInsertUserCode();

    /**
     * 挿入ユーザコードを設定する
     *
     * @param insertUserCode 挿入ユーザコード
     */
    void setInsertUserCode(Integer insertUserCode);

    /**
     * 挿入ユーザ名称を取得する
     *
     * @return 挿入ユーザ名称
     */
    String getInsertUserName();

    /**
     * 挿入ユーザ名称を設定する
     *
     * @param insertUserName 挿入ユーザ名称
     */
    void setInsertUserName(String insertUserName);

    /**
     * 挿入日時を取得する
     *
     * @return 挿入日時
     */
    LocalDateTime getInsertTimestamp();

    /**
     * 挿入日時を設定する
     *
     * @param insertTimestamp 挿入日時
     */
    void setInsertTimestamp(LocalDateTime insertTimestamp);

    /**
     * 無効ユーザIdを取得する
     *
     * @return 無効ユーザId
     */
    Integer getDeleteUserId();

    /**
     * 無効ユーザIdを設定する
     *
     * @param deleteUserId 無効ユーザId
     */
    void setDeleteUserId(Integer deleteUserId);

    /**
     * 無効ユーザコードを取得する
     *
     * @return 無効ユーザコード
     */
    Integer getDeleteUserCode();

    /**
     * 無効ユーザコードを設定する
     *
     * @param deleteUserCode 無効ユーザコード
     */
    void setDeleteUserCode(Integer deleteUserCode);

    /**
     * 無効ユーザ名称を取得する
     *
     * @return 無効ユーザ名称
     */
    String getDeleteUserName();

    /**
     * 無効ユーザ名称を設定する
     *
     * @param deleteUserName 無効ユーザ名称
     */
    void setDeleteUserName(String deleteUserName);

    /**
     * 無効日時を取得する
     *
     * @return 無効日時
     */
    LocalDateTime getDeleteTimestamp();

    /**
     * 無効日時を設定する
     *
     * @param deleteTimestamp 無効日時
     */
    void setDeleteTimestamp(LocalDateTime deleteTimestamp);
}
