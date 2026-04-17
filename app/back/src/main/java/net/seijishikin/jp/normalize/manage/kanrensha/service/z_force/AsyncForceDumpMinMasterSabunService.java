package net.seijishikin.jp.normalize.manage.kanrensha.service.z_force;

import java.io.IOException;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.manage.kanrensha.constants.MasterCsvFileNameConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.MasterCsvFileNameConstants.SabunMasterMin;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.task.InsertTaskPlanResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.task.TaskPlanInfoDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.z_force.ForceDumpCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.file.CompressZipPointedFileLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.file.CreateMasterCompressFilePathLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;

/**
 * マスタ最小差分ダンプ非同期処理Service
 */
@Service
public class AsyncForceDumpMinMasterSabunService {

    /** ダンプ企業／団体履歴Service */
    @Autowired
    private ForceSabunDumpMinMasterKigyouDtService forceSabunDumpMinMasterKigyouDtService;

    /** ダンプ個人履歴Service */
    @Autowired
    private ForceSabunDumpMinMasterPersonService forceSabunDumpMinMasterPersonService;

    /** ダンプ政治団体履歴Service */
    @Autowired
    private ForceSabunDumpMinMasterSeijidantaiService forceSabunDumpMinMasterSeijidantaiService;

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
    public void practice(final Integer year, final InsertTaskPlanResultDto planDto1, final InsertTaskPlanResultDto planDto2,
            final InsertTaskPlanResultDto planDto3, final ForceDumpCapsuleDto capsuleDto) {

        LocalDate startDate = capsuleDto.getDateStart();
        LocalDate endDate = capsuleDto.getDateEnd();

        if (capsuleDto.getIsExecuteKigyouDt()) {
            forceSabunDumpMinMasterKigyouDtService.practice(year, planDto1, startDate, endDate,
                    capsuleDto.getUserDto());
        }
        if (capsuleDto.getIsExecutePerson()) {
            forceSabunDumpMinMasterPersonService.practice(year, planDto2, startDate, endDate, capsuleDto.getUserDto());
        }
        if (capsuleDto.getIsExecuteSeijidantai()) {
            forceSabunDumpMinMasterSeijidantaiService.practice(year, planDto3, startDate, endDate,
                    capsuleDto.getUserDto());
        }

        try {
            // 生成したファイルを圧縮
            compressZipPointedFileLogic.practice(
                    createMasterCompressFilePathLogic.practiceZipFile(SabunMasterMin.SABUN_MIN_ZIP),
                    createMasterCompressFilePathLogic.practiceFileList(MasterCsvFileNameConstants.FOLDER_MASTER_SABUN,
                            SabunMasterMin.SABUN_MIN_KIGYOU, SabunMasterMin.SABUN_MIN_PERSON,
                            SabunMasterMin.SABUN_MIN_SEIJIDANTAI));

        } catch (IOException exception) {
            saveStackTraceService.practice(exception, year, planDto1.getTaskPlanCode());
        }

    }

}
