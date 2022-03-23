import { useQuery } from 'react-query';
import { Link, useNavigate } from 'react-router-dom';

import { apiClient } from '../../services/api';

function SubjectItem({ subject, onSuccess }) {
  const navigate = useNavigate();

  async function deleteHandler() {
    if (confirm('Are you sure you want to delete this subject?')) {
      await apiClient.delete(`/subject/${subject.id}`);
      onSuccess();
    }
  }

  function editHandler() {
    navigate(`edit/${subject.id}`);
  }

  return (
    <li key={subject.id}>
      {subject.name} <button onClick={() => deleteHandler()}>Delete</button>{' '}
      <button onClick={() => editHandler()}>Edit</button>
      <h2>Homework</h2>
      <ul>
        {subject.homework.map(homework => (
          <li key={homework.id}>{homework.name}</li>
        ))}
        <li>
          <Link to={`/subjects/${subject.id}/create-homework`}>Add homework</Link>
        </li>
      </ul>
    </li>
  );
}

export function SubjectsPage() {
  const { data, error, isLoading, refetch } = useQuery('subjects', async () => {
    const { data } = await apiClient.get('/subject');
    return data;
  });

  return (
    <>
      <h1>Subjects</h1>
      {isLoading && <div>Loader</div>}
      {error && <p>There was an error</p>}
      <Link to="add">Add subject</Link>
      {data && (
        <ul>
          {data.map(subject => (
            <SubjectItem subject={subject} key={subject.id} onSuccess={refetch} />
          ))}
        </ul>
      )}
    </>
  );
}
