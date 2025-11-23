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
    @Column(name = "sample_table_id")
    private Integer sampleTableId = 0;

    /**
     * サンプルテーブルIdを取得する
     * 
     * @return サンプルテーブルId
     */
    public Integer getSampleTableId() {
        return sampleTableId;
    }

    /**
     * サンプルテーブルIdを設定する
     * 
     * @param sampleTableId サンプルテーブルId
     */
    public void setSampleTableId(final Integer sampleTableId) {
        this.sampleTableId = sampleTableId;
    }

}
