package net.seijishikin.jp.normalize.manage.kanrensha.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * 疎通確認用サンプルテーブルEntity
 */
@Entity
@Table(name = "sample_table")
public class SampleTableEntity implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** サンプルテーブルId */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_info_id")
    private Integer taskInfoId = 0;

    /**
     * サンプルテーブルId
     *
     * @return サンプルテーブルId
     */
    public Integer getTaskInfoId() {
        return taskInfoId;
    }

    /**
     * サンプルテーブルId
     *
     * @param taskInfoId サンプルテーブルId
     */
    public void setTaskInfoId(final Integer taskInfoId) {
        this.taskInfoId = taskInfoId;
    }
}
