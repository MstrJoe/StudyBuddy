import React, { useCallback, useContext, useEffect, useState } from 'react';

import { apiClient } from '../services/api';

export const UserContext = React.createContext(null);

export function UserProvider({ children }) {
  const [user, setUser] = useState();
  const [isLoading, setLoading] = useState(false);

  const logout = useCallback(() => {
    setUser(null);
  }, []);

  const initialize = useCallback(() => {
    if (!user) {
      setLoading(true);
      apiClient
        .get('/user/me')
        .then(res => {
          setUser(res.data);
        })
        .catch(() => {
          logout();
        })
        .finally(() => {
          setLoading(false);
        });

      console.log('dfgdfgfd');
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
