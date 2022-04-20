import axios from 'axios';
import { Link, useNavigate } from 'react-router-dom';

import { Button } from '../Button';
import { useUser } from '../../context/UserContext';
import './Layout.css';

export function Layout({ navigationItems = [], children }) {
  const { logout } = useUser();

  return (
    <>
      <div className="header">
        <div>
          <div>
            <div className="logo">Logo</div>
          </div>
          <nav className="nav-list">
            {navigationItems.map(item => {
              return (
                <Link key={item.to} to={item.to}>
                  {item.label}
                </Link>
              );
            })}
          </nav>
        </div>

        <div className="actions">
          <Button onClick={logout}>Logout</Button>

          <Link className="profile-link" to="/profile">
            Profile
          </Link>
        </div>
      </div>

      <main>{children}</main>
    </>
  );
}
