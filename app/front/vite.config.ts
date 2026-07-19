import { defineConfig, loadEnv } from 'vite'
import vue from '@vitejs/plugin-vue'
import path from 'path' 

// https://vite.dev/config/
export default defineConfig(({ mode }) => {
  // 環境変数の読み込み (第3引数を空にすることで、VITE_ 以外のOS環境変数もロード対象にします)
  const env = loadEnv(mode, process.cwd(), '')

  // VITE_ALLOWED_HOSTS が指定されていればカンマ区切りでパースし、未指定ならデフォルト値を使用
  const allowedHosts = env.VITE_ALLOWED_HOSTS
    ? env.VITE_ALLOWED_HOSTS.split(',').map(host => host.trim())
    : ['localhost', 'host.docker.internal']

  return {
    plugins: [vue()],
    base: '/manage-kanrensha',
    // エイリアスの設定
    resolve: {
      alias: {
        '#': path.resolve(__dirname, './src')
      }
    },
    server: {
      host: true,
      port: 5173,
      allowedHosts: allowedHosts
    }
  }
})
