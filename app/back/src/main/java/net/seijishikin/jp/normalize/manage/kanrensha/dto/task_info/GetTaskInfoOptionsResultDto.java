package net.seijishikin.jp.normalize.manage.kanrensha.dto.task_info;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;

/**
 * タスク選択選択肢検索結果Dto
 */
public class GetTaskInfoOptionsResultDto extends FrameworkMessageAndResultDto //
        implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 選択肢リスト */
    private List<TaskInfoCodeCheckOptionDto> listDto = new ArrayList<>();

    /**
     * 選択肢リストを取得する
     * 
     * @return 選択肢リスト
     */
    public List<TaskInfoCodeCheckOptionDto> getListDto() {
        return listDto;
    }

    /**
     * 選択肢リストを設定する
     * 
     * @param listDto 選択肢リスト
     */
    public void setListDto(final List<TaskInfoCodeCheckOptionDto> listDto) {
        this.listDto = listDto;
    }

}
