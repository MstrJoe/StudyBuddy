import dayjs from 'dayjs';
import { useQuery } from 'react-query';
import { Link, useNavigate, useParams } from 'react-router-dom';

import { AgendaItemForm } from '../../components/AgendaItemForm';
import { apiClient } from '../../services/api';

export function AgendaItemCreatePage() {
  const { homeworkId } = useParams();
  const navigate = useNavigate();

  const { data: homework, isLoading } = useQuery(
    ['homework', homeworkId],
    async () => {
      const { data } = await apiClient.get(`/homework/${homeworkId}`);
      return data;
    },
    {
      enabled: Boolean(homeworkId),
    },
  );

  async function submitHandler(values, form) {
    try {
      const { startDate, startTime, ...input } = values;
      input.moment = dayjs(`${startDate} ${startTime}`);
      await apiClient.post(`/homework/${homeworkId}/agendaitem`, input);
      navigate('/agenda');
    } catch {
      alert('Something went wrong');
    }
  }

  if (!homework) {
    return <></>;
  }

  return (
    <div>
      <Link to="/subjects">Back</Link>
      <h2>Add agenda item to {homework.name}</h2>
      {!isLoading && <AgendaItemForm mode={'create'} onSubmit={submitHandler} />}
    </div>
  );
}
