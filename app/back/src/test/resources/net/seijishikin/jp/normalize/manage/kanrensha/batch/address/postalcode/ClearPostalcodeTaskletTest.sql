DELETE FROM `address_postal`;
ALTER TABLE `address_postal` auto_increment = 0;

DELETE FROM `address_postal_irregular`;
ALTER TABLE `address_postal_irregular` auto_increment = 0;

INSERT INTO `address_postal` (`address_postal_id`,`postalcode`,`is_latest`,`lg_code`,`address_org`,`address_name`,`is_gyoseiku_data`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) 
    VALUES 
         (713,'253467',1,'965314','町字？','都道府県行政区',1,196,190,'管理人　太郎','2026-02-22 15:55:44',0,0,'','1948-07-28 23:59:59')
        ,(714,'253467',1,'965314','町字？','都道府県行政区',1,196,190,'管理人　太郎','2026-02-22 15:55:44',0,0,'','1948-07-28 23:59:59')
    ;


INSERT INTO `address_postal_irregular` (`address_postal_irregular_id`,`postalcode`,`is_latest`,`lg_code`,`address_org`,`address_name`,`address_postal`,`address_block`,`is_add_postal`,`is_repair_rsdt`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)
  VALUES 
       (496,'253467',1,'965314','町字？','都道府県行政区','都道府県行政区','町字番地建物',1,1,196,190,'管理人　太郎','2026-02-22 16:03:38',0,0,'','1948-07-28 23:59:59')
      ,(497,'253467',1,'965314','町字？','都道府県行政区','都道府県行政区','町字番地建物',1,1,196,190,'管理人　太郎','2026-02-22 16:03:38',0,0,'','1948-07-28 23:59:59')
  ;
