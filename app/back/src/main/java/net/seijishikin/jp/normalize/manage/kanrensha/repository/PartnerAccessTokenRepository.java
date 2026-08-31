package net.seijishikin.jp.normalize.manage.kanrensha.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.PartnerAccessTokenEntity;

/**
 * partner_access_token接続用Repository
 */
public interface PartnerAccessTokenRepository extends JpaRepository<PartnerAccessTokenEntity, Integer> {

    /**
     * ユーザコードで現在有効なAPIパートナー長期トークン発行状態を取得する
     * 
     * @param userCode ユーザコード
     * @return 検索結果
     */
    List<PartnerAccessTokenEntity> findByUserCodeAndRevokedAtNull(Integer userCode);

    /**
     * ハッシュ化されたトークンが一致するでーーたを取得する
     * 
     * @param tokenHashed ハッシュ化されたトークン
     * @return 検索結果
     */
    Optional<PartnerAccessTokenEntity> findByAccessTokenHash(String tokenHashed);
}
