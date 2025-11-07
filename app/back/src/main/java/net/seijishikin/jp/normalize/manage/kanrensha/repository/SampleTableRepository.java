package net.seijishikin.jp.normalize.manage.kanrensha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.SampleTableEntity;

/**
 * sample_table接続用Repository
 */
public interface SampleTableRepository extends JpaRepository<SampleTableEntity, Integer> {

    /**
     * テーブルsample_tableの存在確認をする
     *
     * @return 存在する場合はテーブル名
     */
    @Query(value = "SELECT table_name FROM INFORMATION_SCHEMA.TABLES"
            + "    WHERE table_schema = 'manage_kanrensha' AND table_name = 'sample_table' ", nativeQuery = true)
    List<String> findMyself();

}
