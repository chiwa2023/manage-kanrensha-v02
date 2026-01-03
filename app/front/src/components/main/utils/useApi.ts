import { ref, type Ref } from 'vue';

interface ApiConfig {
  method: string;
  headers?: Record<string, string>;
  body?: string;
}

interface ApiResponse<T> {
  loading: Ref<boolean>;
  error: Ref<string | null>;
  fetchData: (url: string, config: ApiConfig) => Promise<T | null>;
}

export function useApi<T>(): ApiResponse<T> {
  const loading: Ref<boolean> = ref(false);
  const error: Ref<string | null> = ref(null);

  const fetchData = async (url: string, config: ApiConfig): Promise<T | null> => {
    loading.value = true;
    error.value = null;
    try {
      const response = await fetch(url, config);
      if (!response.ok) {
        const errorBody = await response.json();
        throw new Error(errorBody.message || `HTTP error! status: ${response.status}`);
      }
      const result = await response.json();
      return result;
    } catch (e: any) {
      error.value = e.message || '不明なエラーが発生しました。';
      return null;
    } finally {
      loading.value = false;
    }
  };

  return {
    loading,
    error,
    fetchData,
  };
}
