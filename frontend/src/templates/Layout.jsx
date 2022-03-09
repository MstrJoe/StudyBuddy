import { Link } from 'react-router-dom';
import './Layout.css';

export function Layout({ navigationItems = [], children }) {
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
