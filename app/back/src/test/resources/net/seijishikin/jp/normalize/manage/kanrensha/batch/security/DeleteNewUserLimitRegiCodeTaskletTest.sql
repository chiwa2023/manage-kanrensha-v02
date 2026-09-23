DELETE FROM `user_new`;

INSERT INTO `user_new` (`email`,`regist_code`,`limit_datetime`,`verify_token`,`verify_limit_date_time`)  VALUES
  -- 認証した
    ('aaa@example.com','2111','2022-12-05 12:34:56',NULL,NULL)
  -- 認証しなかった
   ,('bbb@example.com','3111','2022-12-05 12:34:56','3112','2022-12-05 12:34:56')
  -- 期限切れでないので残る
   ,('ccc@example.com','2111','2022-12-05 12:34:56','2112','2090-01-03 10:08:18')
;
