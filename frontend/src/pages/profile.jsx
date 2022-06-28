import { MyDropzone } from '../components/MyDropzone';
import { useUser } from '../context/UserContext';
import { apiClient } from '../services/api';
import './profile.scss';
import { AvatarUpload } from '../components/AvatarUpload';

export function ProfilePage() {
  const { user, refresh } = useUser();

  async function uploadAvatar(files) {
    const formData = new FormData();
    formData.set('file', files[0]);

    try {
      await apiClient.post('/user/avatar', formData);
      refresh();
    } catch (err) {
      alert('Something went wrong');
      console.error(err);
    }
  }

  return (
    <>
      <div className="avatar-page">
        <AvatarUpload
          avatar={`http://localhost:8080/uploads/${user.avatar}`}
          onDrop={uploadAvatar}
        />

        <h1>Profile</h1>

        <div className="user-info">
          <p>Name: {user.name}</p>
          <p>Username: {user.username}</p>
          <p>Email: {user.email}</p>
        </div>
      </div>
    </>
  );
}
