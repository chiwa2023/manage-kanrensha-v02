import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vite.dev/config/
export default defineConfig({
  plugins: [vue()],
  base: '/manage-kanrensha',
  server: {
    host: true,
    port: 5173,
    allowedHosts: ['host.docker.internal']
  },

})
