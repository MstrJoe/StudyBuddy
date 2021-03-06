import { useSearchParams } from 'react-router-dom';

import { LoginForm } from '../components/LoginForm';
import { SignUpForm } from '../components/SignUpForm';
import { LoginTemplate } from '../components/templates/LoginTemplate';
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
      alert('Wrong Email or Password');
    }
  }

  async function signup(values) {
    try {
      await apiClient.post('/auth/signup', values);
      initialize();
    } catch (err) {
      alert('Something went wrong');
    }
  }

  return (
    <LoginTemplate>
      {searchParams.get('mode') === 'sign-up' ? (
        <SignUpForm onSubmit={signup} />
      ) : (
        <LoginForm onSubmit={login} />
      )}
    </LoginTemplate>
  );
}
