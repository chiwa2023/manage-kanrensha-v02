import { MultiSelectOptionNumberDto, type MultiSelectOptionNumberDtoInterface } from "../select_options/multiSelectOptionsNumberDto";

export default function createYoteiKbnList(): MultiSelectOptionNumberDtoInterface[][] {


    const options: MultiSelectOptionNumberDtoInterface[][] = [[]];


    options.push([]);
    options[0]?.push(createOptionDto(101, "ダンプ履歴"));
    options[0]?.push(createOptionDto(102, "ダンプ履歴差分"));
    options[0]?.push(createOptionDto(103, "ダンプ最小"));
    options[0]?.push(createOptionDto(104, "ダンプ最小差分"));
    options.push([]);
    options[1]?.push(createOptionDto(105, "ダンプ標準"));
    options[1]?.push(createOptionDto(106, "ダンプ標準差分"));

    return options;
}

function createOptionDto(value: number, text: string): MultiSelectOptionNumberDtoInterface {

    const dto = new MultiSelectOptionNumberDto();
    dto.value = value;
    dto.text = text;
    dto.isSelected = true;

    return dto;
}