import { InputAddressDto, type InputAddressDtoInterface } from "seijishikin-jp-normalize_common-tool";

export default function mockGetAddress(): InputAddressDtoInterface {

    const dto: InputAddressDtoInterface = new InputAddressDto();

    dto.addressAll = "和歌山県実在市山麓町2丁目6番地四角ビル7F";

    dto.addressPostal = "和歌山県実在市山麓町";
    dto.addressBlock = "2丁目6番地";
    dto.addressBuilding = "四角ビル7F";
    dto.postalcode1 = "012";
    dto.postalcode2 = "3456";

    dto.lgCode = "100";
    dto.machiazaId = "111";
    dto.blkId = "120";
    dto.prcId = "130";
    dto.rsdtId = "140";
    dto.rsdt2Id = "141";

    return dto;
}