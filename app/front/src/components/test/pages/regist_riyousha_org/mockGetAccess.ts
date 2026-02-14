import { InputAccessDto, type InputAccessDtoInterface } from "seijishikin-jp-normalize_common-tool";

export default function mockGetAccess(): InputAccessDtoInterface {
    const dto: InputAccessDtoInterface = new InputAccessDto();
    dto.email
    dto.email = "taro@jakusho.net";
    dto.phon1 = "012";
    dto.phon2 = "3456";
    dto.phon3 = "7890";
    dto.snsAccount = "@aaa";
    dto.snsPortalUrl = "http://sns-basue.net/";
    dto.myPortalUrl = "http://jakusho.net/taro";

    return dto;
}