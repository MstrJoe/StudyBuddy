import dayjs from 'dayjs';
import { useQuery, useQueryClient } from 'react-query';
import { Link, useNavigate, useParams } from 'react-router-dom';

import { AgendaItemForm } from '../../components/AgendaItemForm';
import { Drawer } from '../../components/Drawer';
import { apiClient } from '../../services/api';

export function AgendaItemCreatePage() {
  const navigate = useNavigate();
  const queryClient = useQueryClient();

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
      await queryClient.invalidateQueries('agendaitems');
      navigate('/agenda');
    } catch {
      alert('Something went wrong');
    }
  }

  if (!subjects) {
    return <></>;
  }

  return (
    <Drawer onClose={() => navigate('/agenda')}>
      <Link to="/agenda">Back</Link>
      <h2>Add agenda item</h2>
      {!isLoading && (
        <AgendaItemForm subjects={subjects} mode={'create'} onSubmit={submitHandler} />
      )}
    </Drawer>
  );
}
