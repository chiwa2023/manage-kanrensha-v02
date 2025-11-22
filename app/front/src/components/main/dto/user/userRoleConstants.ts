export default class UserRoleConstants {

    /** SE権限 */
    static readonly ROLE_ADMIN: string = "ROLE_admin";

    /** 運営者権限 */
    static readonly ROLE_MANAGER: string = "ROLE_manager";

    /** APIユーザ権限 */
    static readonly ROLE_PARTNER_API: string = "ROLE_partner_api";

    /** 関連者個人権限 */
    static readonly ROLE_KANRENSHA_PERSON: string = "ROLE_kanrensha_person";

    /** 関連者企業・団体権限 */
    static readonly ROLE_KANRENSHA_KIGYOU_DT: string = "ROLE_kanrensha_kigyou_dt";

    /** 関連者政治団体権限 */
    static readonly ROLE_KANRENSHA_SEIJIDANTAI: string = "ROLE_kanrensha_seijidantai";

    /** SE権限表示テキスト */
    static readonly ROLE_ADMIN_TEXT: string = "SE権限";

    /** 運営者権限表示テキスト */
    static readonly ROLE_MANAGER_TEXT: string = "運営者";

    /** APIユーザ権限表示テキスト */
    static readonly ROLE_PARTNER_API_TEXT: string = "APIユーザ";

    /** 関連者個人権限表示テキスト */
    static readonly ROLE_KANRENSHA_PERSON_TEXT: string = "関連者個人";

    /** 関連者企業・団体権限表示テキスト */
    static readonly ROLE_KANRENSHA_KIGYOU_DT_TEXT: string = "関連者企業・団体";

    /** 関連者政治団体権限表示テキスト */
    static readonly ROLE_KANRENSHA_SEIJIDANTAI_TEXT: string = "関連者政治団体";

    /**
     * ユーザroleの表示ラベルを取得する
     * @param role 役割
     * @returns 表示テキスト
     */
    public static getLabel(role: string): string {

        switch (role) {
            case UserRoleConstants.ROLE_ADMIN:
                return UserRoleConstants.ROLE_ADMIN_TEXT;

            case UserRoleConstants.ROLE_MANAGER:
                return UserRoleConstants.ROLE_MANAGER_TEXT;

            case UserRoleConstants.ROLE_PARTNER_API:
                return UserRoleConstants.ROLE_PARTNER_API_TEXT;

            case UserRoleConstants.ROLE_KANRENSHA_KIGYOU_DT:
                return UserRoleConstants.ROLE_KANRENSHA_KIGYOU_DT_TEXT;

            case UserRoleConstants.ROLE_KANRENSHA_PERSON:
                return UserRoleConstants.ROLE_KANRENSHA_PERSON_TEXT;

            case UserRoleConstants.ROLE_KANRENSHA_SEIJIDANTAI:
                return UserRoleConstants.ROLE_KANRENSHA_SEIJIDANTAI_TEXT;
            default:
                // 何もしない
                return "";
        }

    }
}