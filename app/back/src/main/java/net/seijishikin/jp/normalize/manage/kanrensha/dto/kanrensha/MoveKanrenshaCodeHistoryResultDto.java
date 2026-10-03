package net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;

/**
 * 関連者コード移動履歴検索Dto
 */
public class MoveKanrenshaCodeHistoryResultDto extends FrameworkMessageAndResultDto // NOPMD DataClass
        implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 存続コードユーザリスト */
    private List<KanrenshaCodeMoveHistoryDto> listUserOrgin = new ArrayList<>();

    /** 廃止コードユーザリスト */
    private List<KanrenshaCodeMoveHistoryDto> listUserAbolish = new ArrayList<>();

    /** 存続コードコードリスト */
    private List<KanrenshaCodeMoveHistoryDto> listCodeOrgin = new ArrayList<>();

    /** 廃止コードコードリスト */
    private List<KanrenshaCodeMoveHistoryDto> listCodeAbolish = new ArrayList<>();

    /**
     * 存続コードユーザリストを取得する
     * 
     * @return 存続コードユーザリスト
     */
    public List<KanrenshaCodeMoveHistoryDto> getListUserOrgin() {
        return listUserOrgin;
    }

    /**
     * 存続コードユーザリストを設定する
     * 
     * @param listUserOrgin 存続コードユーザリスト
     */
    public void setListUserOrgin(final List<KanrenshaCodeMoveHistoryDto> listUserOrgin) {
        this.listUserOrgin = listUserOrgin;
    }

    /**
     * 廃止コードユーザリストを取得する
     * 
     * @return 廃止コードユーザリスト
     */
    public List<KanrenshaCodeMoveHistoryDto> getListUserAbolish() {
        return listUserAbolish;
    }

    /**
     * 廃止コードユーザリストを設定する
     * 
     * @param listUserAbolish 廃止コードユーザリスト
     */
    public void setListUserAbolish(final List<KanrenshaCodeMoveHistoryDto> listUserAbolish) {
        this.listUserAbolish = listUserAbolish;
    }

    /**
     * 存続コードコードリストを取得する
     * 
     * @return 存続コードコードリスト
     */
    public List<KanrenshaCodeMoveHistoryDto> getListCodeOrgin() {
        return listCodeOrgin;
    }

    /**
     * 存続コードコードリストを設定する
     * 
     * @param listCodeOrgin 存続コードコードリスト
     */
    public void setListCodeOrgin(final List<KanrenshaCodeMoveHistoryDto> listCodeOrgin) {
        this.listCodeOrgin = listCodeOrgin;
    }

    /**
     * 廃止コードコードリストを取得する
     * 
     * @return 廃止コードコードリスト
     */
    public List<KanrenshaCodeMoveHistoryDto> getListCodeAbolish() {
        return listCodeAbolish;
    }

    /**
     * 廃止コードコードリストを設定する
     * 
     * @param listCodeAbolish 廃止コードコードリスト
     */
    public void setListCodeAbolish(final List<KanrenshaCodeMoveHistoryDto> listCodeAbolish) {
        this.listCodeAbolish = listCodeAbolish;
    }

}
