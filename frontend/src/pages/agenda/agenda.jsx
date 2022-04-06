import React from 'react';
import { useQuery } from 'react-query';
import { Link } from 'react-router-dom';

import { AgendaItem } from '../../components/AgendaItem';
import { Button } from '../../components/Button';
import { apiClient } from '../../services/api';

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

      <Link to="add">
        <Button>Add new</Button>
      </Link>

      {agendaItems.map(item => (
        <AgendaItem key={item.id} onDelete={() => refetch()} item={item} />
      ))}
    </>
  );
}
