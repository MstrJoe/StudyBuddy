import dayjs from 'dayjs';
import { Form, Formik } from 'formik';

import { Button } from './Button';
import { FormField } from './FormField';

const defaultValues = {
  name: '',
  description: '',
  link: '',
  deadlineDate: dayjs(new Date()).format('YYYY-MM-DD'),
  deadlineTime: dayjs(new Date()).format('HH:mm'),
};

export function HomeworkForm({ initialValues = defaultValues, onSubmit, mode }) {
  return (
    <Formik enableReinitialize initialValues={initialValues} onSubmit={onSubmit}>
      <Form>
        <FormField
          autoFocus
          required
          name="name"
          label="Homework name"
          placeholder="Insert name here"
        ></FormField>
        <FormField
          required
          as="textarea"
          name="description"
          label="Description"
          placeholder="Insert homework description"
        ></FormField>
        <FormField name="link" type="url" label="Link" placeholder="http://example.com"></FormField>
        <FormField
          required
          name="deadlineDate"
          type="date"
          min={dayjs(new Date()).format('YYYY-MM-DD')}
          label="Homework deadline date"
        ></FormField>

        <FormField
          required
          name="deadlineTime"
          type="time"
          label="Homework deadline time"
        ></FormField>

        <Button type="submit">{mode === 'edit' ? 'Edit' : 'Add'} homework</Button>
      </Form>
    </Formik>
  );
}
