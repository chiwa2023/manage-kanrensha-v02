import { describe, it, expect } from 'vitest';
import { mount } from '@vue/test-utils';
import SetYearPeriod from '../../../main/common/kanrensha_edit/SetYearPeriod.vue';
import type { YearOptionEntityInterface } from '../../../main/entity/yearOptionEntity';

function createYearList(start: number, end: number): YearOptionEntityInterface[] {
    const list: YearOptionEntityInterface[] = [];
    for (let y = start; y <= end; y++) {
        list.push({ selectedYear: y, isSelected: false });
    }
    return list;
}

describe('SetYearPeriod.vue', () => {
    it('2019年〜2027年（9年分）が渡されたとき、具体的に指定で6年ごとに改行（2行）されること', async () => {
        const listYear = createYearList(2019, 2027);
        const wrapper = mount(SetYearPeriod, {
            props: { listYear }
        });

        // 「具体的に指定」ラジオボタンに切り替え
        const radios = wrapper.findAll('input[type="radio"]');
        await radios[1]?.setValue();

        const rows = wrapper.findAll('.year-row');
        expect(rows.length).toBe(2);

        // 1行目は2019年〜2024年の6年分
        const firstRowText = rows[0]?.text();
        expect(firstRowText).toContain('2019 年');
        expect(firstRowText).toContain('2020 年');
        expect(firstRowText).toContain('2021 年');
        expect(firstRowText).toContain('2022 年');
        expect(firstRowText).toContain('2023 年');
        expect(firstRowText).toContain('2024 年');
        expect(firstRowText).not.toContain('2025 年');

        // 2行目は2025年〜2027年の3年分
        const secondRowText = rows[1]?.text();
        expect(secondRowText).toContain('2025 年');
        expect(secondRowText).toContain('2026 年');
        expect(secondRowText).toContain('2027 年');
        expect(secondRowText).not.toContain('2024 年');
    });

    it('チェックボックスを変更したとき sendCheckList が emit されること', async () => {
        const listYear = createYearList(2019, 2027);
        const wrapper = mount(SetYearPeriod, {
            props: { listYear }
        });

        const radios = wrapper.findAll('input[type="radio"]');
        await radios[1]?.setValue();

        const firstCheckbox = wrapper.find('.year-row input[type="checkbox"]');
        await firstCheckbox.setValue(true);

        expect(wrapper.emitted('sendCheckList')).toBeTruthy();
        const emittedPayload = wrapper.emitted('sendCheckList')?.[0]?.[0] as YearOptionEntityInterface[];
        expect(emittedPayload[0]?.isSelected).toBe(true);
    });

    it('ちょうど6年の場合、1行のみ生成されること', async () => {
        const listYear = createYearList(2019, 2024);
        const wrapper = mount(SetYearPeriod, {
            props: { listYear }
        });

        const radios = wrapper.findAll('input[type="radio"]');
        await radios[1]?.setValue();

        const rows = wrapper.findAll('.year-row');
        expect(rows.length).toBe(1);
        expect(rows[0]?.findAll('input[type="checkbox"]').length).toBe(6);
    });

    it('13年の場合、3行（6件、6件、1件）生成されること', async () => {
        const listYear = createYearList(2015, 2027);
        const wrapper = mount(SetYearPeriod, {
            props: { listYear }
        });

        const radios = wrapper.findAll('input[type="radio"]');
        await radios[1]?.setValue();

        const rows = wrapper.findAll('.year-row');
        expect(rows.length).toBe(3);
        expect(rows[0]?.findAll('input[type="checkbox"]').length).toBe(6);
        expect(rows[1]?.findAll('input[type="checkbox"]').length).toBe(6);
        expect(rows[2]?.findAll('input[type="checkbox"]').length).toBe(1);
    });

    it('空配列の場合、行が生成されないこと', async () => {
        const wrapper = mount(SetYearPeriod, {
            props: { listYear: [] }
        });

        const radios = wrapper.findAll('input[type="radio"]');
        await radios[1]?.setValue();

        const rows = wrapper.findAll('.year-row');
        expect(rows.length).toBe(0);
    });

    it('期間で指定モード時、開始年と終了年の変更イベントが発火すること', async () => {
        const listYear = createYearList(2019, 2027);
        const wrapper = mount(SetYearPeriod, {
            props: { listYear }
        });

        const numberInputs = wrapper.findAll('input[type="number"]');
        await numberInputs[0]?.setValue(2020);
        expect(wrapper.emitted('sendStartYear')).toBeTruthy();
        expect(wrapper.emitted('sendStartYear')?.[0]?.[0]).toBe(2020);

        await numberInputs[1]?.setValue(2025);
        expect(wrapper.emitted('sendEndYear')).toBeTruthy();
        expect(wrapper.emitted('sendEndYear')?.[0]?.[0]).toBe(2025);
    });
});
