import './SubjectItem.scss';

import { useState } from 'react';
import { BiChevronDown, BiChevronUp } from 'react-icons/bi';
import { BsPlus } from 'react-icons/bs';
import { useNavigate, Link } from 'react-router-dom';

import { apiClient } from '../services/api';
import { Button } from './Button';
import { HomeworkItem } from './HomeworkItem';

export function SubjectItem({ subject, onSuccess }) {
  const navigate = useNavigate();
  const [showMore, setShowMore] = useState(false);

  async function deleteHandler() {
    try {
      if (confirm('Are you sure you want to delete this subject?')) {
        await apiClient.delete(`/subject/${subject.id}`);
        onSuccess();
      }
    } catch {
      alert('Delete the homework items from this subject first');
    }
  }

  function editHandler() {
    navigate(`edit/${subject.id}`);
  }

  return (
    <li key={subject.id} className="subject-item">
      <div className="subject-header">
        <h3>{subject.name}</h3>
        <div className="actions">
          <Button
            className="button-outline"
            disabled={subject.homework.length > 0}
            onClick={() => deleteHandler()}
          >
            Delete
          </Button>
          <Button className="button-outline" onClick={() => editHandler()}>
            Edit
          </Button>
          <Button onClick={() => setShowMore(state => !state)}>
            {showMore ? <BiChevronUp size={24} /> : <BiChevronDown size={24} />}
          </Button>
        </div>
      </div>

      {showMore && (
        <div className="homework-wrapper">
          <h4 className="homework-title">Homework</h4>
          <ul className="homework-list">
            {subject.homework.map(homework => (
              <HomeworkItem key={homework.id} homework={homework} onSuccess={onSuccess} />
            ))}
            <li>
              <Link
                className="button button-outline homework-button"
                to={`/subjects/${subject.id}/create-homework`}
              >
                <BsPlus />
                Add homework
              </Link>
            </li>
          </ul>
        </div>
      )}
    </li>
  );
}
