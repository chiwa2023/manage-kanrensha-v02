DELETE FROM `wk_tbl_postal_common`;
ALTER TABLE `wk_tbl_postal_common` auto_increment = 0;

INSERT INTO `wk_tbl_postal_common` (`wk_tbl_postal_common_id`,`address_postal_id`,`address_postal_irregular_id`,`postalcode1`,`postalcode2`,`is_latest`,`lg_code`,`address_org`,`address_name`,`is_gyoseiku_data`,`address_postal`,`address_block`,`is_add_postal`,`is_repair_rsdt`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES 
     (103,2,3,'4','5',1,'6','7','8',1,'10','11',1,1,196,190,'ユーザ','2022-12-05 12:34:56',0,0,NULL,'1948-07-28 23:59:59')
   , (104,2,3,'4','5',1,'6','7','8',1,'10','11',1,1,196,190,'ユーザ','2022-12-05 12:34:56',0,0,NULL,'1948-07-28 23:59:59')
   , (105,2,3,'4','5',1,'6','7','8',1,'10','11',1,1,196,190,'ユーザ','2022-12-05 12:34:56',0,0,NULL,'1948-07-28 23:59:59')
;
