import { useUser } from '../context/UserContext';

export function ProfilePage() {
  const { user } = useUser();

  return (
    <>
      <h1>Profile</h1>
      <p>Name: {user.name}</p>
      <p>Username: {user.username}</p>
      <p>Email: {user.email}</p>
    </>
  );
}
