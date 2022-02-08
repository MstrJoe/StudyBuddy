import { useUser } from './context/UserContext';
import { LoginPage } from './pages/login';

function App() {
  const { user, isLoading } = useUser();

  if (isLoading) {
    return <div>Loading</div>;
  }

  if (!user) {
    return <LoginPage />;
  }

  return <div className="App">Your name is {user.name}</div>;
}

export default App;
