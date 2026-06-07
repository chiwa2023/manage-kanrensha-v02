package net.seijishikin.jp.normalize.manage.kanrensha.controller.postal;

import org.springframework.beans.factory.annotation.Autowired;

import net.seijishikin.jp.normalize.manage.kanrensha.service.postal.SearchWktblPostalCodeService;

// 差分ワークテーブルの内容を検索
public class SearchWktblPostalCodeController {

    /** 郵便番号差分ワークテーブル検索Service */
    @Autowired
    private SearchWktblPostalCodeService searchWktblPostalCodeService;
}
