import { useState, useEffect } from 'react';

export function useApi(fetcher) {
  const [isLoading, setLoading] = useState(false);
  const [error, setError] = useState();
  const [data, setData] = useState();

  useEffect(() => {
    setLoading(true);

    fetcher()
      .then(res => setData(res.data))
      .catch(err => setError(err))
      .finally(() => setLoading(false));
  }, []);

  return {
    isLoading,
    error,
    data,
  };
}
