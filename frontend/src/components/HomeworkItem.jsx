import dayjs from 'dayjs';
import { Link, useNavigate } from 'react-router-dom';

import { apiClient } from '../services/api';
import { Button } from './Button';

export function HomeworkItem({ homework, onSuccess }) {
  const navigate = useNavigate();

  async function deleteHandler() {
    try {
      if (confirm(`Are you sure you want to delete ${homework.name} ?`)) {
        await apiClient.delete(`/homework/${homework.id}`);
        onSuccess();
      }
    } catch {
      alert('This homework is beïng used in the Agenda, therefore it cannot be deleted');
    }
  }

  function editHandler() {
    navigate(`homework/edit/${homework.id}`);
  }

  return (
    <li className="homework-item">
      <div className="info">
        <h5>{homework.name}</h5>
        <p>Deadline {dayjs(homework.deadline).format('DD/MM/YYYY HH:mm')}</p>
      </div>

      <div className="actions">
        <Button onClick={() => deleteHandler()}>Delete</Button>{' '}
        <Button onClick={() => editHandler()}>Edit</Button>
      </div>
    </li>
  );
}
