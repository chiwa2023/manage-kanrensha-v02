DELETE FROM `address_postal`;
ALTER TABLE `address_postal` auto_increment = 0;

INSERT INTO `address_postal` (`address_postal_id`,`postalcode1`,`postalcode2`,`is_latest`,`lg_code`,`address_org`,`address_name`,`is_gyoseiku_data`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES 
   (145,'074','1271',1,'011011','以下に掲載がない場合','北海道深川市以下に掲載がない場合',0,196,190,'管理人　太郎','2026-02-26 20:08:21',0,0,'','1948-07-28 23:59:59')
 , (146,'074','1271',1,'011011','以下に掲載がない場合','北海道稚内市以下に掲載がない場合',0,196,190,'管理人　太郎','2026-02-26 20:08:21',0,0,'','1948-07-28 23:59:59')
 ;
