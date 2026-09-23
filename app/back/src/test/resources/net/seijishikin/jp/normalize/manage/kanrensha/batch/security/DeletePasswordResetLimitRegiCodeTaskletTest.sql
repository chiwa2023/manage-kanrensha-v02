DELETE FROM `user_password_reset`;

INSERT INTO `user_password_reset` (`email`,`regist_code`,`limit_datetime`)    VALUES 
    ('ddd@seijishikin.net','12345','2099-12-05 00:00:00')
   ,('eee@seijishikin.net','23456','2024-09-05 00:00:00')
;

   
