import './AgendaItem.scss';

import dayjs from 'dayjs';
import { useNavigate } from 'react-router-dom';
import { useState } from 'react';

import { useUser } from '../context/UserContext';
import { apiClient } from '../services/api';
import { Button } from './Button';
import { BiChevronDown, BiChevronUp } from 'react-icons/bi';
import classNames from 'classnames';

export function AgendaItem({ item, isPast, onDelete, onSubscribe }) {
  const { user } = useUser();
  const navigate = useNavigate();

  const [showMore, setShowMore] = useState(false);
  const [showSubscribers, setShowSubscribers] = useState(false);

  const canSubscribe = user.id !== item.createdBy.id && user.role.name === 'STUDENT';
  const hasSubscribed = item.subscribers.some(item => {
    return item.subscriber.id === user.id;
  });
  const isCreator = user.id === item.createdBy.id;

  async function deleteHandler() {
    if (!confirm('Are you sure you want to delete this homework?')) {
      return;
    }

    try {
      await apiClient.delete(`/agendaitem/${item.id}`);
      onDelete();
    } catch (err) {
      if (err.response.status === 403) {
        alert('You are not allowed to delete because there are subscribers');
      } else {
        alert('Something went wrong');
      }
    }
  }

  async function subscribeHandler() {
    await apiClient.post(`/agendaitem/${item.id}/subscribe`);
    onSubscribe();
  }

  return (
    <div className={classNames('agenda-item', isPast && 'is-past')}>
      <div className="agenda-item-information">
        <div className="agenda-item-info-1">
          <h1>{item.title}</h1>
          {Boolean(item.homework) && <p>Subject: {item.homework.subject.name}</p>}
          {Boolean(item.homework) && (
            <>
              <p>
                Homework:
                {item.homework.link ? (
                  <a href={item.homework.link} target="_blank" rel="noreferrer">
                    {item.homework.name}
                  </a>
                ) : (
                  item.homework.name
                )}
              </p>
              <p>Deadline: {dayjs(item.homework.deadline).format('DD-MM-YYYY')}</p>
            </>
          )}
          <div className="toggle-container">
            <Button className="show-hide-description" onClick={() => setShowMore(state => !state)}>
              Description
              {showMore ? <BiChevronUp size={15} /> : <BiChevronDown size={15} />}
            </Button>
            {showMore && (
              <div className="description">
                <p>{item.description}</p>
              </div>
            )}
          </div>
        </div>

        <div className="agenda-item-info-2">
          <p className="agenda-item-when">When: {dayjs(item.moment).format('DD/MM/YYYY HH:mm')}</p>
          <p>Hosted by: {item.createdBy.name}</p>
          {item.link && (
            <a href={item.link} target="_blank" rel="noreferrer">
              Link
            </a>
          )}
        </div>
      </div>
      <div className="agenda-item-button-section">
        <div>
          {item.subscribers.length > 0 && (
            <>
              <div className="dropdown">
                <Button
                  className="agenda-item-show-hide-subscribers"
                  onClick={() => setShowSubscribers(state => !state)}
                >
                  <p>{item.subscribers.length} Subscribers:</p>
                  {showSubscribers ? <BiChevronUp size={15} /> : <BiChevronDown size={15} />}
                </Button>
                {showSubscribers && (
                  <div className="dropdown-content">
                    <ul>
                      {item.subscribers.map(item => (
                        <li key={item.id}>
                          <a href={`mailto:${item.subscriber.email}`}>{item.subscriber.name}</a>
                        </li>
                      ))}
                    </ul>
                  </div>
                )}
              </div>
            </>
          )}
        </div>

        <div className="agenda-item-edit-button-section">
          <div className="agenda-item-buttons, agenda-item-delete-edit">
            {isCreator && (
              <Button className="agenda-item-buttons" onClick={deleteHandler}>
                Delete
              </Button>
            )}
            {isCreator && (
              <Button className="agenda-item-buttons" onClick={() => navigate(`edit/${item.id}`)}>
                Edit
              </Button>
            )}
          </div>
        </div>

        {canSubscribe && (
          <Button
            className="agenda-item-buttons, agenda-item-subscribe-button"
            onClick={subscribeHandler}
          >
            {hasSubscribed ? 'Unsubscribe' : 'Subscribe'}
          </Button>
        )}
      </div>
    </div>
  );
}
