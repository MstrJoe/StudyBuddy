import dayjs from 'dayjs';
import { useQuery } from 'react-query';
import { Link, useNavigate, useParams } from 'react-router-dom';

import { AgendaItemForm } from '../../components/AgendaItemForm';
import { apiClient } from '../../services/api';

export function AgendaItemCreatePage() {
  const { homeworkId } = useParams();
  const navigate = useNavigate();

  const {
    data: subjects,
    error,
    isLoading,
    refetch,
  } = useQuery('subjects', async () => {
    const { data } = await apiClient.get('/subject');
    return data;
  });

  async function submitHandler(values, form) {
    try {
      const { startDate, startTime, ...input } = values;
      input.moment = dayjs(`${startDate} ${startTime}`);
      await apiClient.post(`/agendaitem`, input);
      navigate('/agenda');
    } catch {
      alert('Something went wrong');
    }
  }

  if (!subjects) {
    return <></>;
  }

  return (
    <div>
      <Link to="/subjects">Back</Link>
      <h2>Add agenda item</h2>
      {!isLoading && (
        <AgendaItemForm subjects={subjects} mode={'create'} onSubmit={submitHandler} />
      )}
    </div>
  );
}
