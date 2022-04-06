import { Link, useNavigate } from 'react-router-dom';

import { apiClient } from '../services/api';
import { Button } from './Button';

export function HomeworkItem({ homework, onSuccess }) {
  const navigate = useNavigate();

  async function deleteHandler() {
    if (confirm(`Are you sure you want to delete ${homework.name} ?`)) {
      await apiClient.delete(`/homework/${homework.id}`);
      onSuccess();
    }
  }

  function editHandler() {
    navigate(`homework/edit/${homework.id}`);
  }

  return (
    <li>
      {homework.name} <Button onClick={() => deleteHandler()}>Delete</Button>{' '}
      <Button onClick={() => editHandler()}>Edit</Button>
    </li>
  );
}
