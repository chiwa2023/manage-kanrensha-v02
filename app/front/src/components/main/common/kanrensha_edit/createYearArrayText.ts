import type { WkTblKanrenshaCombineOrgEntityInterface } from "../../entity/wkTblKanrenshaCombineOrgEntity";
import type { YearOptionEntityInterface } from "../../entity/yearOptionEntity";

export default function createYearArrayText(
    entityEdit: WkTblKanrenshaCombineOrgEntityInterface, listYearCheck: YearOptionEntityInterface[], isPeriod: boolean): string {

    // 編集中のEntityを編集のためにBack側に受け渡し
    let text = "";
    if (isPeriod) {
        // 範囲による指定
        for (let index = entityEdit.startYear; index <= entityEdit.endYear; index++) {
            text = text + index + ":";
        }
    }
    else {
        // チェックボックスによる指定
        for (const dto of listYearCheck) {
            if (dto.isSelected) {
                text = text + dto.selectedYear + ":";
            }
        }
        entityEdit.startYear = 0;
        entityEdit.endYear = 0;
    }
    const data = text === "" ? "" : text.substring(0, text.length - 1);

    return data;
}