package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.xml; //NOPMD

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.test.MetaDataInstanceFactory;
import org.springframework.beans.BeanUtils;
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
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaKigyouDtMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaSeijidantaiMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblMasterAllByXmlEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblMasterAllByXmlResultEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory01Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaPersonHistory01Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaSeijidantaiHistory01Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaKigyouDtMasterRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaPersonMasterRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaSeijidantaiMasterRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblMasterAllByXmlRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblMasterAllByXmlResultRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaKigyouDtHistory01Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaPersonHistory01Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaSeijidantaiHistory01Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * KanrenshaByXmlMinRecordItemWriter単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("KanrenshaByXmlMinRecordItemWriterTest.sql")
class KanrenshaByXmlMinRecordItemWriterTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private KanrenshaByXmlMinRecordItemWriter KanrenshaByXmlMinRecordItemWriter;

    /** 関連者企業・団体ワークテーブル判定Repository */
    @Autowired
    private WkTblMasterAllByXmlRepository wkTblMasterAllByXmlRepository;

    /** 関連者企業・団体ワークテーブル判定Repository */
    @Autowired
    private WkTblMasterAllByXmlResultRepository wkTblMasterAllByXmlResultRepository;

    /** 関連者個人履歴(01)Repository */
    @Autowired
    private KanrenshaPersonHistory01Repository kanrenshaPersonHistory01Repository;

    /** 関連者個人マスタRepository */
    @Autowired
    private KanrenshaPersonMasterRepository kanrenshaPersonMasterRepository;

    /** 関連者企業・団体履歴(01)Repository */
    @Autowired
    private KanrenshaKigyouDtHistory01Repository kanrenshaKigyouDtHistory01Repository;

    /** 関連者企業・団体マスタRepository */
    @Autowired
    private KanrenshaKigyouDtMasterRepository kanrenshaKigyouDtMasterRepository;

    /** 関連者政治団体履歴(01)Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory01Repository kanrenshaSeijidantaiHistory01Repository;

    /** 関連者政治団体マスタRepository */
    @Autowired
    private KanrenshaSeijidantaiMasterRepository kanrenshaSeijidantaiMasterRepository;

    @Test
    @Tag("TableTruncate")
    void testPerson() {

        final Integer saveId = 1059;

        WkTblMasterAllByXmlEntity entity00 = wkTblMasterAllByXmlRepository.findById(342).get();
        WkTblMasterAllByXmlEntity entityBase = new WkTblMasterAllByXmlEntity();
        BeanUtils.copyProperties(entity00, entityBase);
        entityBase.setWkTblMasterAllByXmlId(saveId);
        entityBase.setIsAffected(false);
        entityBase.setJudgeReason("理由");

        List<WkTblMasterAllByXmlEntity> list = new ArrayList<>();
        list.add(entityBase);

        // Chunkを作成してセット
        Chunk<? extends WkTblMasterAllByXmlEntity> items = new Chunk<>(list);

        KanrenshaByXmlMinRecordItemWriter.beforeStep(this.getStepExecution());
        KanrenshaByXmlMinRecordItemWriter.write(items);

        List<WkTblMasterAllByXmlResultEntity> listAns = wkTblMasterAllByXmlResultRepository.findAll();
        assertEquals(1, list.size());
        WkTblMasterAllByXmlResultEntity entity = listAns.get(0);

        assertEquals(true, entity.getIsAffected());
        assertEquals(true, entity.getIsLatest());
        assertEquals(saveId, entity.getWkTblMasterAllByXmlId());

        // すべてのマスタと履歴テーブルとそのauto_incrementをクリアしているのでId1をで取得して登録内容を確認

        KanrenshaPersonHistory01Entity entiytHistory = kanrenshaPersonHistory01Repository.findById(1).get();
        assertEquals(entityBase.getKanrenshaName(), entiytHistory.getAllName());
        assertEquals(entityBase.getAllAddress(), entiytHistory.getAllAddress());
        assertEquals(entityBase.getPersonShokugyou(), entiytHistory.getPersonShokugyou());

        KanrenshaPersonMasterEntity entityMaster = kanrenshaPersonMasterRepository.findById(1).get();
        assertEquals(entityBase.getKanrenshaName(), entityMaster.getKanrenshaName());
        assertEquals(entityBase.getAllAddress(), entityMaster.getAllAddress());
        assertEquals(entityBase.getPersonShokugyou(), entityMaster.getPersonShokugyou());

        // 同じコードで紐づけ
        assertEquals(entityMaster.getPersonKanrenshaCode(), entiytHistory.getPersonKanrenshaCode());
    }

    @Test
    @Tag("TableTruncate")
    @Transactional
    void testCorp() {

        final Integer saveId = 1059;

        WkTblMasterAllByXmlEntity entity00 = wkTblMasterAllByXmlRepository.findById(347).get();
        WkTblMasterAllByXmlEntity entityBase = new WkTblMasterAllByXmlEntity();
        BeanUtils.copyProperties(entity00, entityBase);
        entityBase.setWkTblMasterAllByXmlId(saveId);
        entityBase.setIsAffected(false);
        entityBase.setJudgeReason("理由");

        List<WkTblMasterAllByXmlEntity> list = new ArrayList<>();
        list.add(entityBase);

        // Chunkを作成してセット
        Chunk<? extends WkTblMasterAllByXmlEntity> items = new Chunk<>(list);

        KanrenshaByXmlMinRecordItemWriter.beforeStep(this.getStepExecution());
        KanrenshaByXmlMinRecordItemWriter.write(items);

        List<WkTblMasterAllByXmlResultEntity> listAns = wkTblMasterAllByXmlResultRepository.findAll();
        assertEquals(1, list.size());
        WkTblMasterAllByXmlResultEntity entity = listAns.get(0);

        assertEquals(true, entity.getIsAffected());
        assertEquals(true, entity.getIsLatest());
        assertEquals(saveId, entity.getWkTblMasterAllByXmlId());

        // すべてのマスタと履歴テーブルとそのauto_incrementをクリアしているのでId1をで取得して登録内容を確認

        KanrenshaKigyouDtHistory01Entity entiytHistory = kanrenshaKigyouDtHistory01Repository.findById(1).get();
        assertEquals(entityBase.getKanrenshaName(), entiytHistory.getAllName());
        assertEquals(entityBase.getAllAddress(), entiytHistory.getAllAddress());
        assertEquals(entityBase.getOrgDelegate(), entiytHistory.getOrgDelegateName());

        KanrenshaKigyouDtMasterEntity entityMaster = kanrenshaKigyouDtMasterRepository.findById(1).get();
        assertEquals(entityBase.getKanrenshaName(), entityMaster.getKanrenshaName());
        assertEquals(entityBase.getAllAddress(), entityMaster.getAllAddress());
        assertEquals(entityBase.getOrgDelegate(), entityMaster.getKigyouDtDelegate());
        assertEquals(entityBase.getHoujinNo(), entityMaster.getHoujinNo());

        // 同じコードで紐づけ
        assertEquals(entityMaster.getKigyouDtKanrenshaCode(), entiytHistory.getKigyouDtKanrenshaCode());
    }

    @Test
    @Tag("TableTruncate")
    @Transactional
    void testPoliOrg() {

        final Integer saveId = 1059;

        WkTblMasterAllByXmlEntity entity00 = wkTblMasterAllByXmlRepository.findById(348).get();
        WkTblMasterAllByXmlEntity entityBase = new WkTblMasterAllByXmlEntity();
        BeanUtils.copyProperties(entity00, entityBase);
        entityBase.setWkTblMasterAllByXmlId(saveId);
        entityBase.setIsAffected(false);
        entityBase.setJudgeReason("理由");

        List<WkTblMasterAllByXmlEntity> list = new ArrayList<>();
        list.add(entityBase);

        // Chunkを作成してセット
        Chunk<? extends WkTblMasterAllByXmlEntity> items = new Chunk<>(list);

        KanrenshaByXmlMinRecordItemWriter.beforeStep(this.getStepExecution());
        KanrenshaByXmlMinRecordItemWriter.write(items);

        List<WkTblMasterAllByXmlResultEntity> listAns = wkTblMasterAllByXmlResultRepository.findAll();
        assertEquals(1, list.size());
        WkTblMasterAllByXmlResultEntity entity = listAns.get(0);

        assertEquals(true, entity.getIsAffected());
        assertEquals(true, entity.getIsLatest());
        assertEquals(saveId, entity.getWkTblMasterAllByXmlId());

        // すべてのマスタと履歴テーブルとそのauto_incrementをクリアしているのでId1をで取得して登録内容を

        KanrenshaSeijidantaiHistory01Entity entiytHistory = kanrenshaSeijidantaiHistory01Repository.findById(1).get();
        assertEquals(entityBase.getKanrenshaName(), entiytHistory.getAllName());
        assertEquals(entityBase.getAllAddress(), entiytHistory.getAllAddress());
        assertEquals(entityBase.getOrgDelegate(), entiytHistory.getOrgDelegateName());

        KanrenshaSeijidantaiMasterEntity entityMaster = kanrenshaSeijidantaiMasterRepository.findById(1).get();
        assertEquals(entityBase.getKanrenshaName(), entityMaster.getKanrenshaName());
        assertEquals(entityBase.getAllAddress(), entityMaster.getAllAddress());
        assertEquals(entityBase.getOrgDelegate(), entityMaster.getSeijidantaiDelegate());
        assertEquals(entityBase.getDantaiKbn(), entityMaster.getDantaiKbn());
        assertTrue(entityMaster.getPoliOrgNo().startsWith("0987654"));

        // 同じコードで紐づけ
        assertEquals(entityMaster.getSeijidantaiKanrenshaCode(), entiytHistory.getSeijidantaiKanrenshaCode());
    }

    private StepExecution getStepExecution() {
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        JobParameters jobParameters = new JobParametersBuilder().addLong("userId", (long) userDto.getUserPersonId())
                .addLong("userCode", (long) userDto.getUserPersonCode())
                .addString("userName", userDto.getUserPersonName()).toJobParameters();
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

}
