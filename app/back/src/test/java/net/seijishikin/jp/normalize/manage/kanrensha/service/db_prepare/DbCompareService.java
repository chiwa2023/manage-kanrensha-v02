package net.seijishikin.jp.normalize.manage.kanrensha.service.db_prepare;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.manage.kanrensha.logic.db_prepare.CompareDbTableLocalToAwsLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.db_prepare.CreateTableDdlLogic;

/**
 * Db比較Service
 */
@Service
public class DbCompareService {

    /** テーブルDDL作成Logic */
    @Autowired
    private CreateTableDdlLogic createTableDdlLogic;

    /** テーブル比較Logic */
    @Autowired
    private CompareDbTableLocalToAwsLogic compareDbTableLocalToAwsLogic;

    /**
     * 処理を行う
     * 
     * @param capsuleDto 処理条件Dto
     * @throws IOException ファイル例外
     */
    public void practice(final DbCompareCapsuleDto capsuleDto) throws IOException {
        createTableDdlLogic.practice(capsuleDto);
        compareDbTableLocalToAwsLogic.practice(capsuleDto);
    }
}
