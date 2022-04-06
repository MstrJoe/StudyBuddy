import dayjs from 'dayjs';
import { useQuery, useQueryClient } from 'react-query';
import { Link, useNavigate, useParams } from 'react-router-dom';

import { Drawer } from '../../components/Drawer';
import { HomeworkForm } from '../../components/HomeworkForm';
import { apiClient } from '../../services/api';

export function HomeworkCreatePage() {
  const { subjectId } = useParams();
  const navigate = useNavigate();
  const queryClient = useQueryClient();

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
    try {
      const { deadlineDate, deadlineTime, ...input } = values;
      input.deadline = dayjs(`${deadlineDate} ${deadlineTime}`).toDate();
      await apiClient.post(`/subject/${subjectId}/homework`, input);
      await queryClient.invalidateQueries('subjects');

      navigate('/subjects');
    } catch {
      alert('Something went wrong');
    }
  }

  if (!subject) {
    return <></>;
  }

  return (
    <Drawer onClose={() => navigate('/subjects')}>
      <Link to="/subjects">Back</Link>
      <h2>Add homework to {subject.name}</h2>
      {!isLoading && (
        <HomeworkForm
          mode={'create'}
          onSubmit={submitHandler}
          // initialValues={subjectId && data}
        />
      )}
    </Drawer>
  );
}
