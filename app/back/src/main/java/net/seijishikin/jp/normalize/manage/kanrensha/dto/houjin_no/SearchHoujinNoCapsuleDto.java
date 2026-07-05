package net.seijishikin.jp.normalize.manage.kanrensha.dto.houjin_no;

import java.io.Serializable;
import java.time.LocalDate;

import net.seijishikin.jp.normalize.common_tool.dto.paging.PagingIntegerDtoInterface;

/**
 * 法人番号検索条件Dto
 */
public class SearchHoujinNoCapsuleDto implements Serializable, PagingIntegerDtoInterface { // NOPMD DataClass

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 全件数 */
    private Integer allCount = INIT_INTEGER;

    /** ページ内件数 */
    private Integer limit = INIT_INTEGER;

    /** ページ番号 */
    private Integer pageNumber = INIT_INTEGER;

    /**
     * 全件数を取得する
     */
    @Override
    public Integer getAllCount() {
        return allCount;
    }

    /**
     * 全件数を設定する
     */
    @Override
    public void setAllCount(final Integer allCount) {
        this.allCount = allCount;
    }

    /**
     * ページ内件数を取得する
     */
    @Override
    public Integer getLimit() {
        return limit;
    }

    /**
     * ページ内件数を設定する
     */
    @Override
    public void setLimit(final Integer limit) {
        this.limit = limit;
    }

    /**
     * ページ番号を取得する
     */
    @Override
    public Integer getPageNumber() {
        return pageNumber;
    }

    /**
     * ページ番号を設定する
     */
    @Override
    public void setPageNumber(final Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    /** アプリケーションId */
    private String appId = INIT_STRING;

    /** 名称 */
    private String name = INIT_STRING;

    /** 応答形式(CSV-Unicode) */
    private String type = INIT_STRING;

    /** 名称検索方式 */
    private String mode = INIT_STRING;

    /** 名称検索対象 */
    private String target = INIT_STRING;

    /** 市区町村コード */
    private String address = INIT_STRING;

    /** 法人種別(常に全検索 = 空文字) */
    private String kind = INIT_STRING;

    /** 履歴該否 */
    private String change = INIT_STRING;

    /** 登記記録の閉鎖該否(過去データを見る場合、閉鎖を使いうるので常にON) */
    private static final String close = INIT_STRING;

    /** 指定年月日開始 */
    private LocalDate from = INIT_DATE;

    /** 指定年月日終了 */
    private LocalDate to = INIT_DATE; // NOPMD ShortValiable(外部仕様がその指定なので・・・)

    /** 分割番号(ページングのページ番号) */
    private Integer divide = INIT_INTEGER;

    /**
     * アプリケーションIdを取得する
     * 
     * @return アプリケーションId
     */
    public String getAppId() {
        return appId;
    }

    /**
     * アプリケーションIdを設定する
     * 
     * @param appId アプリケーションId
     */
    public void setAppId(final String appId) {
        this.appId = appId;
    }

    /**
     * 名称を取得する
     * 
     * @return 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 名称を設定する
     * 
     * @param name 名称
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * 応答形式(CSV-Unicode)を取得する
     * 
     * @return 応答形式(CSV-Unicode)
     */
    public String getType() {
        return type;
    }

    /**
     * 応答形式(CSV-Unicode)を設定する
     * 
     * @param type 応答形式(CSV-Unicode)
     */
    public void setType(final String type) {
        this.type = type;
    }

    /**
     * 名称検索方式を取得する
     * 
     * @return 名称検索方式
     */
    public String getMode() {
        return mode;
    }

    /**
     * 名称検索方式を設定する
     * 
     * @param mode 名称検索方式
     */
    public void setMode(final String mode) {
        this.mode = mode;
    }

    /**
     * 名称検索対象を取得する
     * 
     * @return 名称検索対象
     */
    public String getTarget() {
        return target;
    }

    /**
     * 名称検索対象を設定する
     * 
     * @param target 名称検索対象
     */
    public void setTarget(final String target) {
        this.target = target;
    }

    /**
     * 市区町村コードを取得する
     * 
     * @return 市区町村コード
     */
    public String getAddress() {
        return address;
    }

    /**
     * 市区町村コードを設定する
     * 
     * @param address 市区町村コード
     */
    public void setAddress(final String address) {
        this.address = address;
    }

    /**
     * 法人種別を取得する
     * 
     * @return 法人種別
     */
    public String getKind() {
        return kind;
    }

    /**
     * 法人種別を設定する
     * 
     * @param kind 法人種別
     */
    public void setKind(final String kind) {
        this.kind = kind;
    }

    /**
     * 履歴該否を取得する
     * 
     * @return 履歴該否
     */
    public String getChange() {
        return change;
    }

    /**
     * 履歴該否を設定する
     * 
     * @param change 履歴該否
     */
    public void setChange(final String change) {
        this.change = change;
    }

    /**
     * 指定年月日開始を取得する
     * 
     * @return 指定年月日開始
     */
    public LocalDate getFrom() {
        return from;
    }

    /**
     * 指定年月日開始を設定する
     * 
     * @param from 指定年月日開始
     */
    public void setFrom(final LocalDate from) {
        this.from = from;
    }

    /**
     * 指定年月日終了を取得する
     * 
     * @return 指定年月日終了
     */
    public LocalDate getTo() {
        return to;
    }

    /**
     * 指定年月日終了を設定する
     * 
     * @param to 指定年月日終了
     */
    public void setTo(final LocalDate to) { // NOPMD
        this.to = to;
    }

    /**
     * 分割番号を取得する
     * 
     * @return 分割番号
     */
    public Integer getDivide() {
        return divide;
    }

    /**
     * 分割番号を設定する
     * 
     * @param divide 分割番号
     */
    public void setDivide(final Integer divide) {
        this.divide = divide;
    }

    /**
     * 登記記録の閉鎖該否を取得する
     * 
     * @return 登記記録の閉鎖該否
     */
    public String getClose() {
        return close;
    }

}
