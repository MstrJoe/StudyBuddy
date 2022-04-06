import React, { useCallback, useContext, useEffect, useState } from 'react';

import { apiClient } from '../services/api';

export const UserContext = React.createContext(null);

export function UserProvider({ children }) {
  const [user, setUser] = useState();
  const [isLoading, setLoading] = useState(false);

  const logout = useCallback(async () => {
    await apiClient.get('/auth/signout');
    setUser(null);
  }, []);

  const initialize = useCallback(async () => {
    if (!user) {
      setLoading(true);
      try {
        const { data } = await apiClient.get('/user/me');
        setUser(data);
      } catch {
        logout();
      } finally {
        setLoading(false);
      }
    }
  }, [logout, user]);

  useEffect(() => {
    initialize();
  }, [initialize]);

  return (
    <UserContext.Provider
      value={{
        user,
        initialize,
        isLoading,
        logout,
      }}
    >
      {children}
    </UserContext.Provider>
  );
}

export function useUser() {
  return useContext(UserContext);
}
