import { Field } from 'formik';

import styles from './FormField.module.css';

export function FormField({
  label,
  name,
  placeholder = '',
  type = 'text',
  as = undefined,
  className = undefined,
  ...props
}) {
  return (
    <div className={styles.wrapper}>
      <label htmlFor={name} className={styles.label}>
        {label}
      </label>
      <Field
        className={styles.input}
        type={type}
        as={as}
        name={name}
        placeholder={placeholder}
        {...props}
      />
    </div>
  );
}
