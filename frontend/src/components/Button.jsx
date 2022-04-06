import './Button.css';

export function Button({ children, loading = false, ...props }) {
  return (
    <button className="button" {...props} disabled={loading}>
      {loading ? 'Loading' : children}
    </button>
  );
}
