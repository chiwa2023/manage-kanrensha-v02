package net.seijishikin.jp.normalize.manage.kanrensha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.YearOptionEntity;

/**
 * year_option接続用Repository
 */
public interface YearOptionRepository extends JpaRepository<YearOptionEntity, Integer> {

}
