import './Button.scss';

import classNames from 'classnames';

export function Button({ children, className, loading = false, ...props }) {
  return (
    <button className={classNames('button', className)} {...props} disabled={loading}>
      {loading ? 'Loading' : children}
    </button>
  );
}
