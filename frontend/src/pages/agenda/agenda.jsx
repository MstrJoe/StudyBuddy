import React from 'react';
import { BsPlus } from 'react-icons/bs';
import { useQuery } from 'react-query';
import { Link, Outlet } from 'react-router-dom';

import { AgendaItem } from '../../components/AgendaItem';
import { apiClient } from '../../services/api';
import { useUser } from '../../context/UserContext';
import dayjs from 'dayjs';

export function AgendaPage() {
  const { user } = useUser();
  const canCreate = user.role.name === 'STUDENT';

  const {
    data: agendaItems = [],
    error,
    isLoading,
    refetch,
  } = useQuery('agendaitems', async () => {
    const { data } = await apiClient.get('/agendaitem');
    return data;
  });

  const futureItems = agendaItems.filter(item => dayjs(item.moment) >= dayjs());
  const pastItems = agendaItems.filter(item => dayjs(item.moment) < dayjs());

  return (
    <>
      <div className="page-header">
        <h1>Agenda</h1>
        <div className="actions">
          {canCreate && (
            <Link className="button" to="add">
              <BsPlus />
              Add new
            </Link>
          )}
        </div>
      </div>

      <h2 className="agenda-title">Future agenda items:</h2>
      {futureItems.reverse().map(item => (
        <AgendaItem
          key={item.id}
          onDelete={() => refetch()}
          onSubscribe={() => refetch()}
          item={item}
        />
      ))}

      <h2 className="agenda-title">Past agenda items:</h2>
      {pastItems.map(item => (
        <AgendaItem
          isPast
          key={item.id}
          onDelete={() => refetch()}
          onSubscribe={() => refetch()}
          item={item}
        />
      ))}

      <Outlet />
    </>
  );
}
