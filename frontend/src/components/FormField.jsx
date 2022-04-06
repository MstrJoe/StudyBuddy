import { Field } from 'formik';

import './FormField.css';

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
    <div className="input-wrapper">
      <label htmlFor={name} className="label">
        {label}
      </label>
      <Field
        className="input"
        type={type}
        as={as}
        name={name}
        placeholder={placeholder}
        {...props}
      />
    </div>
  );
}
