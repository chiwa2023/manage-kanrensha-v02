package net.seijishikin.jp.normalize.manage.kanrensha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.UserNewEntity;

/**
 * user_new接続用Repository
 */
public interface UserNewRepository extends JpaRepository<UserNewEntity, String> {

    /**
     * URLダイレクトアクセス用認証トークンを元にエンティティを取得する
     * 
     * @param verifyToken URLダイレクトアクセス用認証トークン
     * @return 該当エンティティ
     */
    UserNewEntity findByVerifyToken(String verifyToken);
}
