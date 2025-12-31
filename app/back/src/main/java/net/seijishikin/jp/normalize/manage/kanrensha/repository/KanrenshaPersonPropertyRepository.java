package net.seijishikin.jp.normalize.manage.kanrensha.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonPropertyEntity;

/**
 * kanrensha_person_property接続用Repository
 */
public interface KanrenshaPersonPropertyRepository extends JpaRepository<KanrenshaPersonPropertyEntity, Integer> {

    /**
     * 関連者コードで検索する
     * 
     * @param kanrenshaCode 関連者コード
     * @return 検索結果
     */
    List<KanrenshaPersonPropertyEntity> findByPersonKanrenshaCodeOrderByKanrenshaPersonPropertyIdDesc(
            String kanrenshaCode);


    /**
     * 職業承認作業リストを取得する
     *
     * @param startDatetime 検索開始日時
     * @param endDatetime   検索終了日時
     * @param listIsEdit    作業終了フラグリスト
     * @param isAccept      作業承認フラグ
     * @param isLatest      最新フラグ
     * @param pageable      ページング
     * @return 検索結果
     */
    List<KanrenshaPersonPropertyEntity> findByInsertTimestampBetweenAndIsShokyouAcceptInAndIsShokyouEditAndIsLatest(
            LocalDateTime startDatetime, LocalDateTime endDatetime, List<Boolean> listIsEdit, Boolean isAccept,
            Boolean isLatest, Pageable pageable);

    /**
     * 作業承認対象件数を取得する
     *
     * @param startDatetime 検索開始日時
     * @param endDatetime   検索終了日時
     * @param listIsEdit    作業終了フラグリスト
     * @param isAccept      作業承認
     * @param isLatest      最新フラグ
     * @return 検索件数
     */
    Integer countByInsertTimestampBetweenAndIsShokyouAcceptInAndIsShokyouEditAndIsLatest(LocalDateTime startDatetime,
            LocalDateTime endDatetime, List<Boolean> listIsEdit, Boolean isAccept, Boolean isLatest);
}
