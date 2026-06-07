package net.seijishikin.jp.normalize.manage.kanrensha.controller.postal;

import org.springframework.beans.factory.annotation.Autowired;

import net.seijishikin.jp.normalize.manage.kanrensha.service.postal.UpdateWkTblPostalcodeService;

// 郵便番号ワークテーブルを編集する(実質作業終了をマークするだけ？ほぼ使わない？)
public class UpdateWkTblPostalcodeController {

    /** 郵便番号差分ワークテーブル更新Service */
    @Autowired
    private UpdateWkTblPostalcodeService wkTblPostalcodeService;
}
