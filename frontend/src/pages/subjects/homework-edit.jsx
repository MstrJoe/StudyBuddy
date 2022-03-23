import { useQuery } from 'react-query';
import { Link, useNavigate, useParams } from 'react-router-dom';

import { apiClient } from '../../services/api';
import { HomeworkForm } from '../../components/HomeworkForm';

export function HomeworkEditPage() {
  const { id: homeworkId } = useParams();
  const navigate = useNavigate();

  const { data: homework, isLoading } = useQuery(['homework', homeworkId], async () => {
    const { data } = await apiClient.get(`/homework/${homeworkId}`);
    return data;
  });

  async function submitHandler(values, form) {
    try {
      await apiClient.put(`/homework/${homeworkId}`, values);
      navigate('/subjects');
    } catch {
      // handle error
    }
  }

  if (!homework) {
    return <></>;
  }

  return (
    <div>
      <Link to="/subjects">Back</Link>
      <h2>Edit homework "{homework.name}"</h2>
      {!isLoading && (
        <HomeworkForm
          mode={'edit'}
          onSubmit={submitHandler}
          initialValues={{
            ...homework,
            // https://stackoverflow.com/questions/28760254/assign-javascript-date-to-html5-datetime-local-input
            deadline: new Date(homework.deadline)
              .toLocaleString('sv-SE', {
                year: 'numeric',
                month: '2-digit',
                day: '2-digit',
                hour: '2-digit',
                minute: '2-digit',
                second: '2-digit',
              })
              .replace(' ', 'T'),
          }}
        />
      )}
    </div>
  );
}
