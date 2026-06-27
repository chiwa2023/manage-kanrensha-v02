package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.lgcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.job.parameters.JobParameters;
import org.springframework.batch.core.job.parameters.JobParametersBuilder;
import org.springframework.batch.core.step.StepExecution;
import org.springframework.batch.test.MetaDataInstanceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblAddressRsdtChangeEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblAddressRsdtDeleteEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblAddressRsdtFileEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblAddressRsdtMarkEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblAddressRsdtChangeRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblAddressRsdtDeleteRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblAddressRsdtFileRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblAddressRsdtMarkRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * DeleteWkTblAddressTasklet単体テスト
 */
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("DeleteWkTblAddressTaskletTest.sql")
@Transactional
class DeleteWkTblAddressTaskletTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private DeleteWkTblAddressTasklet deleteWkTblAddressTasklet;

    /** アドレス・ベース・レジストリワークテーブルファイル登録Repository */
    @Autowired
    private WkTblAddressRsdtFileRepository wkTblAddressRsdtFileRepository;

    /** アドレス・ベース・レジストリワークテーブル更新マークRepository */
    @Autowired
    private WkTblAddressRsdtChangeRepository wkTblAddressRsdtChangeRepository;

    /** アドレス・ベース・レジストリワークテーブル削除Repository */
    @Autowired
    private WkTblAddressRsdtDeleteRepository wkTblAddressRsdtDeleteRepository;

    /** アドレス・ベース・レジストリワークテーブル処理マークRepository */
    @Autowired
    private WkTblAddressRsdtMarkRepository wkTblAddressRsdtMarkRepository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        deleteWkTblAddressTasklet.beforeStep(this.getStepExecution());
        deleteWkTblAddressTasklet.execute(null, null);

        List<WkTblAddressRsdtFileEntity> listFile = wkTblAddressRsdtFileRepository.findAll();
        assertEquals(1, listFile.size());
        assertEquals(247, listFile.get(0).getWkTblAddressRsdtFileId());

        List<WkTblAddressRsdtChangeEntity> listChange = wkTblAddressRsdtChangeRepository.findAll();
        assertEquals(1, listChange.size());
        assertEquals(331, listChange.get(0).getWkTblAddressRsdtChangeId());

        List<WkTblAddressRsdtDeleteEntity> listDelete = wkTblAddressRsdtDeleteRepository.findAll();
        assertEquals(1, listDelete.size());
        assertEquals(525, listDelete.get(0).getWkTblAddressRsdtDeleteId());

        List<WkTblAddressRsdtMarkEntity> listMark = wkTblAddressRsdtMarkRepository.findAll();
        assertEquals(1, listMark.size());
        assertEquals(367, listMark.get(0).getWkTblAddressRsdtMarkId());
    }

    private StepExecution getStepExecution() {

        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();

        JobParameters jobParameters = new JobParametersBuilder() // NOPMD
                .addLong("userId", (long) userDto.getUserPersonId())
                .addLong("userCode", (long) userDto.getUserPersonCode())
                .addString("userName", userDto.getUserPersonName()).toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

}
