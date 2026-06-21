package net.seijishikin.jp.normalize.manage.kanrensha.service.address_rsdt;

import java.time.LocalDateTime;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.CreateUserLeastDtoByBatchParamUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.address.lgcode.ChangePrepareAddressRsdtBatchConfiguration;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.task_plan.RecordTaskPlanJobExecutionListner;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.FileTypeConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.storage_file.StorageFileDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.task.InsertTaskPlanResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.file.ModifyTempToStorageFileService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;

/**
 * 住所差分作成準備非同期起動Service
 */
@Service
public class SabunPreparAddressRsdtService {

    /** 起動をするJob */
    @Qualifier(ChangePrepareAddressRsdtBatchConfiguration.JOB_NAME)
    @Autowired
    private Job changePrepareAddressRsdt;

    /** 起動をつかさどるランチャー */
    @Autowired
    private JobLauncher jobLauncher;

    /** 一時ファイルから正式ファイル専用Service */
    @Autowired
    private ModifyTempToStorageFileService modifyTempToStorageFileService;

    /** Stacktrace保存Service */
    @Autowired
    private SaveStackTraceService saveStackTraceService;

    /**
     * 処理を行う
     * 
     * @param userDto       ユーザ最小限Dto
     * @param taskPlanDto   タスク計画Dto
     * @param fileRsdtDto   RsdtファイルDto
     * @param fileParcelDto ParcelファイルDto
     */
    @Async
    public void practice(final LeastUserDto userDto, final InsertTaskPlanResultDto taskPlanDto,
            final StorageFileDto fileRsdtDto, final StorageFileDto fileParcelDto, final String lgCode) {

        Integer year = taskPlanDto.getTaskYear();
        try {

            JobParameters jobParameters = new JobParametersBuilder(
                    changePrepareAddressRsdt.getJobParametersIncrementer().getNext(new JobParameters())) // NOPMD
                    .addLocalDateTime("executeTime", LocalDateTime.now()) //
                    .addString("readFilePathParcel",
                            modifyTempToStorageFileService.practice(year, userDto, fileParcelDto,
                                    FileTypeConstants.FILE_TYPE)) //
                    .addString("readFilePathRsdt",
                            modifyTempToStorageFileService.practice(year, userDto, fileRsdtDto,
                                    FileTypeConstants.FILE_TYPE)) //
                    .addString("lgCode", lgCode) //
                    .addLong(CreateUserLeastDtoByBatchParamUtil.USER_ID_PARAM,
                            Long.parseLong(userDto.getUserPersonId().toString()))
                    .addLong(CreateUserLeastDtoByBatchParamUtil.USER_CODE_PARAM,
                            Long.parseLong(userDto.getUserPersonCode().toString()))
                    .addString(CreateUserLeastDtoByBatchParamUtil.USER_NAME_PARAM, userDto.getUserPersonName())
                    .addLong(RecordTaskPlanJobExecutionListner.KEY_YEAR, (long) year)
                    .addLong(RecordTaskPlanJobExecutionListner.KEY_ID, (long) taskPlanDto.getTaskPlanId())
                    .addLong(RecordTaskPlanJobExecutionListner.KEY_CODE, (long) taskPlanDto.getTaskPlanCode())
                    .toJobParameters();

            jobLauncher.run(changePrepareAddressRsdt, jobParameters);

        } catch (Exception exception) { // NOPMD 業務的な理由から積極的に許容
            // ここで補足できる例外はバッチ起動に関する例外のみで、バッチ動作に関する例外は別で処理する
            saveStackTraceService.practice(exception, year, taskPlanDto.getTaskPlanCode());
        }
    }

}
