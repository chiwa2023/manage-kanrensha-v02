import { describe, it, expect } from 'vitest';
import { createListRoleOptions } from '../../../main/common/menu/createListRoleOptions';
import type { SelectOptionStringDtoInterface } from '../../../main/dto/select_options/selectOptionStringDto';
import UserRoleConstants from '../../../main/dto/user/userRoleConstants';

describe('createListRoleOptions', () => {

    it('空リストを渡すと初期値のみが戻る', () => {

        const role: string[] = [];
        const list: SelectOptionStringDtoInterface[] = createListRoleOptions(role);

        // 初期値
        expect(list.length).toBe(1);

        const dto0: SelectOptionStringDtoInterface | undefined = list[0];
        expect(dto0?.value).toBe("");
        expect(dto0?.text).toBe("");

    });

    it('全リストを渡すと全項目が戻る', () => {

        const role: string[] = [];
        role.push(UserRoleConstants.ROLE_ADMIN);
        role.push(UserRoleConstants.ROLE_MANAGER);
        role.push(UserRoleConstants.ROLE_PARTNER_API);
        role.push(UserRoleConstants.ROLE_KANRENSHA_KIGYOU_DT);
        role.push(UserRoleConstants.ROLE_KANRENSHA_PERSON);
        role.push(UserRoleConstants.ROLE_KANRENSHA_SEIJIDANTAI);

        const list: SelectOptionStringDtoInterface[] = createListRoleOptions(role);

        // 全項目
        expect(list.length).toBe(7);

        const dto0: SelectOptionStringDtoInterface | undefined = list[0];
        expect(dto0?.value).toBe("");
        expect(dto0?.text).toBe("");

        const dto1: SelectOptionStringDtoInterface | undefined = list[1];
        expect(dto1?.value).toBe(UserRoleConstants.ROLE_ADMIN);
        expect(dto1?.text).toBe(UserRoleConstants.ROLE_ADMIN_TEXT);

        const dto2: SelectOptionStringDtoInterface | undefined = list[2];
        expect(dto2?.value).toBe(UserRoleConstants.ROLE_MANAGER);
        expect(dto2?.text).toBe(UserRoleConstants.ROLE_MANAGER_TEXT);

        const dto3: SelectOptionStringDtoInterface | undefined = list[3];
        expect(dto3?.value).toBe(UserRoleConstants.ROLE_PARTNER_API);
        expect(dto3?.text).toBe(UserRoleConstants.ROLE_PARTNER_API_TEXT);

        const dto4: SelectOptionStringDtoInterface | undefined = list[4];
        expect(dto4?.value).toBe(UserRoleConstants.ROLE_KANRENSHA_KIGYOU_DT);
        expect(dto4?.text).toBe(UserRoleConstants.ROLE_KANRENSHA_KIGYOU_DT_TEXT);

        const dto5: SelectOptionStringDtoInterface | undefined = list[5];
        expect(dto5?.value).toBe(UserRoleConstants.ROLE_KANRENSHA_PERSON);
        expect(dto5?.text).toBe(UserRoleConstants.ROLE_KANRENSHA_PERSON_TEXT);

        const dto6: SelectOptionStringDtoInterface | undefined = list[6];
        expect(dto6?.value).toBe(UserRoleConstants.ROLE_KANRENSHA_SEIJIDANTAI);
        expect(dto6?.text).toBe(UserRoleConstants.ROLE_KANRENSHA_SEIJIDANTAI_TEXT);

    });

});
