import { PartnerAccessTokenStateDto, type PartnerAccessTokenStateDtoInterface } from "../../../../main/dto/user/partnerAccessTokenStateDto";

export default function mockGetPartnerApiForNewToken():PartnerAccessTokenStateDtoInterface{

    const dto:PartnerAccessTokenStateDtoInterface = new PartnerAccessTokenStateDto();

    dto.userCode =124;
    dto.userName = "tokenName";
    dto.createdAt = new Date();
    dto.expiresAt = new Date();
    dto.lastUsedAt = new Date();
    dto.partnerAccessTokenId = 794;
    dto.ipAddress = "127.0.0.1";

    return dto;
}