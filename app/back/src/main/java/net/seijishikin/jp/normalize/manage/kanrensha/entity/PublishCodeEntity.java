package net.seijishikin.jp.normalize.manage.kanrensha.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * publish_code接続用Entity
 */
@Entity
@Table(name = "publish_code")
public class PublishCodeEntity implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** テーブルId */
    @Id
    @Column(name = "kanrensha_code")
    private String kanrenshaCode = "";

    /**
     * テーブルIdを取得する
     * 
     * @return テーブルId
     */
    public String getKanrenshaCode() {
        return kanrenshaCode;
    }

    /**
     * テーブルIdを設定する
     * 
     * @param kanrenshaCode テーブルId
     */
    public void setKanrenshaCode(final String kanrenshaCode) {
        this.kanrenshaCode = kanrenshaCode;
    }

}
