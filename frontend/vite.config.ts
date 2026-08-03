import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';

export default defineConfig({
  plugins: [react()],
  server: {
    port: 3000,
    proxy: {
      '/auth': 'http://localhost:8100',
      '/exercises': 'http://localhost:8100',
      '/plans': 'http://localhost:8100',
      '/generator': 'http://localhost:8100',
      '/api': 'http://localhost:8100'
    }
  }
});
