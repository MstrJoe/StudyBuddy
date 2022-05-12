import dayjs from 'dayjs';
import { BiArrowBack } from 'react-icons/bi';
import { useQuery, useQueryClient } from 'react-query';
import { Link, useNavigate, useParams } from 'react-router-dom';

import { Drawer } from '../../components/Drawer';
import { HomeworkForm } from '../../components/HomeworkForm';
import { apiClient } from '../../services/api';

export function HomeworkEditPage() {
  const { id: homeworkId } = useParams();
  const navigate = useNavigate();
  const queryClient = useQueryClient();

  const { data: homework, isLoading } = useQuery(['homework', homeworkId], async () => {
    const { data } = await apiClient.get(`/homework/${homeworkId}`);
    return data;
  });

  async function submitHandler(values, form) {
    try {
      const { deadlineDate, deadlineTime, ...input } = values;
      input.deadline = dayjs(`${deadlineDate} ${deadlineTime}`).format();
      await apiClient.put(`/homework/${homeworkId}`, input);
      navigate('/subjects');
    } catch {
      alert('Something went wrong');
    }
  }

  if (!homework) {
    return <></>;
  }

  return (
    <Drawer onClose={() => navigate('/subjects')}>
      <Link to="/subjects">
        <BiArrowBack />
        Back
      </Link>
      <h2>Edit homework "{homework.name}"</h2>
      {!isLoading && (
        <HomeworkForm
          mode={'edit'}
          onSubmit={submitHandler}
          initialValues={{
            ...homework,
            deadlineDate: dayjs(new Date(homework.deadline)).format('YYYY-MM-DD'),
            deadlineTime: dayjs(new Date(homework.deadline)).format('HH:mm'),
          }}
        />
      )}
    </Drawer>
  );
}
