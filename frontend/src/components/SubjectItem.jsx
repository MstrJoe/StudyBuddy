import './SubjectItem.scss';

import { useNavigate, Link } from 'react-router-dom';

import { apiClient } from '../services/api';
import { Button } from './Button';
import { HomeworkItem } from './HomeworkItem';

export function SubjectItem({ subject, onSuccess }) {
  const navigate = useNavigate();

  async function deleteHandler() {
    if (confirm('Are you sure you want to delete this subject?')) {
      await apiClient.delete(`/subject/${subject.id}`);
      onSuccess();
    }
  }

  function editHandler() {
    navigate(`edit/${subject.id}`);
  }

  return (
    <li key={subject.id} className="subject-item">
      {subject.name}{' '}
      <Button disabled={subject.homework.length > 0} onClick={() => deleteHandler()}>
        Delete
      </Button>{' '}
      <Button onClick={() => editHandler()}>Edit</Button>
      <h2>Homework</h2>
      <ul>
        {subject.homework.map(homework => (
          <HomeworkItem key={homework.id} homework={homework} onSuccess={onSuccess} />
        ))}
        <li>
          <Link to={`/subjects/${subject.id}/create-homework`}>Add homework</Link>
        </li>
      </ul>
    </li>
  );
}
