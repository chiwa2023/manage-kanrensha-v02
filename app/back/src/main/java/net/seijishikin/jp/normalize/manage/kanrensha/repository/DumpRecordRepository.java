package net.seijishikin.jp.normalize.manage.kanrensha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.DumpRecordEntity;

/**
 * dump_record接続用Repository
 */
public interface DumpRecordRepository extends JpaRepository<DumpRecordEntity, Integer> {

    /**
     * タスクコードから最新リストを取得する
     * 
     * @param taskInfoCode タスクコード
     * @return 検索結果
     */
    List<DumpRecordEntity> findByTaskInfoCodeAndIsLatestTrue(Integer taskInfoCode);

    /**
     * タスクコードリストに合致する最新リストを取得する
     * 
     * @param listCodeCode タスクコードリスト
     * @return 検索結果
     */
    List<DumpRecordEntity> findByTaskInfoCodeInAndIsLatestTrue(List<Integer> listCodeCode);
}
