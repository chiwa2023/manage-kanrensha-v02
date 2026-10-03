
DELETE FROM `contact_manager`;
ALTER TABLE `contact_manager` auto_increment = 0;

INSERT INTO `contact_manager` (`contact_manager_id`,`contact_manager_code`,`is_latest`,`first_timestamp`,`is_closed`,`close_timestamp`,`inquire_user_id`,`inquire_user_code`,`inquire_user_name`,`inquire_title`,`inquire_content`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) 
   VALUES 
       --最新でないので抽出対象外
      (324,26,0,'2025-12-05 12:34:56',0,'1948-07-28 23:59:59',196,190,'管理人　太郎','問い合わせタイトル','問い合わせ内容',196,190,'管理人　太郎','2026-09-23 16:12:34',0,0,'','1948-07-28 23:59:59')

      --最新なので未終了で抽出1
    , (325,26,1,'2025-12-05 12:34:56',0,'1948-07-28 23:59:59',196,190,'管理人　太郎','問い合わせタイトルc','問い合わせ内容',196,190,'管理人　太郎','2026-09-23 16:12:34',0,0,'','1948-07-28 23:59:59')
      --最新なので未終了で抽出2
    , (326,29,1,'2025-12-05 12:34:56',0,'1948-07-28 23:59:59',197,191,'aaa','問い合わせタイトルa','問い合わせ内容a',196,190,'管理人　太郎','2026-09-23 16:12:34',0,0,'','1948-07-28 23:59:59')
      --終了かつ最新で抽出
    , (327,29,1,'2025-12-05 12:34:56',1,'1948-07-28 23:59:59',197,191,'aaa','問い合わせタイトルa','問い合わせ内容a',196,190,'管理人　太郎','2026-09-23 16:12:34',0,0,'','1948-07-28 23:59:59')
      ;
