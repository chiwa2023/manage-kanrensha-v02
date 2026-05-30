package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.lgcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.test.MetaDataInstanceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblAddressRsdtChangeEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblAddressRsdtFileEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblAddressRsdtChangeRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * RsdtWkTblChangeAddressItemWriter単体テスト
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("RsdtWkTblChangeAddressItemWriterTest.sql")
class RsdtWkTblChangeAddressItemWriterTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private RsdtWkTblChangeAddressItemWriter rsdtWkTblChangeAddressItemWriter;

    /** アドレス・ベース・レジストリ新規・変更用ワークテーブル */
    @Autowired
    private WkTblAddressRsdtChangeRepository wkTblAddressRsdtChangeRepository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {


        // コードが一致して住所が異なるので変更扱い
        WkTblAddressRsdtFileEntity fileEntity0 = this.getChengeEntity();
        fileEntity0.setAddressBlock("北海道石狩郡当別町425番地の37");

        // コードが一致しないので新規
        WkTblAddressRsdtFileEntity fileEntity10 = this.getChengeEntity();
        fileEntity10.setMachiazaId("9");

        WkTblAddressRsdtFileEntity fileEntity11 = this.getChengeEntity();
        fileEntity11.setBlkId("9");

        WkTblAddressRsdtFileEntity fileEntity12 = this.getChengeEntity();
        fileEntity12.setPrcId("9");

        WkTblAddressRsdtFileEntity fileEntity13 = this.getChengeEntity();
        fileEntity13.setRsdtId("9");

        WkTblAddressRsdtFileEntity fileEntity14 = this.getChengeEntity();
        fileEntity14.setRsdt2Id("9");

        // 建物名が入っている住所しかない(番地代表データが失われた・・・やりそうだけど結構な大事件)は補充
        WkTblAddressRsdtFileEntity fileEntity3 = this.getChengeEntity();
        fileEntity3.setAddressBlock("北海道石狩郡当別町1114番地7号");
        fileEntity3.setAddressBuilding("");
        fileEntity3.setMachiazaId("11");
        fileEntity3.setBlkId("12");
        fileEntity3.setPrcId("13");
        fileEntity3.setRsdtId("14");
        fileEntity3.setRsdt2Id("15");

        List<WkTblAddressRsdtFileEntity> list = new ArrayList<>();
        list.add(fileEntity0);
        list.add(fileEntity10);
        list.add(fileEntity11);
        list.add(fileEntity12);
        list.add(fileEntity13);
        list.add(fileEntity14);
        // 検索条件が一致するので無視
        WkTblAddressRsdtFileEntity fileEntity2 = this.getChengeEntity();
        list.add(fileEntity2);
        list.add(fileEntity3);

        // Chunkを作成してセット
        Chunk<? extends WkTblAddressRsdtFileEntity> items = new Chunk<>(list);

        rsdtWkTblChangeAddressItemWriter.beforeStep(getStepExecution());
        rsdtWkTblChangeAddressItemWriter.write(items);

        List<WkTblAddressRsdtChangeEntity> listAns = wkTblAddressRsdtChangeRepository.findAll();
        assertEquals(7, listAns.size());

        // 変更
        WkTblAddressRsdtChangeEntity ansEntity0 = listAns.get(0);
        assertEquals(fileEntity0.getAddressBlock(), ansEntity0.getAddressBlock());
        assertEquals(fileEntity0.getMachiazaId(), ansEntity0.getMachiazaId());
        assertEquals(fileEntity0.getBlkId(), ansEntity0.getBlkId());
        assertEquals(fileEntity0.getPrcId(), ansEntity0.getPrcId());
        assertEquals(fileEntity0.getRsdtId(), ansEntity0.getRsdtId());
        assertEquals(fileEntity0.getRsdt2Id(), ansEntity0.getRsdt2Id());
        assertEquals(2, ansEntity0.getAddressRsdtId());

        // 新規
        WkTblAddressRsdtChangeEntity ansEntity1 = listAns.get(1);
        assertEquals(fileEntity10.getAddressBlock(), ansEntity1.getAddressBlock());
        assertEquals(fileEntity10.getMachiazaId(), ansEntity1.getMachiazaId());
        assertEquals(fileEntity10.getBlkId(), ansEntity1.getBlkId());
        assertEquals(fileEntity10.getPrcId(), ansEntity1.getPrcId());
        assertEquals(fileEntity10.getRsdtId(), ansEntity1.getRsdtId());
        assertEquals(fileEntity10.getRsdt2Id(), ansEntity1.getRsdt2Id());
        assertEquals(0, ansEntity1.getAddressRsdtId());

        WkTblAddressRsdtChangeEntity ansEntity2 = listAns.get(2);
        assertEquals(fileEntity11.getAddressBlock(), ansEntity2.getAddressBlock());
        assertEquals(fileEntity11.getMachiazaId(), ansEntity2.getMachiazaId());
        assertEquals(fileEntity11.getBlkId(), ansEntity2.getBlkId());
        assertEquals(fileEntity11.getPrcId(), ansEntity2.getPrcId());
        assertEquals(fileEntity11.getRsdtId(), ansEntity2.getRsdtId());
        assertEquals(fileEntity11.getRsdt2Id(), ansEntity2.getRsdt2Id());
        assertEquals(0, ansEntity2.getAddressRsdtId());

        WkTblAddressRsdtChangeEntity ansEntity3 = listAns.get(3);
        assertEquals(fileEntity12.getAddressBlock(), ansEntity3.getAddressBlock());
        assertEquals(fileEntity12.getMachiazaId(), ansEntity3.getMachiazaId());
        assertEquals(fileEntity12.getBlkId(), ansEntity3.getBlkId());
        assertEquals(fileEntity12.getPrcId(), ansEntity3.getPrcId());
        assertEquals(fileEntity12.getRsdtId(), ansEntity3.getRsdtId());
        assertEquals(fileEntity12.getRsdt2Id(), ansEntity3.getRsdt2Id());
        assertEquals(0, ansEntity3.getAddressRsdtId());

        WkTblAddressRsdtChangeEntity ansEntity4 = listAns.get(4);
        assertEquals(fileEntity13.getAddressBlock(), ansEntity4.getAddressBlock());
        assertEquals(fileEntity13.getMachiazaId(), ansEntity4.getMachiazaId());
        assertEquals(fileEntity13.getBlkId(), ansEntity4.getBlkId());
        assertEquals(fileEntity13.getPrcId(), ansEntity4.getPrcId());
        assertEquals(fileEntity13.getRsdtId(), ansEntity4.getRsdtId());
        assertEquals(fileEntity13.getRsdt2Id(), ansEntity4.getRsdt2Id());
        assertEquals(0, ansEntity4.getAddressRsdtId());

        WkTblAddressRsdtChangeEntity ansEntity5 = listAns.get(5);
        assertEquals(fileEntity14.getAddressBlock(), ansEntity5.getAddressBlock());
        assertEquals(fileEntity14.getMachiazaId(), ansEntity5.getMachiazaId());
        assertEquals(fileEntity14.getBlkId(), ansEntity5.getBlkId());
        assertEquals(fileEntity14.getPrcId(), ansEntity5.getPrcId());
        assertEquals(fileEntity14.getRsdtId(), ansEntity5.getRsdtId());
        assertEquals(fileEntity14.getRsdt2Id(), ansEntity5.getRsdt2Id());
        assertEquals(0, ansEntity5.getAddressRsdtId());

        WkTblAddressRsdtChangeEntity ansEntity6 = listAns.get(6);
        assertEquals(fileEntity3.getAddressBlock(), ansEntity6.getAddressBlock());
        assertEquals(fileEntity3.getMachiazaId(), ansEntity6.getMachiazaId());
        assertEquals(fileEntity3.getBlkId(), ansEntity6.getBlkId());
        assertEquals(fileEntity3.getPrcId(), ansEntity6.getPrcId());
        assertEquals(fileEntity3.getRsdtId(), ansEntity6.getRsdtId());
        assertEquals(fileEntity3.getRsdt2Id(), ansEntity6.getRsdt2Id());
        assertEquals(0, ansEntity6.getAddressRsdtId());
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

    private WkTblAddressRsdtFileEntity getChengeEntity() {
        WkTblAddressRsdtFileEntity entity = new WkTblAddressRsdtFileEntity();
        entity.setLgCode("827637");
        entity.setMachiazaId("1");
        entity.setBlkId("2");
        entity.setPrcId("3");
        entity.setRsdtId("4");
        entity.setRsdt2Id("5");
        entity.setAddressBlock("北海道石狩郡当別町425番地37号");

        return entity;
    }
}
