DELETE FROM `wk_tbl_address_rsdt_file`;
ALTER TABLE `wk_tbl_address_rsdt_file` auto_increment = 0;


DELETE FROM `wk_tbl_address_rsdt_change`;
ALTER TABLE `wk_tbl_address_rsdt_change` auto_increment = 0;

DELETE FROM `wk_tbl_address_rsdt_delete`;
ALTER TABLE `wk_tbl_address_rsdt_delete` auto_increment = 0;

DELETE FROM `wk_tbl_address_rsdt_mark`;
ALTER TABLE `wk_tbl_address_rsdt_mark` auto_increment = 0;


INSERT INTO `wk_tbl_address_rsdt_file` (`wk_tbl_address_rsdt_file_id`,`lg_code`,`postalcode1`,`postalcode2`,`is_latest`,`address_block`,`address_building`,`machiaza_id`,`blk_id`,`prc_id`,`rsdt_id`,`rsdt2_id`,`effect_date`,`abolish_date`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)
VALUES
   (245,'45202','777','8888',1,'札幌市豊平区月寒東五条十八丁目aaa17番地11号','99号室','0013018','334','017','556','778','2022-11-19','2041-02-06',196,190,'管理人　太郎','2026-05-27 06:21:10',0,0,'','1948-07-28 23:59:59')
 , (246,'45202','777','8888',0,'札幌市豊平区月寒東五条十八丁目aaa17番地11号','99号室','0013018','334','017','556','778','2022-11-19','2041-02-06',196,190,'管理人　太郎','2026-05-27 06:21:10',0,0,'','1948-07-28 23:59:59')
 , (247,'45202','777','8888',1,'札幌市豊平区月寒東五条十八丁目aaa17番地11号','99号室','0013018','334','017','556','778','2022-11-19','2041-02-06',113,266,'管理人　太郎','2026-05-27 06:21:10',0,0,'','1948-07-28 23:59:59')
 , (248,'45202','777','8888',1,'札幌市豊平区月寒東五条十八丁目aaa17番地11号','99号室','0013018','334','017','556','778','2022-11-19','2041-02-06',196,190,'管理人　太郎','2026-05-27 06:21:10',0,0,'','1948-07-28 23:59:59')
;


INSERT INTO `wk_tbl_address_rsdt_change` (`wk_tbl_address_rsdt_change_id`,`address_rsdt_id`,`lg_code`,`postalcode1`,`postalcode2`,`is_latest`,`address_block`,`address_building`,`machiaza_id`,`blk_id`,`prc_id`,`rsdt_id`,`rsdt2_id`,`effect_date`,`abolish_date`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) 
  VALUES 
      (331,2,'827637','','',1,'北海道石狩郡当別町425番地の37','','1','2','3','4','5','1948-07-28','1948-07-28',196,460,'管理人　太郎','2026-05-30 06:24:32',0,0,'','1948-07-28 23:59:59')
    , (332,0,'827637','','',1,'北海道石狩郡当別町425番地37号','','9','2','3','4','5','1948-07-28','1948-07-28',196,190,'管理人　太郎','2026-05-30 06:24:32',0,0,'','1948-07-28 23:59:59')
    , (333,0,'827637','','',1,'北海道石狩郡当別町425番地37号','','1','9','3','4','5','1948-07-28','1948-07-28',196,190,'管理人　太郎','2026-05-30 06:24:32',0,0,'','1948-07-28 23:59:59')
    , (334,0,'827637','','',1,'北海道石狩郡当別町425番地37号','','1','2','9','4','5','1948-07-28','1948-07-28',196,190,'管理人　太郎','2026-05-30 06:24:32',0,0,'','1948-07-28 23:59:59')
    , (335,0,'827637','','',1,'北海道石狩郡当別町425番地37号','','1','2','3','9','5','1948-07-28','1948-07-28',196,190,'管理人　太郎','2026-05-30 06:24:32',0,0,'','1948-07-28 23:59:59')
    ;

INSERT INTO `wk_tbl_address_rsdt_delete` (`wk_tbl_address_rsdt_delete_id`,`address_rsdt_id`,`lg_code`,`postalcode1`,`postalcode2`,`is_latest`,`address_block`,`address_building`,`machiaza_id`,`blk_id`,`prc_id`,`rsdt_id`,`rsdt2_id`,`effect_date`,`abolish_date`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) 
   VALUES 
      (522,140,'452020','','',1,'札幌市豊平区月寒東五条十八丁目aaa17番地11号','','0013018','334','017','556','245','1948-07-28','1948-07-28',196,190,'管理人　太郎','2026-05-30 07:55:54',0,0,'','1948-07-28 23:59:59')
    , (523,141,'452020','','',1,'','','0013018','334','017','556','246','1948-07-28','1948-07-28',196,190,'管理人　太郎','2026-05-30 07:55:54',0,0,'','1948-07-28 23:59:59')
    , (524,143,'452020','','',1,'','','013018','334','017','556','247','1948-07-28','1948-07-28',196,190,'管理人　太郎','2026-05-30 07:55:54',0,0,'','1948-07-28 23:59:59')
    , (525,144,'452020','','',1,'','','0013018','1334','017','556','247','1948-07-28','1948-07-28',196,335,'管理人　太郎','2026-05-30 07:55:54',0,0,'','1948-07-28 23:59:59')
    , (526,5,'452020','','',1,'','','0013018','334','1017','556','247','1948-07-28','1948-07-28',196,190,'管理人　太郎','2026-05-30 07:55:54',0,0,'','1948-07-28 23:59:59')
;
      
INSERT INTO `wk_tbl_address_rsdt_mark` (`wk_tbl_address_rsdt_mark_id`,`is_latest`,`wl_rsdt_change_id`,`wl_rsdt_delete_id`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) 
    VALUES 
        (365,1,100,100,196,190,'管理人　太郎','2025-12-05 12:34:56',0,0,'','1947-07-28 23:59:59')
      , (366,1,100,100,196,190,'管理人　太郎','2025-12-05 12:34:56',0,0,'','1947-07-28 23:59:59')
      , (367,1,100,100,196,297,'管理人　太郎','2025-12-05 12:34:56',0,0,'','1947-07-28 23:59:59')
   ;
      
      