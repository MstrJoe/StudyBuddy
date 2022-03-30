import React from 'react';
import { useQuery } from 'react-query';

import { AgendaItem } from '../components/AgendaItem';
import { apiClient } from '../services/api';

export function AgendaPage() {
  const {
    data: agendaItems = [],
    error,
    isLoading,
    refetch,
  } = useQuery('agendaitems', async () => {
    const { data } = await apiClient.get('/agendaitem');
    return data;
  });

  return (
    <>
      <h1>Agenda</h1>
      {agendaItems.map(item => (
        <AgendaItem key={item.id} item={item} />
      ))}
    </>
  );
}
