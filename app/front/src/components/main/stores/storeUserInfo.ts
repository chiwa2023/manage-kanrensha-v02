import { defineStore } from 'pinia';
import { ref, type Ref } from 'vue';
import { LeastUserDto, type LeastUserDtoInterface } from '../dto/user/leastUserDto';
import { JwtTokenDto, type JwtTokenDtoInterface } from '../dto/login/jwtTokenDto';

export const useUserInfoStore = defineStore('userInfo', () => {
    // state（リアクティブな状態）
    const userDto: Ref<LeastUserDtoInterface> = ref(new LeastUserDto());
    const jwtDto: Ref<JwtTokenDtoInterface> = ref(new JwtTokenDto());

    const initialize = () => {
        userDto.value = new LeastUserDto();
        jwtDto.value = new JwtTokenDto();
    }

    // 外部に公開する値や関数を返す
    return {
        initialize,
        userDto,
        jwtDto,
    };
},
    {
        persist: {
            storage: sessionStorage,
        },
    },
);