import { useQuery, useQueryClient } from 'react-query';
import { Link, useNavigate, useParams } from 'react-router-dom';

import { AgendaItemForm } from '../../components/AgendaItemForm';
import { apiClient } from '../../services/api';

export function AgendaItemEditPage() {
  const { homeworkId } = useParams();
  const navigate = useNavigate();
  const queryClient = useQueryClient();

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
      await apiClient.post(`/homework/${homeworkId}/agendaitem`, values);
      await queryClient.invalidateQueries('agendaitems');

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
