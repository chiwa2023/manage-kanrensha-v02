package net.seijishikin.jp.normalize.manage.kanrensha.service.postal;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.postal.MovePostalCodeCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalIrregularEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.AddressPostalIrregularRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.AddressPostalRepository;

/**
 * 郵便番号移動Service(郵便番号・住所一括1番号)
 */
@Service
public class WrapMovePostalcodeRsdtService {

    /** 正規郵便番号Repository */
    @Autowired
    private AddressPostalRepository addressPostalRepository;

    /** 正規郵便番号Repository */
    @Autowired
    private AddressPostalIrregularRepository addressPostalIrregularRepository;

    /** 正規郵便番号Repository */
    @Autowired
    private EntityManager entityManager;

    /** 郵便番号移動 */
    @Autowired
    private MovePostalcodeRsdtService movePostalcodeRsdtService;

    /** テーブル履歴設定Util */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /**
     * 処理を行う
     * 
     * @param capsuleDto 処理条件Dto
     * @return 正規郵便番号変更件数
     */
    @Transactional
    public Integer practice(final MovePostalCodeCapsuleDto capsuleDto) {

        String postalOld1 = capsuleDto.getPostalOld1();
        String postalOld2 = capsuleDto.getPostalOld2();

        List<AddressPostalEntity> listPostal = addressPostalRepository
                .findByPostalcode1AndPostalcode2AndIsLatestTrueOrderByAddressNameAsc(postalOld1, postalOld2);

        // 郵便番号からリストが取得できない場合は中断
        if (listPostal.isEmpty()) {
            return 0;
        }

        String postalNew1 = capsuleDto.getPostalNew1();
        String postalNew2 = capsuleDto.getPostalNew2();
        LeastUserDto userDto = capsuleDto.getUserDto();
        List<AddressPostalEntity> listPostalChange = new ArrayList<>();
        for (AddressPostalEntity entity : listPostal) {

            // 新データを編集対象に
            listPostalChange.add(this.createNewPostalEntity(entity, postalNew1, postalNew2, userDto));

            // 旧データは履歴に
            setTableDataHistoryUtil.practiceDelete(userDto, entity);
            listPostalChange.add(entity);

            // 行政区呼び出しでない場合は不規則を処理する必要がある
            this.changeIrregular(entity, postalNew1, postalNew2, userDto);
        }

        // 住居を変更
        movePostalcodeRsdtService.practice(entityManager, userDto, capsuleDto.getLgCode(), postalOld1, postalOld2,
                postalNew1, postalNew2);

        // 正規住所を登録
        return addressPostalRepository.saveAll(listPostalChange).size();
    }

    private AddressPostalEntity createNewPostalEntity(final AddressPostalEntity srcEntity, final String postalNew1,
            final String postalNew2, final LeastUserDto userDto) {

        AddressPostalEntity entity = new AddressPostalEntity();
        BeanUtils.copyProperties(srcEntity, entity);
        entity.setPostalcode1(postalNew1);
        entity.setPostalcode2(postalNew2);

        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setAddressPostalId(0); // auto increment明記

        return entity;
    }

    private AddressPostalIrregularEntity createNewIrregularPostalEntity(final AddressPostalIrregularEntity srcEntity,
            final String postalNew1, final String postalNew2, final LeastUserDto userDto) {

        AddressPostalIrregularEntity entity = new AddressPostalIrregularEntity();
        BeanUtils.copyProperties(srcEntity, entity);
        entity.setPostalcode1(postalNew1);
        entity.setPostalcode2(postalNew2);

        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setAddressPostalIrregularId(0); // auto increment明記

        return entity;
    }

    private void changeIrregular(final AddressPostalEntity entity, final String postalNew1, final String postalNew2,
            final LeastUserDto userDto) {
        List<AddressPostalIrregularEntity> listPostalIrregularChange = new ArrayList<>();
        if (!entity.getIsGyoseikuData()) {
            List<AddressPostalIrregularEntity> listIrr = addressPostalIrregularRepository
                    .findByPostalcode1AndPostalcode2AndIsLatestTrue(entity.getPostalcode1(), entity.getPostalcode2());
            for (AddressPostalIrregularEntity irrEntity : listIrr) {
                listPostalIrregularChange
                        .add(this.createNewIrregularPostalEntity(irrEntity, postalNew1, postalNew2, userDto));
                setTableDataHistoryUtil.practiceDelete(userDto, irrEntity);
                listPostalIrregularChange.add(irrEntity);
            }
            // 不規則住所を登録
            addressPostalIrregularRepository.saveAll(listPostalIrregularChange);
        }
    }

}
