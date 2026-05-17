DELETE FROM `riyousha_combine_org`;

ALTER TABLE `riyousha_combine_org` auto_increment = 0;

INSERT INTO `riyousha_combine_org` (`riyousha_combine_org_id`,`riyousha_combine_org_code`,`is_latest`,`riyousha_role`,`person_code`,`person_riyousha_code`,`person_riyousha_name`,`org_riyousha_code`,`org_name`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)
   VALUES 
    (754,654,1,'ROLE_manager',246,901,'管理者太郎',383,'組織E',181,215,'ユーザ','2022-12-05 12:34:56',0,0,'','1947-07-28 23:59:59')
   ,(755,654,1,'ROLE_manager',190,901,'管理者太郎',483,'組織A',181,215,'ユーザ','2022-12-05 12:34:56',0,0,'','1947-07-28 23:59:59')
   ,(756,654,1,'ROLE_partner_api',190,901,'管理者太郎',484,'組織B',181,215,'ユーザ','2022-12-05 12:34:56',0,0,'','1947-07-28 23:59:59')
   ,(757,654,1,'ROLE_manager',190,901,'管理者太郎',485,'組織C',181,215,'ユーザ','2022-12-05 12:34:56',0,0,'','1947-07-28 23:59:59')
   ,(758,654,0,'ROLE_admin',190,901,'管理者太郎',383,'組織D',181,215,'ユーザ','2022-12-05 12:34:56',0,0,'','1947-07-28 23:59:59')
   ;
