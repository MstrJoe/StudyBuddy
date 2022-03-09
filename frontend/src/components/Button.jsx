import styles from './Button.module.css';

export function Button({ children, loading = false, ...props }) {
  return (
    <button className={styles.button} {...props} disabled={loading}>
      {loading ? 'Loading' : children}
    </button>
  );
}
