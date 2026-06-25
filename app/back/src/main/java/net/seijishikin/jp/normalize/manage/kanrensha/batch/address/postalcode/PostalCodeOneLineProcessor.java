package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.postalcode;

import org.springframework.batch.core.step.StepExecution;
import org.springframework.batch.core.listener.StepExecutionListener;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.infrastructure.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.address.registory.WriteLogAddressFormatLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.postal.CheckPostalToMachiazaLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.PlusCheckDigitUtil;

/**
 * 郵便番号CSV登録Enitty変換Processor
 */
@Component
public class PostalCodeOneLineProcessor
        implements ItemProcessor<PostalCodeCsvOneLineDto, AddressPostalEntity>, StepExecutionListener {

    /** 郵便番号桁数 */
    private static final int POS_DIGIT = 7;

    /** 郵便番号前桁数 */
    private static final int POS_MAE = 3;

    /** Logger */
    @Autowired
    private WriteLogAddressFormatLogic writeLogAddressFormatLogic;

    /** アドレス・ベース・レジストリ地名確認Logic */
    @Autowired
    private CheckPostalToMachiazaLogic checkPostalToMachiazaLogic;

    /** 地方自治体コード */
    private String lgCode;

    /**
     * BeforeStep(ユーザ情報設定)
     *
     * @param stepExecution stepExecution
     */
    @BeforeStep
    @Override
    public void beforeStep(final StepExecution stepExecution) {
        lgCode = stepExecution.getJobParameters().getString("lgCode");
    }

    /**
     * 変換処理を実行する
     */
    @Override
    public AddressPostalEntity process(final PostalCodeCsvOneLineDto item) throws Exception {

        AddressPostalEntity entity = new AddressPostalEntity();

        String postalCode = item.getPostalcode();
        if (POS_DIGIT == postalCode.length()) {
            entity.setPostalcode1(postalCode.substring(0, POS_MAE));
            entity.setPostalcode2(postalCode.substring(POS_MAE, POS_DIGIT));
        } else {
            writeLogAddressFormatLogic.practice(WriteLogAddressFormatLogic.ERROR, postalCode, "郵便番号が7桁でありません");
        }

        String lgCode6 = PlusCheckDigitUtil.plusForLgCode(item.getLgCode());
        entity.setLgCode(lgCode6);

        if (lgCode6.startsWith(lgCode)) {
            entity.setAddressName(item.getPref() + item.getCity() + checkPostalToMachiazaLogic.practice(item, lgCode6)
                    + item.getAddressOrg());
            entity.setAddressOrg(item.getAddressOrg());
            entity.setIsGyoseikuData(true);
        }

        return entity;
    }

}
