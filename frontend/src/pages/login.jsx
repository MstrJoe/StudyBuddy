import { useSearchParams } from 'react-router-dom';

import { LoginForm } from '../components/LoginForm';
import { SignUpForm } from '../components/SignUpForm';
import { useUser } from '../context/UserContext';
import { apiClient } from '../services/api';

export function LoginPage() {
  const [searchParams, setSearchParams] = useSearchParams({
    mode: 'login',
  });
  const { initialize } = useUser();

  async function login(values) {
    try {
      await apiClient.post('/auth/signin', values);
      initialize();
    } catch (err) {
      console.error(err);
    }
  }

  async function signup(values) {
    try {
      await apiClient.post('/auth/signup', values);
      initialize();
    } catch (err) {
      console.error(err);
    }
  }

  return (
    <div>
      {searchParams.get('mode') === 'sign-up' ? (
        <SignUpForm onSubmit={signup} />
      ) : (
        <LoginForm onSubmit={login} />
      )}
    </div>
  );
}
