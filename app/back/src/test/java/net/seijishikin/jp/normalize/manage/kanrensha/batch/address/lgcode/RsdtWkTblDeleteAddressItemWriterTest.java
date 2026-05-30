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
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressRsdtBaseEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblAddressRsdtDeleteEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblAddressRsdtDeleteRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * RsdtWkTblDeleteAddressItemWriter単体テスト
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("RsdtWkTblDeleteAddressItemWriterTest.sql")
class RsdtWkTblDeleteAddressItemWriterTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private RsdtWkTblDeleteAddressItemWriter rsdtWkTblDeleteAddressItemWriter;

    /** アドレス・ベース・レジストリ削除ワークテーブルRepository */
    @Autowired
    private WkTblAddressRsdtDeleteRepository wkTblAddressRsdtDeleteRepository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        // コードは一致するが建物住所が入力されているデータしかなく、番地代表住所が存在しないので削除対象とカウント
        AddressRsdtBaseEntity baseEntity0 = new AddressRsdtBaseEntity();
        baseEntity0.setLgCode("452020");
        baseEntity0.setAddressRsdtId(140);
        baseEntity0.setMachiazaId("0013018");
        baseEntity0.setBlkId("334");
        baseEntity0.setPrcId("017");
        baseEntity0.setRsdtId("556");
        baseEntity0.setRsdt2Id("245");
        baseEntity0.setAddressBlock("札幌市豊平区月寒東五条十八丁目aaa17番地11号");
        baseEntity0.setAddressBuilding("");

        // 履歴が最新でないので削除とカウント
        AddressRsdtBaseEntity baseEntity1 = new AddressRsdtBaseEntity();
        baseEntity1.setLgCode("452020");
        baseEntity1.setAddressRsdtId(141);
        baseEntity1.setMachiazaId("0013018");
        baseEntity1.setBlkId("334");
        baseEntity1.setPrcId("017");
        baseEntity1.setRsdtId("556");
        baseEntity1.setRsdt2Id("246");

        // 条件を満たすので削除対象でない
        AddressRsdtBaseEntity baseEntity2 = new AddressRsdtBaseEntity();
        baseEntity2.setLgCode("452020");
        baseEntity2.setAddressRsdtId(142);
        baseEntity2.setMachiazaId("0013018");
        baseEntity2.setBlkId("334");
        baseEntity2.setPrcId("017");
        baseEntity2.setRsdtId("556");
        baseEntity2.setRsdt2Id("247");

        // コードが違う1
        AddressRsdtBaseEntity baseEntity3 = new AddressRsdtBaseEntity();
        baseEntity3.setLgCode("452020");
        baseEntity3.setAddressRsdtId(143);
        baseEntity3.setMachiazaId("013018");
        baseEntity3.setBlkId("334");
        baseEntity3.setPrcId("017");
        baseEntity3.setRsdtId("556");
        baseEntity3.setRsdt2Id("247");

        // コードが違う2
        AddressRsdtBaseEntity baseEntity4 = new AddressRsdtBaseEntity();
        baseEntity4.setLgCode("452020");
        baseEntity4.setAddressRsdtId(144);
        baseEntity4.setMachiazaId("0013018");
        baseEntity4.setBlkId("1334");
        baseEntity4.setPrcId("017");
        baseEntity4.setRsdtId("556");
        baseEntity4.setRsdt2Id("247");

        // コードが違う3
        AddressRsdtBaseEntity baseEntity5 = new AddressRsdtBaseEntity();
        baseEntity5.setLgCode("452020");
        baseEntity5.setAddressRsdtId(5);
        baseEntity5.setMachiazaId("0013018");
        baseEntity5.setBlkId("334");
        baseEntity5.setPrcId("1017");
        baseEntity5.setRsdtId("556");
        baseEntity5.setRsdt2Id("247");

        // コードが違う4
        AddressRsdtBaseEntity baseEntity6 = new AddressRsdtBaseEntity();
        baseEntity6.setLgCode("452020");
        baseEntity6.setAddressRsdtId(6);
        baseEntity6.setMachiazaId("0013018");
        baseEntity6.setBlkId("334");
        baseEntity6.setPrcId("017");
        baseEntity6.setRsdtId("1556");
        baseEntity6.setRsdt2Id("247");

        // コードが違う5
        AddressRsdtBaseEntity baseEntity7 = new AddressRsdtBaseEntity();
        baseEntity7.setLgCode("452020");
        baseEntity7.setAddressRsdtId(7);
        baseEntity7.setMachiazaId("0013018");
        baseEntity7.setBlkId("334");
        baseEntity7.setPrcId("017");
        baseEntity7.setRsdtId("556");
        baseEntity7.setRsdt2Id("999");

        // ユーザが違う
        AddressRsdtBaseEntity baseEntity8 = new AddressRsdtBaseEntity();
        baseEntity8.setLgCode("452020");
        baseEntity8.setAddressRsdtId(8);
        baseEntity8.setMachiazaId("0013018");
        baseEntity8.setBlkId("334");
        baseEntity8.setPrcId("017");
        baseEntity8.setRsdtId("556");
        baseEntity8.setRsdt2Id("248");

        List<AddressRsdtBaseEntity> list = new ArrayList<>();
        list.add(baseEntity0);
        list.add(baseEntity1);
        list.add(baseEntity2);
        list.add(baseEntity3);
        list.add(baseEntity4);
        list.add(baseEntity5);
        list.add(baseEntity6);
        list.add(baseEntity7);
        list.add(baseEntity8);

        // Chunkを作成してセット
        Chunk<? extends AddressRsdtBaseEntity> items = new Chunk<>(list);

        rsdtWkTblDeleteAddressItemWriter.beforeStep(getStepExecution());
        rsdtWkTblDeleteAddressItemWriter.write(items);

        List<WkTblAddressRsdtDeleteEntity> listAns = wkTblAddressRsdtDeleteRepository.findAll();
        assertEquals(8, listAns.size());

        WkTblAddressRsdtDeleteEntity deleteEntity0 = listAns.get(0);
        assertEquals(baseEntity0.getMachiazaId(), deleteEntity0.getMachiazaId());
        assertEquals(baseEntity0.getBlkId(), deleteEntity0.getBlkId());
        assertEquals(baseEntity0.getPrcId(), deleteEntity0.getPrcId());
        assertEquals(baseEntity0.getRsdtId(), deleteEntity0.getRsdtId());
        assertEquals(baseEntity0.getRsdt2Id(), deleteEntity0.getRsdt2Id());
        assertEquals(baseEntity0.getAddressBlock(), deleteEntity0.getAddressBlock());
        assertEquals(baseEntity0.getAddressBuilding(), deleteEntity0.getAddressBuilding());

        assertEquals(baseEntity0.getLgCode(), deleteEntity0.getLgCode());
        assertEquals(baseEntity0.getAddressRsdtId(), deleteEntity0.getAddressRsdtId());

        // 出力結果を目視してのち削除が望ましいが、何も考えずに削除の場合、地方自治体コードとidさえわかれば削除できる

        WkTblAddressRsdtDeleteEntity deleteEntity1 = listAns.get(1);
        assertEquals(baseEntity1.getLgCode(), deleteEntity1.getLgCode());
        assertEquals(baseEntity1.getAddressRsdtId(), deleteEntity1.getAddressRsdtId());

        WkTblAddressRsdtDeleteEntity deleteEntity2 = listAns.get(2);
        assertEquals(baseEntity3.getLgCode(), deleteEntity2.getLgCode());
        assertEquals(baseEntity3.getAddressRsdtId(), deleteEntity2.getAddressRsdtId());

        WkTblAddressRsdtDeleteEntity deleteEntity3 = listAns.get(3);
        assertEquals(baseEntity4.getLgCode(), deleteEntity3.getLgCode());
        assertEquals(baseEntity4.getAddressRsdtId(), deleteEntity3.getAddressRsdtId());

        WkTblAddressRsdtDeleteEntity deleteEntity4 = listAns.get(4);
        assertEquals(baseEntity5.getLgCode(), deleteEntity4.getLgCode());
        assertEquals(baseEntity5.getAddressRsdtId(), deleteEntity4.getAddressRsdtId());

        WkTblAddressRsdtDeleteEntity deleteEntity5 = listAns.get(5);
        assertEquals(baseEntity6.getLgCode(), deleteEntity5.getLgCode());
        assertEquals(baseEntity6.getAddressRsdtId(), deleteEntity5.getAddressRsdtId());

        WkTblAddressRsdtDeleteEntity deleteEntity6 = listAns.get(6);
        assertEquals(baseEntity7.getLgCode(), deleteEntity6.getLgCode());
        assertEquals(baseEntity7.getAddressRsdtId(), deleteEntity6.getAddressRsdtId());

        WkTblAddressRsdtDeleteEntity deleteEntity7 = listAns.get(7);
        assertEquals(baseEntity8.getLgCode(), deleteEntity7.getLgCode());
        assertEquals(baseEntity8.getAddressRsdtId(), deleteEntity7.getAddressRsdtId());
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
