import { BsPlus } from 'react-icons/bs';
import { useQuery } from 'react-query';
import { Link, Outlet, useNavigate } from 'react-router-dom';

import { SubjectItem } from '../../components/SubjectItem';
import { apiClient } from '../../services/api';

export function SubjectsPage() {
  const { data, error, isLoading, refetch } = useQuery('subjects', async () => {
    const { data } = await apiClient.get('/subject');
    return data;
  });

  return (
    <>
      <div className="page-header">
        <h1>Subjects</h1>
        <div className="actions">
          <Link className="button" to="add">
            <BsPlus />
            Add subject
          </Link>
        </div>
      </div>

      {isLoading && <div>Loader</div>}
      {error && <p>There was an error</p>}

      {data && (
        <ul className="subject-list">
          {data.map(subject => (
            <SubjectItem subject={subject} key={subject.id} onSuccess={refetch} />
          ))}
        </ul>
      )}

      <Outlet />
    </>
  );
}
