import { describe, it, expect } from 'vitest';
import { logout } from '../../main/utils/logout';
import App from '../../../App.vue'
import { createPinia } from 'pinia';
import { createApp } from 'vue';
import piniaPluginPersistedstate from 'pinia-plugin-persistedstate'
import { useUserInfoStore } from '../../main/stores/storeUserInfo';
import { JwtTokenDto, type JwtTokenDtoInterface } from '../../main/dto/login/jwtTokenDto';
import { LeastUserDto, type LeastUserDtoInterface } from '../../main/dto/user/leastUserDto';

// pinia設定
const app = createApp(App);
const pinia = createPinia();
pinia.use(piniaPluginPersistedstate)
app.use(pinia);

describe('logout', () => {

    it('値が未設定でも落ちない', () => {
        const userInfo = useUserInfoStore();
        logout();// ログアウト処理

        const jwtDto: JwtTokenDtoInterface = userInfo.jwtDto;
        expect(jwtDto.accessToken).toBe("");
        expect(jwtDto.refreshToken).toBe("");
        expect(jwtDto.expiresAt).toEqual(new Date(1948, 7, 29, 0, 0, 0));

        const userDto: LeastUserDtoInterface = userInfo.userDto;
        expect(userDto.listRoles).toEqual([]);
        expect(userDto.userPersonId).toBe(0);
        expect(userDto.userPersonCode).toBe(0);
        expect(userDto.userPersonName).toBe("");


    });

    it('値が設定してある場合はクリアされる', () => {

        const setJwtDto:JwtTokenDtoInterface = new JwtTokenDto();  
        setJwtDto.accessToken = "1";
        setJwtDto.refreshToken = "2";
        setJwtDto.expiresAt = new Date(2022,12,5,12,34,56);

        const setUserDto:LeastUserDtoInterface = new LeastUserDto();  
        setUserDto.userPersonId = 100;
        setUserDto.userPersonCode = 101;
        setUserDto.userPersonName = "103";
        setUserDto.listRoles.push("104");

        const userInfo = useUserInfoStore();
        userInfo.jwtDto = setJwtDto;
        userInfo.userDto = setUserDto;

        logout();// ログアウト処理

        const jwtDto: JwtTokenDtoInterface = userInfo.jwtDto;
        expect(jwtDto.accessToken).toBe("");
        expect(jwtDto.refreshToken).toBe("");
        expect(jwtDto.expiresAt).toEqual(new Date(1948, 7, 29, 0, 0, 0));

        const userDto: LeastUserDtoInterface = userInfo.userDto;
        expect(userDto.listRoles).toEqual([]);
        expect(userDto.userPersonId).toBe(0);
        expect(userDto.userPersonCode).toBe(0);
        expect(userDto.userPersonName).toBe("");
    });
});
