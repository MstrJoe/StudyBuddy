import './Layout.scss';

import classNames from 'classnames';
import { Link, NavLink, useNavigate } from 'react-router-dom';

import { useUser } from '../../context/UserContext';
import { Button } from '../Button';
import { Logo } from '../Logo';

export function Layout({ navigationItems = [], children }) {
  const { logout } = useUser();

  return (
    <>
      <div className="header">
        <div className="navigation-wrapper">
          <div className="logo">
            <Logo />
          </div>
          <nav className="nav-list">
            {navigationItems.map(item => {
              return (
                <NavLink className="nav-item" key={item.to} to={item.to}>
                  {item.label}
                </NavLink>
              );
            })}
          </nav>
        </div>

        <div className="actions">
          <NavLink className="nav-item" to="/profile">
            Profile
          </NavLink>

          <Button className="button-transparent" onClick={logout}>
            Logout
          </Button>
        </div>
      </div>

      <main>{children}</main>
    </>
  );
}
