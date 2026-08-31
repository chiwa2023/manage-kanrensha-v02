DELETE FROM `user_password_reset`;

INSERT INTO `user_password_reset` (`email`,`regist_code`,`limit_datetime`)    VALUES 
   ('eee@seijishikin.net','99887','2022-12-05 00:00:00')
  ,('fff@seijishikin.net','77665','2099-12-31 23:59:59')
;
