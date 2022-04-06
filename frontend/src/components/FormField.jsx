import { Field } from 'formik';

import './FormField.scss';

export function FormField({
  label,
  name,
  placeholder = '',
  type = 'text',
  as = undefined,
  className = '',
  ...props
}) {
  return (
    <div className="input-wrapper">
      <label htmlFor={name} className="label">
        {label}
      </label>
      <Field
        className={`input ${className}`}
        type={type}
        as={as}
        name={name}
        placeholder={placeholder}
        {...props}
      />
    </div>
  );
}
