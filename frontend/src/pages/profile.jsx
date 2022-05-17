import axios from 'axios';

import { useUser } from '../context/UserContext';
import { apiClient } from '../services/api';

export function ProfilePage() {
  const { user } = useUser();

  async function uploadAvatar(event) {
    event.preventDefault();
    const formData = new FormData(event.currentTarget);

    console.log(formData);

    try {
      await apiClient.post('/user/avatar', formData);
    } catch (err) {
      console.error(err);
    }
  }

  return (
    <>
      <h1>Profile</h1>
      <p>Name: {user.name}</p>
      <p>Username: {user.username}</p>
      <p>Email: {user.email}</p>

      <h2>Upload avatar</h2>
      <form onSubmit={uploadAvatar}>
        <input name="file" type="file" required />
        <button>Upload</button>
      </form>
    </>
  );
}
