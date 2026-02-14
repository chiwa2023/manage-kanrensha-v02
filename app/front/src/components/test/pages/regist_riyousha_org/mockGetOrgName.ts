import { InputOrgNameDto, type InputOrgNameDtoInterface } from "seijishikin-jp-normalize_common-tool";

export default function mockGetOrgName():InputOrgNameDtoInterface{
    const dto = new InputOrgNameDto();
    dto.orgName = "組織名";
    dto.orgNameKana = "そしきめい";
    return dto;
}