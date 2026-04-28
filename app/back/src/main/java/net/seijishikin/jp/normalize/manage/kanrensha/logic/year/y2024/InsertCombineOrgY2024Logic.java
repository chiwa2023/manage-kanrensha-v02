package net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2024;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaCombineOrgEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.year.y2024.KanrenshaCombineOrg2024Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2024.KanrenshaCombineOrg2024Repository;

/**
 * 紺団体紐づけテーブル挿入Logic(2024)
 */
@Component
public class InsertCombineOrgY2024Logic {

    /** 個人団体紐づけRepository(2024) */
    @Autowired
    private KanrenshaCombineOrg2024Repository kanrenshaCombineOrg2024Repository;

    /** テーブル履歴設定Util */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /**
     * 処理を行う
     *
     * @param entityWkTbl 複写元ワークテーブルEntity
     * @return 登録完了Id
     */
    public Integer practice(final WkTblKanrenshaCombineOrgEntity entityWkTbl, final LeastUserDto userDto) {

        Optional<KanrenshaCombineOrg2024Entity> optional = kanrenshaCombineOrg2024Repository
                .findFirstByOrderByKanrenshaCombineOrgCode();
        Integer code = 1;
        if (!optional.isEmpty()) {
            code = optional.get().getKanrenshaCombineOrgCode();
        }

        KanrenshaCombineOrg2024Entity entity = new KanrenshaCombineOrg2024Entity();
        BeanUtils.copyProperties(entityWkTbl, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaCombineOrgCode(code);
        entity.setKanrenshaCombineOrgId(0); // auto_increment明示

        return kanrenshaCombineOrg2024Repository.save(entity).getKanrenshaCombineOrgId();
    }

}
