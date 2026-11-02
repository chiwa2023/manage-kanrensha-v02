import { fileURLToPath } from 'node:url';
import { mergeConfig, defineConfig } from 'vite';
import { configDefaults } from 'vitest/config';
import viteConfig from './vite.config.mts';

export default defineConfig((env) => {
    // 1. viteConfigが関数（コールバック）の場合は実行してオブジェクトに変換する
    const resolvedViteConfig = typeof viteConfig === 'function' 
        ? viteConfig(env) 
        : viteConfig;

    // 2. 変換後のオブジェクトと、Vitest固有の設定をマージして返す
    return mergeConfig(
        resolvedViteConfig,
        defineConfig({
            test: {
                environment: 'jsdom', 
                exclude: [...configDefaults.exclude, 'e2e/*'],
                root: fileURLToPath(new URL('./', import.meta.url)),
                transformMode: {
                    web: [/\.[jt]sx$/],
                },
            },
        })
    );
});