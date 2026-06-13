package net.seijishikin.jp.normalize.manage.kanrensha.service.postal;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;
import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.postal.SavePostalIrregularCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalIrregularEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.AddressPostalIrregularRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.AddressPostalRepository;

/**
 * 郵便番号建物元住所複写Service
 */
@Service
public class SavePostalIrregularBuildingAllFloorService {

    /** 郵便番号不規則データRepository */
    @Autowired
    private AddressPostalIrregularRepository addressPostalIrregularRepository;

    /** 郵便番号正規データRepository */
    @Autowired
    private AddressPostalRepository addressPostalRepository;

    /** テーブル履歴設定Util */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /**
     * 処理を行う
     *
     * @param capsuleDto 編集内容Dto
     * @return 処理結果Dto
     */
    @Transactional
    public FrameworkMessageAndResultDto practice(final SavePostalIrregularCapsuleDto capsuleDto) {

        AddressPostalIrregularEntity entityRoot = capsuleDto.getAddressPostalIrregularEntity();

        // 紐づく郵便番号住所を取得して設定された値をコピー
        List<AddressPostalIrregularEntity> listDetail = addressPostalIrregularRepository
                .findByAddressNameAndIsLatestTrue(entityRoot.getAddressName());

        List<AddressPostalIrregularEntity> listChange = new ArrayList<>();
        LeastUserDto userDto = capsuleDto.getUserDto();
        List<AddressPostalEntity> listRegular = new ArrayList<>();
        for (AddressPostalIrregularEntity entityDetail : listDetail) {

            // 変更内容を新規で保存
            listChange.add(this.createCopyAddressEntity(entityDetail, entityRoot, userDto));

            // 既存データは履歴に
            setTableDataHistoryUtil.practiceDelete(userDto, entityDetail);
            listChange.add(entityDetail);

            // 同じ郵便番号の正規は不規則呼び出しに変更
            listRegular.addAll(this.createCopyAddressEntity(entityDetail.getPostalcode1(),
                    entityDetail.getPostalcode2(), userDto));
        }

        // 正規を保存
        addressPostalRepository.saveAll(listRegular);
        
        FrameworkMessageAndResultDto resultDto = new FrameworkMessageAndResultDto();
        // メッセージ処理
        if (listChange.size() == addressPostalIrregularRepository.saveAll(listChange).size()) {
            resultDto.setMessage("保存しました");
        } else {
            resultDto.setIsFailure(true);
            resultDto.setMessage("正常に保存できませんでした");
        }

        return resultDto;
    }

    private AddressPostalIrregularEntity createCopyAddressEntity(final AddressPostalIrregularEntity entityDetail,
            final AddressPostalIrregularEntity entityRoot, final LeastUserDto userDto) {

        AddressPostalIrregularEntity entity = new AddressPostalIrregularEntity();
        BeanUtils.copyProperties(entityDetail, entity);

        // 番地まで住所を複写
        entity.setAddressPostal(entityRoot.getAddressPostal());
        entity.setAddressBlock(entityRoot.getAddressBlock());
        
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setAddressPostalIrregularId(0); // auto increment明記

        return entity;
    }

    private List<AddressPostalEntity> createCopyAddressEntity(final String postlcode1, final String postalcode2,
            final LeastUserDto userDto) {

        List<AddressPostalEntity> list = new ArrayList<>();
        List<AddressPostalEntity> listSamePostal = addressPostalRepository
                .findByPostalcode1AndPostalcode2AndIsLatestTrueOrderByAddressNameAsc(postlcode1, postalcode2);

        for (AddressPostalEntity entity : listSamePostal) {

            // 住居を見に行かない値を最新に
            list.add(this.createnotGyouseikuEntity(entity, userDto));

            // 現在データは履歴
            setTableDataHistoryUtil.practiceDelete(userDto, entity);
            list.add(entity);
        }

        return list;
    }

    private AddressPostalEntity createnotGyouseikuEntity(final AddressPostalEntity entitySrc,
            final LeastUserDto userDto) {

        AddressPostalEntity entity = new AddressPostalEntity();
        BeanUtils.copyProperties(entitySrc, entity);
        // 番地まで住所は住居を見に行かない
        entity.setIsGyoseikuData(false);

        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setAddressPostalId(0); // auto increment明記

        return entity;
    }

}
