DELETE FROM `kanrensha_combine_org_2019`;
ALTER TABLE `kanrensha_combine_org_2019` auto_increment = 0;

INSERT INTO `kanrensha_combine_org_2019` (`kanrensha_combine_org_id`,`kanrensha_combine_org_code`,`is_latest`,`kanrensha_kbn`,`person_kanrensha_code`,`person_name`,`org_kanrensha_code`,`org_name`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) 
   VALUES 
       (2,1,1,2,'101','三宅紀昭','102','ふんだくり企業',202,2,'bbb運営者　次郎','2019-04-29 07:26:27',0,0,'','1948-07-28 23:59:59')
     , (3,1,1,3,'201','宮城壮一','202','未来創造党',202,2,'bbb運営者　次郎','2019-04-29 07:30:37',0,0,'','1948-07-28 23:59:59')
   ;
