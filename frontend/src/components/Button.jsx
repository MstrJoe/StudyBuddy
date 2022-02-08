import styles from './Button.module.css';

export function Button({ children, loading, ...props }) {
  return (
    <button className={styles.button} {...props} disabled={loading}>
      {loading ? 'Loading' : children}
    </button>
  );
}
