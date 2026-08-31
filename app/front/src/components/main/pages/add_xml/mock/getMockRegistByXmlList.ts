import { WkTblMasterAllByXmlEntity, type WkTblMasterAllByXmlEntityInterface } from "../../../entity/wkTblMasterAllByXmlEntity";

export default function getMockRegistByXmlList(): WkTblMasterAllByXmlEntityInterface[] {
    const list: WkTblMasterAllByXmlEntityInterface[] = [];

    list.push(createDtoByBikou(1));
    list.push(createDtoByFull(2));
    list.push(createDtoByNameAddress(3));
    list.push(createDtoByPartner(4));

    return list;
}


function createDtoByBikou(index: number): WkTblMasterAllByXmlEntityInterface {

    const dto: WkTblMasterAllByXmlEntityInterface = new WkTblMasterAllByXmlEntity();

    dto.isAffected = false;
    dto.isDisabled = false;
    dto.judgeReason = "同一行が存在します";

    dto.youshikiKbn = 3;
    dto.youshikiEdaKbn = 0;
    dto.kanrenshaKbn = 0;

    dto.kanrenshaName = "";
    dto.allAddress = "";
    dto.orgDelegate = "";
    dto.personShokugyou = "";
    dto.houjinNo = "";
    dto.dantaiKbn = "";

    // 入力部
    dto.bikou = "(株)ネット選挙" + index;
    dto.inputSrcName = "";  // 入力がない
    dto.inputSrcAddress = "";  // 入力がない
    dto.inputSrcKey = "";  // 入力がない

    return dto;
}

function createDtoByFull(index: number): WkTblMasterAllByXmlEntityInterface {

    const dto: WkTblMasterAllByXmlEntityInterface = new WkTblMasterAllByXmlEntity();

    dto.isAffected = false;
    dto.isDisabled = false;
    dto.judgeReason = "同一行が存在します";

    dto.youshikiKbn = 7;
    dto.youshikiEdaKbn = 1;
    // 様式7,8,11,12は関連者区分がされた状態
    dto.kanrenshaKbn = dto.youshikiEdaKbn;

    // 入力部
    dto.bikou = "";
    dto.inputSrcName = "迂回献金　太郎" + index;
    dto.inputSrcAddress = "和歌山県実在市山麓町";
    dto.inputSrcKey = "団体役員";

    dto.kanrenshaName = dto.inputSrcName;
    dto.allAddress = dto.inputSrcAddress;
    dto.orgDelegate = dto.inputSrcKey;
    dto.personShokugyou = dto.inputSrcKey;
    dto.houjinNo = "";
    dto.dantaiKbn = "";

    return dto;
}

function createDtoByNameAddress(index: number): WkTblMasterAllByXmlEntityInterface {

    const dto: WkTblMasterAllByXmlEntityInterface = new WkTblMasterAllByXmlEntity();

    dto.isAffected = false;
    dto.isDisabled = false;
    dto.judgeReason = "同一行が存在します";

    dto.youshikiKbn = 14;
    dto.youshikiEdaKbn = 0;
    dto.kanrenshaKbn = 0;

    // 入力部
    dto.bikou = ""; // 入力がない
    dto.inputSrcName = "超元素製造組合" + index;
    dto.inputSrcAddress = "和歌山県実在市山麓町";
    dto.inputSrcKey = ""; // 入力がない

    dto.kanrenshaName = dto.inputSrcName;
    dto.allAddress = dto.inputSrcAddress;
    dto.orgDelegate = dto.inputSrcKey;
    dto.personShokugyou = dto.inputSrcKey;
    dto.houjinNo = "";
    dto.dantaiKbn = "";

    return dto;
}

function createDtoByPartner(index: number): WkTblMasterAllByXmlEntityInterface {

    const dto: WkTblMasterAllByXmlEntityInterface = new WkTblMasterAllByXmlEntity();

    dto.isAffected = false;
    dto.isDisabled = false;
    dto.judgeReason = "同一行が存在します";

    dto.youshikiKbn = 4;
    dto.youshikiEdaKbn = 0;
    dto.kanrenshaKbn = 0;

    // 入力部
    dto.bikou = ""; // 入力がない
    dto.inputSrcName = "ふんだくり企業" + index;
    dto.inputSrcAddress = ""; // 入力がない
    dto.inputSrcKey = ""; // 入力がない

    dto.kanrenshaName = dto.inputSrcName;
    dto.allAddress = "";
    dto.orgDelegate = "";
    dto.personShokugyou = "";
    dto.houjinNo = "";
    dto.dantaiKbn = "";

    return dto;
}