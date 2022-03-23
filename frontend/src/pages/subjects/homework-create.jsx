import { useQuery } from 'react-query';
import { Link, useNavigate, useParams } from 'react-router-dom';

import { apiClient } from '../../services/api';
import { HomeworkForm } from '../../components/HomeworkForm';

export function HomeworkCreatePage() {
  const { subjectId } = useParams();
  const navigate = useNavigate();

  const { data: subject, isLoading } = useQuery(
    ['subject', subjectId],
    async () => {
      const { data } = await apiClient.get(`/subject/${subjectId}`);
      return data;
    },
    {
      enabled: Boolean(subjectId),
    },
  );

  async function submitHandler(values, form) {
    console.log(values);
    try {
      await apiClient.post(`/subject/${subjectId}/homework`, values);
      navigate('/subjects');
    } catch {
      // handle error
    }
  }

  if (!subject) {
    return <></>;
  }

  return (
    <div>
      <Link to="/subjects">Back</Link>
      <h2>Add homework to {subject.name}</h2>
      {!isLoading && (
        <HomeworkForm
          mode={"create"}
          onSubmit={submitHandler}
          // initialValues={subjectId && data}
        />
      )}
    </div>
  );
}
