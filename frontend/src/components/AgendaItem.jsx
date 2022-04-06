import dayjs from 'dayjs';
import React from 'react';

import { useUser } from '../context/UserContext';
import { apiClient } from '../services/api';
import { Button } from './Button';

export function AgendaItem({ item, onDelete }) {
  const { user } = useUser();

  const canSubscribe = user.id !== item.createdBy.id && user.role.name === 'STUDENT';
  const canDelete = user.id === item.createdBy.id;

  async function deleteHandler() {
    if (!confirm('Are you sure you want to delete this homework?')) {
      return;
    }

    try {
      await apiClient.delete(`/agendaitem/${item.id}`);
      onDelete();
    } catch {
      alert('Something went wrong');
    }
  }

  return (
    <div className="agenda-item">
      <h1>{item.title}</h1>
      <p>{item.description}</p>
      <p>Homework: {item.homework.name}</p>
      <p>When: {dayjs(item.moment).format('DD/MM/YYYY')}</p>
      <p>Hosted by: {item.createdBy.name}</p>

      {canSubscribe && (
        <Button>
          Subscribe{item.subscribers.length > 0 ? ` ${item.subscribers.length}` : null}
        </Button>
      )}
      {canDelete && <Button onClick={deleteHandler}>Delete</Button>}
    </div>
  );
}
