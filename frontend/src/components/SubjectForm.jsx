import { Form, Formik } from 'formik';

import { Button } from './Button';
import { FormField } from './FormField';

const defaultValues = { name: '' };

export function SubjectForm({ initialValues = defaultValues, onSubmit }) {
  return (
    <Formik initialValues={initialValues} onSubmit={onSubmit}>
      <Form>
        <FormField name="name" label="Subject name" placeholder="Insert name here"></FormField>

        <Button type="submit">Add subject</Button>
      </Form>
    </Formik>
  );
}
