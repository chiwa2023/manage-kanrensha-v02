package net.seijishikin.jp.normalize.manage.kanrensha.controller.postal;

import org.springframework.beans.factory.annotation.Autowired;

import net.seijishikin.jp.normalize.manage.kanrensha.service.postal.SearchPostalCodeService;

public class SearchPostalCodeController {

    /** 郵便番号差分ワークテーブル更新Service */
    @Autowired
    private SearchPostalCodeService searchPostalCodeService;
}
