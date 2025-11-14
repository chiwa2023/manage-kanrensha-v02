import { describe, it, expect } from 'vitest';
import LoginConstants from '../../main/dto/user/loginConstants';
import { LeastUserDto } from '../../main/dto/user/leastUserDto';
import { getLoginUser } from '../../main/utils/getLoginUser';
import UserRoleConstants from '../../main/dto/user/userRoleConstants';
import { SelectOptionStringDto, type SelectOptionStringDtoInterface } from '../../main/dto/select_options/selectOptionStringDto';

describe('getLoginUser', () => {

    it('元のセッションが空の時はから空を返す', () => {
        // セッション初期化処理
        sessionStorage.clear();

        // ユーザを取得したら初期値
        const userDto: LeastUserDto = getLoginUser();
        expect(userDto.userPersonId).toBe(0);
    });

    it('正常に値が設定してある場合は正常に取得', () => {
        // 正常ログイン状態
        sessionStorage.clear();
        const inputDto: LeastUserDto = new LeastUserDto();
        inputDto.userPersonId = 213;
        inputDto.userPersonCode = 190;
        inputDto.userPersonName = "管理者　太郎";
        inputDto.listRoles.push(UserRoleConstants.ROLE_MANAGER);
        inputDto.listRoles.push(UserRoleConstants.ROLE_KANRENSHA_KIGYOU_DT);

        sessionStorage.setItem(LoginConstants.SESSION_KEY_USER, JSON.stringify(inputDto));

        // 取得処理
        const outputDto: LeastUserDto = getLoginUser();
        // ユーザを取得してもnull
        expect(outputDto.userPersonId).toBe(inputDto.userPersonId);
        expect(outputDto.userPersonCode).toBe(inputDto.userPersonCode);
        expect(outputDto.userPersonName).toBe(inputDto.userPersonName);
        expect(outputDto.listRoles.length).toBe(inputDto.listRoles.length);
        expect(outputDto.listRoles.includes(UserRoleConstants.ROLE_MANAGER)).toBeTruthy();
        expect(outputDto.listRoles.includes(UserRoleConstants.ROLE_KANRENSHA_KIGYOU_DT)).toBeTruthy();
    });


    it('JSON変換できない', () => {
        // テスト条件設定
        sessionStorage.clear();
        sessionStorage.setItem(LoginConstants.SESSION_KEY_USER, "12345");

        // 取得処理
        const outputDto: LeastUserDto = getLoginUser();
        expect(outputDto.userPersonId).toBe(0);
        expect(outputDto.listRoles.length).toBe(0);
    });

    it('JSON変換できてもLeastUserDtoでない', () => {
        // テスト条件設定
        sessionStorage.clear();
        const inputDto: SelectOptionStringDtoInterface = new SelectOptionStringDto();
        inputDto.value="aaa";
        inputDto.text="bbb";
        sessionStorage.setItem(LoginConstants.SESSION_KEY_USER, JSON.stringify(inputDto));

        // 取得処理
        const outputDto: LeastUserDto = getLoginUser();
        expect(outputDto.userPersonId).toBe(0);
        expect(outputDto.listRoles.length).toBe(0);
    });

});
