DELETE FROM `year_option`;
ALTER TABLE `year_option` auto_increment = 0;

INSERT INTO `year_option` (`selected_year`,`is_selected`) VALUES 
    (2019,0)  , (2020,0) 
  , (2021,0)  , (2022,0)  , (2023,0)  , (2024,0)  , (2025,0) , (2026,0)  , (2027,0)
;

DELETE FROM `kanrensha_combine_org_2019`;
DELETE FROM `kanrensha_combine_org_2020`;
DELETE FROM `kanrensha_combine_org_2021`;
DELETE FROM `kanrensha_combine_org_2022`;
DELETE FROM `kanrensha_combine_org_2023`;
DELETE FROM `kanrensha_combine_org_2024`;
DELETE FROM `kanrensha_combine_org_2025`;
DELETE FROM `kanrensha_combine_org_2026`;
DELETE FROM `kanrensha_combine_org_2027`;

DELETE FROM `kanrensha_combine_org_2024`;
ALTER TABLE `kanrensha_combine_org_2024` auto_increment = 0;

INSERT INTO `kanrensha_combine_org_2024` (`kanrensha_combine_org_id`,`kanrensha_combine_org_code`,`is_latest`,`kanrensha_kbn`,`person_kanrensha_code`,`person_name`,`org_kanrensha_code`,`org_name`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) 
   VALUES 
       (2,1,1,2,'101','三宅紀昭','102','ふんだくり企業',202,2,'bbb運営者　次郎','2024-04-29 07:26:27',0,0,'','1948-07-28 23:59:59')
     , (3,1,1,3,'201','宮城壮一','202','未来創造党',202,2,'bbb運営者　次郎','2024-04-29 07:30:37',0,0,'','1948-07-28 23:59:59')
   ;
