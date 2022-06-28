import { Form, Formik } from 'formik';

import { Button } from './Button';
import { FormField } from './FormField';

const defaultValues = { name: '' };

export function SubjectForm({ initialValues = defaultValues, onSubmit, mode }) {
  return (
    <Formik enableReinitialize initialValues={initialValues} onSubmit={onSubmit}>
      <Form>
        <FormField
          required
          autoFocus
          name="name"
          label="Subject name"
          placeholder="Insert name here"
        ></FormField>

        <Button type="submit">{mode === 'edit' ? 'Edit' : 'Add'} subject</Button>
      </Form>
    </Formik>
  );
}
