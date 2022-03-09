import { apiClient } from '../../services/api';
import { useApi } from '../../utils/useApi';

export function SubjectsPage() {
  const { data, error, isLoading } = useApi(() => apiClient.get('/subject'));

  return (
    <>
      <h1>Subjects</h1>
      {isLoading && <div>Loader</div>}
      {error && <p>There was an error</p>}
      <button>Add subject</button>
      {data && (
        <ul>
          {data.map(subject => (
            <li key={subject.id}>{subject.name}</li>
          ))}
        </ul>
      )}
    </>
  );
}
