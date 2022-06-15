import { MyDropzone } from '../components/MyDropzone';
import { useUser } from '../context/UserContext';
import { apiClient } from '../services/api';
import './profile.scss';

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
      <div className="avatar-page">
        <MyDropzone onDrop={acceptedFiles => console.log(acceptedFiles)}>
          {({ getRootProps, getInputProps }) => (
            <section>
              <div {...getRootProps()}>
                <input {...getInputProps()} />
                <p>Drag 'n' drop some files here, or click to select files</p>
              </div>
            </section>
          )}
        </MyDropzone>

        <h1>Profile</h1>
        <div>
          {user.avatar && (
            <img
              className="avatar"
              src={'http://localhost:8080/uploads/' + user.avatar}
              alt="Avatar"
            />
          )}
        </div>

        <div className="user-info">
          <p>Name: {user.name}</p>
          <p>Username: {user.username}</p>
          <p>Email: {user.email}</p>
        </div>

        {/*<h2>Upload avatar</h2>*/}
        {/*<form onSubmit={uploadAvatar}>*/}
        {/*  <input name="file" type="file" accept="image/*" required />*/}
        {/*  <button>Upload</button>*/}
        {/*</form>*/}
      </div>
    </>
  );
}
