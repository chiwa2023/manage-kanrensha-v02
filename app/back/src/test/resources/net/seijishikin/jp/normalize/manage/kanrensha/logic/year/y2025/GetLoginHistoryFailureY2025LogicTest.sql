DELETE FROM `login_history_2025`;
ALTER TABLE `login_history_2025` auto_increment = 0;

INSERT INTO `login_history_2025` (`login_history_id`,`email`,`ip_address`,`user_agent`,`attempt_time`,`is_success`) 
  VALUES 
  -- 抽出
     (241,'aaa@politician.balanse.report.net','Unknown','','2025-04-05 11:41:05',0)
   , (242,'aaa@politician.balanse.report.net','Unknown','','2025-04-06 11:41:05',0)
   , (243,'aaa@politician.balanse.report.net','Unknown','','2025-04-06 11:41:05',0)
   -- メールアドレスが異なる
   , (244,'wrong@user.com','Unknown','','2025-10-10 11:41:05',0)
   -- ログイン失敗でない
   , (245,'aaa@politician.balanse.report.net','Unknown','','2025-04-06 11:41:05',1)
   -- 指定期間外
   , (246,'aaa@politician.balanse.report.net','Unknown','','2025-03-06 11:41:05',0)
       ;
