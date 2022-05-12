import { Logo } from '../Logo';
import './LoginTemplate.scss';

export function LoginTemplate({ children }) {
  return (
    <div className="login-template">
      <div className="logo-wrapper">
        <Logo />
      </div>
      <div className="login-form">{children}</div>
    </div>
  );
}
