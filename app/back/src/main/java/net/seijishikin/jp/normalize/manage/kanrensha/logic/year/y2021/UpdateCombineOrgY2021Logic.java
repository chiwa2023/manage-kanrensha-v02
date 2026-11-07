package net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2021;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaCombineOrgEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.year.y2021.KanrenshaCombineOrg2021Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2021.KanrenshaCombineOrg2021Repository;

/**
 * 関連者紐づけ更新Logic(2021)
 */
@Component
public class UpdateCombineOrgY2021Logic {

    /** 個人団体紐づけRepository(2021) */
    @Autowired
    private KanrenshaCombineOrg2021Repository kanrenshaCombineOrg2021Repository;

    /** テーブル履歴設定Util */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /**
     * 処理を行う
     *
     * @param entityWkTbl 複写元ワークテーブルEntity
     * @return 登録完了Id
     */
    public Integer practice(final WkTblKanrenshaCombineOrgEntity entityWkTbl, final boolean isRecord,
            final LeastUserDto userDto) {

        // 編集対象のデータが存在する確認
        List<KanrenshaCombineOrg2021Entity> list = kanrenshaCombineOrg2021Repository
                .findByPersonKanrenshaCodeAndOrgKanrenshaCodeAndKanrenshaKbnAndIsLatestTrue(entityWkTbl.getPersonKanrenshaCode(),
                        entityWkTbl.getOrgKanrenshaCode(),entityWkTbl.getKanrenshaKbn());

        if (isRecord) {
            // 登録する場合
            if (list.isEmpty()) {
                // 新規追加処理
                return this.insert(entityWkTbl, userDto);
            } else {
                // 最新が存在するときは何もしない
                return 0;
            }
        } else {
            // 登録しない場合
            if (list.isEmpty()) {
                // 最新が存在しない場合は何もしない
                return 0;
            } else {
                // 最新が存在するときは履歴に変更
                return this.delete(list, userDto);
            }
        }
    }

    private Integer insert(final WkTblKanrenshaCombineOrgEntity entityWkTbl, final LeastUserDto userDto) {

        Optional<KanrenshaCombineOrg2021Entity> optional = kanrenshaCombineOrg2021Repository
                .findFirstByOrderByKanrenshaCombineOrgCodeDesc();
        Integer code = 1;
        if (!optional.isEmpty()) {
            code += optional.get().getKanrenshaCombineOrgCode();
        }

        KanrenshaCombineOrg2021Entity entity = new KanrenshaCombineOrg2021Entity();
        BeanUtils.copyProperties(entityWkTbl, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaCombineOrgCode(code);
        entity.setKanrenshaCombineOrgId(0); // auto_increment明示

        return kanrenshaCombineOrg2021Repository.save(entity).getKanrenshaCombineOrgId();
    }

    private Integer delete(final List<KanrenshaCombineOrg2021Entity> list, final LeastUserDto userDto) {

        for (KanrenshaCombineOrg2021Entity entity : list) {
            setTableDataHistoryUtil.practiceDelete(userDto, entity);
        }

        return kanrenshaCombineOrg2021Repository.saveAll(list).size();

    }

}
