import dayjs from 'dayjs';
import { useQuery, useQueryClient } from 'react-query';
import { Link, useNavigate, useParams } from 'react-router-dom';

import { AgendaItemForm } from '../../components/AgendaItemForm';
import { BackButton } from '../../components/BackButton';
import { Drawer } from '../../components/Drawer';
import { apiClient } from '../../services/api';

export function AgendaItemEditPage() {
  const { id } = useParams();
  const navigate = useNavigate();
  const queryClient = useQueryClient();
  console.log('bullshit');

  const { data: agendaitem, isLoading } = useQuery(
    ['agendaitem', id],
    async () => {
      const { data } = await apiClient.get(`/agendaitem/${id}`);
      return data;
    },
    {
      enabled: Boolean(id),
    },
  );

  async function submitHandler(values, form) {
    try {
      const { startDate, startTime, ...input } = values;
      input.moment = dayjs(`${startDate} ${startTime}`);
      await apiClient.put(`/agendaitem/${agendaitem.id}`, input);
      await queryClient.invalidateQueries('agendaitems');

      navigate('/agenda');
    } catch {
      alert('Something went wrong');
    }
  }

  if (!agendaitem) {
    return <></>;
  }

  return (
    <Drawer onClose={() => navigate('/agenda')}>
      <BackButton to="/subjects" />

      <h2>Edit agenda item to {agendaitem.title}</h2>
      {!isLoading && (
        <AgendaItemForm
          mode={'edit'}
          initialValues={{
            ...agendaitem,
            startDate: dayjs(agendaitem.moment).format('YYYY-MM-DD'),
            startTime: dayjs(agendaitem.moment).format('HH:mm'),
          }}
          onSubmit={submitHandler}
        />
      )}
    </Drawer>
  );
}
