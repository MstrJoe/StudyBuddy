import { Form, Formik } from 'formik';

import { Button } from './Button';
import { FormField } from './FormField';

const defaultValues = { name: '' };

export function HomeworkForm({ initialValues = defaultValues, onSubmit, mode }) {
  return (
    <Formik enableReinitialize initialValues={initialValues} onSubmit={onSubmit}>
      <Form>
        <FormField required name="name" label="Homework name" placeholder="Insert name here"></FormField>
        <FormField name="description" label="Description" placeholder="Insert homework description"></FormField>
        <FormField name="link" type="url" label="Link" placeholder="http://example.com"></FormField>
        <FormField required name="deadline" type="datetime-local" label="Homework deadline" placeholder="Set deadline date and time"></FormField>

        <Button type="submit">{mode === 'edit' ? 'Edit' : 'Add'} homework</Button>
      </Form>
    </Formik>
  );
}
