package net.seijishikin.jp.normalize.manage.kanrensha.service.z_force;

import java.io.IOException;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.manage.kanrensha.constants.MasterCsvFileNameConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.MasterCsvFileNameConstants.MasterStd;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.task.InsertTaskPlanResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.z_force.ForceDumpCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.file.CompressZipPointedFileLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.file.CreateMasterCompressFilePathLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;

/**
 * マスタ標準ダンプ非同期処理Service
 */
@Service
public class AsyncForceDumpStdMasterService {

    /** ダンプ企業／団体履歴Service */
    @Autowired
    private ForceDumpStdMasterKigyouDtService forceDumpStdMasterKigyouDtService;

    /** ダンプ個人履歴Service */
    @Autowired
    private ForceDumpStdMasterPersonService forceDumpStdMasterPersonService;

    /** ダンプ政治団体履歴Service */
    @Autowired
    private ForceDumpStdMasterSeijidantaiService forceDumpStdMasterSeijidantaiService;

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

        LocalDate endDate = capsuleDto.getDateEnd();

        if (capsuleDto.getIsExecuteKigyouDt()) {
            forceDumpStdMasterKigyouDtService.practice(year, planDto1, endDate, capsuleDto.getUserDto());
        }
        if (capsuleDto.getIsExecutePerson()) {
            forceDumpStdMasterPersonService.practice(year, planDto2, endDate, capsuleDto.getUserDto());
        }
        if (capsuleDto.getIsExecuteSeijidantai()) {
            forceDumpStdMasterSeijidantaiService.practice(year, planDto3, endDate, capsuleDto.getUserDto());
        }

        try {
            // 生成したファイルを圧縮
            compressZipPointedFileLogic.practice(
                    createMasterCompressFilePathLogic.practiceZipFile(MasterStd.MASTER_STD_ZIP),
                    createMasterCompressFilePathLogic.practiceFileList(MasterCsvFileNameConstants.FOLDER_MASTER,
                            MasterStd.MASTER_STD_KIGYOU, MasterStd.MASTER_STD_PERSON,
                            MasterStd.MASTER_STD_SEIJIDANTAI));
        } catch (IOException exception) {
            saveStackTraceService.practice(exception, year, planDto1.getTaskPlanCode());
        }

    }

}
