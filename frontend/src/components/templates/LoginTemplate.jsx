import './LoginTemplate.scss';

export function LoginTemplate({ children }) {
  return (
    <div className="login-template">
      <div className="login-form">{children}</div>
    </div>
  );
}
