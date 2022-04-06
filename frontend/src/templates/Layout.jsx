import axios from 'axios';
import { Link, useNavigate } from 'react-router-dom';

import { Button } from '../components/Button';
import { useUser } from '../context/UserContext';
import './Layout.css';

export function Layout({ navigationItems = [], children }) {
  const { logout } = useUser();

  return (
    <>
      <div className="sidebar">
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

        <Button onClick={logout}>Logout</Button>
      </div>

      <div className="profile-nav">
        <Link className="profile-link" to="/profile">
          Profile
        </Link>
      </div>

      <main>{children}</main>
    </>
  );
}
