DELETE FROM `partner_access_token`;
ALTER TABLE `partner_access_token` auto_increment = 0;


INSERT INTO `partner_access_token` (`partner_access_token_id`,`user_code`,`user_name`,`access_token_hash`,`expires_at`,`created_at`,`last_used_at`,`revoked_at`,`ip_address`)
  VALUES 
     (325,22,'111','339912','2022-12-05 00:00:00','2022-12-05 00:00:00',NULL,NULL,'127.0.0.1') --他人
    ,(326,190,'name_aaa','4c6f8fe41221d5c2000d6413fe9fefa202b4ebdcfb86326d28dee1e9ef6f9777','2099-06-05 12:34:56','2022-12-05 12:34:56','2023-1-2 12:34:56',NULL,'127.0.0.1') --取得
    ,(327,190,'name','98765','2023-06-05 00:00:00','2022-12-05 00:00:00','2022-12-05 00:00:00','2022-12-05 00:00:00','127.0.0.1') --過去データ
    ,(328,44,'name','627eeabfb9477ebc73c916f38bee30801bf811c03c252d5dadc91f1e96d6da9e','2023-06-05 00:00:00','2022-12-05 00:00:00','2022-12-05 00:00:00','2022-12-05 00:00:00','127.0.0.1') --失効
;

