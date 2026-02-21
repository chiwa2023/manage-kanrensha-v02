package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.lgcode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.LocalDate;
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
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressAllCityEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblAddressCityEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.AddressAllCityRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblAddressCityRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * AddressAllCityItemWriter単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("AddressAllCityItemWriterTest.sql")
class AddressAllCityItemWriterTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private AddressAllCityItemWriter addressAllCityItemWriter;

    /** 市区町村テーブルRepository */
    @Autowired
    private AddressAllCityRepository addressAllCityRepository;

    /** 市区町村ワークテーブルRepository */
    @Autowired
    private WkTblAddressCityRepository wkTblAddressCityRepository;

    @Test
    @Tag("TableTruncate")
    void testAdd() {

        // 完全新規
        AddressAllCityEntity entity00 = new AddressAllCityEntity();
        entity00.setLgCode("965314");
        entity00.setAddressName("テスト県テスト郡");
        entity00.setAddressNameKana("てすとけんてすとぐん");
        entity00.setEffectDate(LocalDate.of(1948, 7, 29));
        entity00.setAbolishDate(LocalDate.of(2038, 1, 2));

        List<AddressAllCityEntity> list = new ArrayList<>();
        list.add(entity00);

        // 同一内容が存在するのでワークテーブル登録のみ
        AddressAllCityEntity entity01 = new AddressAllCityEntity();
        entity01.setLgCode("36852");
        entity01.setAddressName("和歌山県実在市");
        entity01.setAddressNameKana("わかやまけんじつざいし");
        entity01.setEffectDate(LocalDate.of(1948, 7, 30));
        entity01.setAbolishDate(LocalDate.of(2038, 1, 3));

        list.add(entity01);

        // 同コードが複数存在するので今回データを問答無用で最新にする(登録内容は無視)
        AddressAllCityEntity entity02 = new AddressAllCityEntity();
        entity02.setLgCode("69512");
        entity02.setAddressName("宮崎県架空市");
        entity02.setAddressNameKana("みやざきけんかくうし");
        entity02.setEffectDate(LocalDate.of(1948, 7, 29));
        entity02.setAbolishDate(LocalDate.of(2038, 1, 2));
        list.add(entity02);

        // 同コードが単一存在し、内容が異なる場合は入れ替え
        // (町村合併で湖畔町がなくなるので、なくなる日である廃止日が追加された)
        AddressAllCityEntity entity03 = new AddressAllCityEntity();
        entity03.setLgCode("82763");
        entity03.setAddressName("山梨県湖畔町");
        entity03.setAddressNameKana("やまなしけんこはんまち");
        entity03.setEffectDate(LocalDate.of(1948, 6, 29));
        entity03.setAbolishDate(LocalDate.of(2038, 1, 2));
        list.add(entity03);

        // Chunkを作成してセット
        Chunk<? extends AddressAllCityEntity> items = new Chunk<>(list);

        addressAllCityItemWriter.beforeStep(this.getStepExecution());
        addressAllCityItemWriter.write(items);

        AddressAllCityEntity answerEntity00 = addressAllCityRepository.findById(225).get();

        assertEquals(entity00.getLgCode(), answerEntity00.getLgCode());
        assertEquals(entity00.getAddressName(), answerEntity00.getAddressName());
        assertEquals(entity00.getAddressNameKana(), answerEntity00.getAddressNameKana());
        assertEquals(entity00.getEffectDate(), answerEntity00.getEffectDate());
        assertEquals(entity00.getAbolishDate(), answerEntity00.getAbolishDate());

        AddressAllCityEntity answerEntity02 = addressAllCityRepository.findById(226).get();

        assertEquals(entity02.getLgCode(), answerEntity02.getLgCode());
        assertEquals(entity02.getAddressName(), answerEntity02.getAddressName());
        assertEquals(entity02.getAddressNameKana(), answerEntity02.getAddressNameKana());
        assertEquals(entity02.getEffectDate(), answerEntity02.getEffectDate());
        assertEquals(entity02.getAbolishDate(), answerEntity02.getAbolishDate());

        AddressAllCityEntity answerEntity03 = addressAllCityRepository.findById(227).get();

        assertEquals(entity03.getLgCode(), answerEntity03.getLgCode());
        assertEquals(entity03.getAddressName(), answerEntity03.getAddressName());
        assertEquals(entity03.getAddressNameKana(), answerEntity03.getAddressNameKana());
        assertEquals(entity03.getEffectDate(), answerEntity03.getEffectDate());
        assertEquals(entity03.getAbolishDate(), answerEntity03.getAbolishDate());

        // 複数データなのですべて履歴
        assertFalse(addressAllCityRepository.findById(221).get().getIsLatest());
        assertFalse(addressAllCityRepository.findById(222).get().getIsLatest());
        assertFalse(addressAllCityRepository.findById(223).get().getIsLatest());

        // 変更があったので履歴
        assertFalse(addressAllCityRepository.findById(224).get().getIsLatest());

        // ここからワークテーブル
        List<WkTblAddressCityEntity> listWkTbl = wkTblAddressCityRepository.findAll();
        assertEquals(4, listWkTbl.size(), "4件登録");

        WkTblAddressCityEntity wktblEntity00 = listWkTbl.get(0);
        assertEquals(entity00.getLgCode(), wktblEntity00.getLgCode());

        WkTblAddressCityEntity wktblEntity01 = listWkTbl.get(1);
        assertEquals(entity01.getLgCode(), wktblEntity01.getLgCode());

        WkTblAddressCityEntity wktblEntity02 = listWkTbl.get(2);
        assertEquals(entity02.getLgCode(), wktblEntity02.getLgCode());

        WkTblAddressCityEntity wktblEntity03 = listWkTbl.get(3);
        assertEquals(entity03.getLgCode(), wktblEntity03.getLgCode());

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
