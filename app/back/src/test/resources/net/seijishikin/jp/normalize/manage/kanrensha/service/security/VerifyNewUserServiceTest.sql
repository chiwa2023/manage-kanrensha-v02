DELETE FROM `user_new`;

INSERT INTO `user_new` (`email`,`regist_code`,`limit_datetime`,`verify_token`,`verify_limit_date_time`)  VALUES
  ('aaa@example.com','44a6e3c5-1143-43b4-ad68-7d2160fe71ad','2022-12-05 12:34:56','53da8d6f-92e3-466a-9f43-b2be7437b81b','2022-01-03 10:08:18')
 ,('bbb@example.com','99a6e3c5-1143-43b4-ad68-7d2160fe71ad','2022-12-05 12:34:56','99da8d6f-92e3-466a-9f43-b2be7437b81b','2090-01-03 10:08:18')
;
