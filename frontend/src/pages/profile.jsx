import axios from 'axios';

import { useUser } from '../context/UserContext';
import { apiClient } from '../services/api';

export function ProfilePage() {
  const { user, refresh } = useUser();

  async function uploadAvatar(event) {
    event.preventDefault();
    const formData = new FormData(event.currentTarget);

    try {
      await apiClient.post('/user/avatar', formData);
      refresh();
    } catch (err) {
      alert('Somethig went wrong');
      console.error(err);
    }
  }

  return (
    <>
      <h1>Profile</h1>
      <p>Name: {user.name}</p>
      <p>Username: {user.username}</p>
      <p>Email: {user.email}</p>
      <p>
        Avatar:{' '}
        {user.avatar && <img src={'http://localhost:8080/uploads/' + user.avatar} alt="Avatar" />}
      </p>

      <h2>Upload avatar</h2>
      <form onSubmit={uploadAvatar}>
        <input name="file" type="file" accept="image/*" required />
        <button>Upload</button>
      </form>
    </>
  );
}
