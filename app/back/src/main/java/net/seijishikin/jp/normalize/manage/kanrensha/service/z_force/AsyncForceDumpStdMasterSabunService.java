package net.seijishikin.jp.normalize.manage.kanrensha.service.z_force;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.manage.kanrensha.constants.MasterCsvFileNameConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.MasterCsvFileNameConstants.SabunMasterStd;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.task.InsertTaskPlanResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.z_force.ForceDumpCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.file.CompressZipPointedFileLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.file.CreateMasterCompressFilePathLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;

/**
 * 履歴差分ダンプ非同期処理Service
 */
@Service
public class AsyncForceDumpStdMasterSabunService {

    /** ダンプ企業／団体履歴Service */
    @Autowired
    private ForceSabunDumpStdMasterKigyouDtService forceSabunDumpStdMasterKigyouDtService;

    /** ダンプ個人履歴Service */
    @Autowired
    private ForceSabunDumpStdMasterPersonService forceSabunDumpStdMasterPersonService;

    /** ダンプ政治団体履歴Service */
    @Autowired
    private ForceSabunDumpStdMasterSeijidantaiService forceSabunDumpStdMasterSeijidantaiService;

    /** 指定ファイル圧縮Logic */
    @Autowired
    private CompressZipPointedFileLogic compressZipPointedFileLogic;

    /** 圧縮ファイルPath取得Logic */
    @Autowired
    private CreateMasterCompressFilePathLogic createMasterCompressFilePathLogic;

    /** StackTrace保存Service */
    @Autowired
    private SaveStackTraceService saveStackTraceService;

    /**
     * 処理を行う
     *
     * @param capsuleDto 処理条件Dto
     */
    @Async
    public void practice(final Integer year, final InsertTaskPlanResultDto planDto1,
            final InsertTaskPlanResultDto planDto2, final InsertTaskPlanResultDto planDto3,
            final ForceDumpCapsuleDto capsuleDto) {

        LocalDate startDate = capsuleDto.getDateStart();
        LocalDate endDate = capsuleDto.getDateEnd();

        if (capsuleDto.getIsExecuteKigyouDt()) {
            forceSabunDumpStdMasterKigyouDtService.practice(year, planDto1, startDate, endDate,
                    capsuleDto.getUserDto());
        }
        if (capsuleDto.getIsExecutePerson()) {
            forceSabunDumpStdMasterPersonService.practice(year, planDto2, startDate, endDate, capsuleDto.getUserDto());
        }
        if (capsuleDto.getIsExecuteSeijidantai()) {
            forceSabunDumpStdMasterSeijidantaiService.practice(year, planDto3, startDate, endDate,
                    capsuleDto.getUserDto());
        }

        try {
            // 生成したファイルを圧縮
            compressZipPointedFileLogic.practice(
                    createMasterCompressFilePathLogic.practiceZipFile(SabunMasterStd.SABUN_STD_ZIP),
                    createMasterCompressFilePathLogic.practiceFileList(MasterCsvFileNameConstants.FOLDER_MASTER_SABUN,
                            SabunMasterStd.SABUN_STD_KIGYOU, SabunMasterStd.SABUN_STD_PERSON,
                            SabunMasterStd.SABUN_STD_SEIJIDANTAI));
        } catch (Exception exception) { // NOPMD 業務的な理由から積極的に許容
            saveStackTraceService.practice(exception, year, planDto1.getTaskPlanCode());
        }
    }

}
