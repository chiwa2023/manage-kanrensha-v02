DELETE FROM `partner_access_token`;
ALTER TABLE `partner_access_token` auto_increment = 0;

DELETE FROM `partner_access_history_2026`;
ALTER TABLE `partner_access_history_2026` auto_increment = 0;


INSERT INTO `partner_access_token` (`partner_access_token_id`,`user_code`,`user_name`,`access_token_hash`,`expires_at`,`created_at`,`last_used_at`,`revoked_at`,`ip_address`)
  VALUES 
     (325,22,'111','339912','2022-12-05 00:00:00','2022-12-05 00:00:00',NULL,NULL,'127.0.0.1') --他人
    ,(326,190,'name_aaa','5994471abb01112afcc18159f6cc74b4f511b99806da59b3caf5a9c173cacfc5','2029-06-05 12:34:56','2022-12-05 12:34:56','2099-1-2 12:34:56',NULL,'127.0.0.1') --取得
    ,(327,190,'name','98765','2023-06-05 00:00:00','2022-12-05 00:00:00','2022-12-05 00:00:00','2022-12-05 00:00:00','127.0.0.1') --過去データ
    ,(328,44,'name','8698ed59037f22f0793abf11c51cea5d9fc0aa6e1739b69fd4b7be3cf08a4dad','2023-06-05 00:00:00','2022-12-05 00:00:00','2022-12-05 00:00:00','2022-12-05 00:00:00','127.0.0.1') --失効
    ;

