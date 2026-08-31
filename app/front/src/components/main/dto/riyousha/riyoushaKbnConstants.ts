export default class RiyoushaKbnConstants {

    /** 0 無選択 */
    static readonly NO_SELECT: number = 0;
    static readonly NO_SELECT_TEXT: string = " ";

    /** 1 運営者 */
    static readonly MANAGER: number = 1;
    static readonly MANAGER_TEXT: string = "運営者";

    /** 2 APIパートナー */
    static readonly PARTNER_API: number = 2;
    static readonly PARTNER_API_TEXT: string = "APIパートナー";

    /** 3 SE権限 */
    static readonly ADMIN: number = 3;
    static readonly ADMIN_TEXT: string = "SE権限";

    static getLabel(value: number): string {

        switch (value) {

            // 0 無選択  
            case this.NO_SELECT:
                return this.NO_SELECT_TEXT;

            // 1 運営者
            case this.MANAGER:
                return this.MANAGER_TEXT;

            // 2 APIパートナー 
            case this.PARTNER_API:
                return this.PARTNER_API_TEXT;

            // 3 SE権限
            case this.ADMIN:
                return this.ADMIN_TEXT;

            default:
                return "";
        }
    }

}