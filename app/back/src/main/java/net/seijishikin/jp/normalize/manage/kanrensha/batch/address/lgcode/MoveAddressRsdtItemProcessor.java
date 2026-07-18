package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.lgcode;

import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.listener.StepExecutionListener;
import org.springframework.batch.core.step.StepExecution;
import org.springframework.batch.infrastructure.item.ItemProcessor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressRsdtBaseEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressRsdtTemplateEntity;

/**
 * 地方自治体コード移動ItemProcessor
 */
@Component
public class MoveAddressRsdtItemProcessor
        implements ItemProcessor<AddressRsdtBaseEntity, AddressRsdtTemplateEntity>, StepExecutionListener {

    /** 複写地方自治体コード */
    private String copyLgCode;
    /** 複写地方自治体名 */
    private String copyLgName;
    /** 移動地方自治体名 */
    private String srcLgName;

    /**
     * 必要パラメータの取得
     */
    @BeforeStep
    @Override
    public void beforeStep(final StepExecution stepExecution) {
        copyLgCode = stepExecution.getJobParameters().getString("copyLgCode");
        copyLgName = stepExecution.getJobParameters().getString("copyLgName");
        srcLgName = stepExecution.getJobParameters().getString("srcLgName");
    }

    /**
     * 変換処理を実行する
     */
    @Override
    public AddressRsdtTemplateEntity process(final AddressRsdtBaseEntity item) throws Exception {

        AddressRsdtTemplateEntity entity = new AddressRsdtTemplateEntity();
        BeanUtils.copyProperties(item, entity);
        entity.setLgCode(copyLgCode);
        entity.setAddressBlock(entity.getAddressBlock().replaceAll(srcLgName, copyLgName));
        return entity;
    }

}
