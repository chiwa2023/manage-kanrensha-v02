export default class SeijidantaiDantaiKbnConstants {

    /** 0 無選択 */
    static readonly NO_SELECT: string = "00";
    static readonly NO_SELECT_TEXT: string = " ";

    /** 01 政党 */
    static readonly SEITOU: string = "01";
    static readonly SEITOU_TEXT: string = "政党";

    /** 02 政党支部 */
    static readonly SEITOU_SHIBU: string = "02";
    static readonly SEITOU_SHIBU_TEXT: string = "政党支部";

    /** 03 政治資金団体 */
    static readonly SEIJI_SHIKIN_DANTAI: string = "03";
    static readonly SEIJI_SHIKIN_DANTAI_TEXT: string = "政治資金団体";

    /** 04 18条2項団体 */
    static readonly DANTAI_18JOU_2KOU: string = "04";
    static readonly DANTAI_18JOU_2KOU_TEXT: string = "18条2項団体";

    /** 05 その他の政治団体 */
    static readonly SONOTA: string = "05";
    static readonly SONOTA_TEXT: string = "その他の政治団体";

    /** 06 その他の政治団体支部 */
    static readonly SONOTA_SHIBU: string = "06";
    static readonly SONOTA_SHIBU_TEXT: string = "その他の政治団体支部";

    static getLabel(value: string): string {

        switch (value) {

            // 00 無選択  
            case this.NO_SELECT:
                return this.NO_SELECT_TEXT;

            // 01 政党
            case this.SEITOU:
                return this.SEITOU_TEXT;

            // 02 政党支部 
            case this.SEITOU_SHIBU:
                return this.SEITOU_SHIBU_TEXT;

            // 03 政治資金団体
            case this.SEIJI_SHIKIN_DANTAI:
                return this.SEIJI_SHIKIN_DANTAI_TEXT;

            // 04 18条2項団体
            case this.DANTAI_18JOU_2KOU:
                return this.DANTAI_18JOU_2KOU_TEXT;

            // 05 その他の政治団体
            case this.SONOTA:
                return this.SONOTA_TEXT;

            // 06 その他の政治団体支部
            case this.SONOTA_SHIBU:
                return this.SONOTA_SHIBU_TEXT;

            default:
                return "";
        }
    }

}