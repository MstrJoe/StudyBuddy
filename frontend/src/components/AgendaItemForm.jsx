import dayjs from 'dayjs';
import { Form, Formik } from 'formik';

import { Button } from './Button';
import { FormField } from './FormField';

const defaultValues = {
  title: '',
  description: '',
  link: '',
  startDate: dayjs(new Date()).format('YYYY-MM-DD'),
  startTime: dayjs(new Date()).format('HH:mm'),
};

export function AgendaItemForm({ initialValues = defaultValues, onSubmit, mode }) {
  return (
    <Formik enableReinitialize initialValues={initialValues} onSubmit={onSubmit}>
      <Form>
        <FormField required name="title" label="Title" placeholder="Insert title here"></FormField>
        <FormField
          as="textarea"
          name="description"
          label="Description"
          placeholder="Insert description"
        ></FormField>
        <FormField name="link" type="url" label="Link" placeholder="http://example.com"></FormField>
        <FormField
          required
          name="startDate"
          type="date"
          min={dayjs(new Date()).format('YYYY-MM-DD')}
          label="Start date"
        ></FormField>

        <FormField required name="startTime" type="time" label="Start time"></FormField>

        <Button type="submit">{mode === 'edit' ? 'Edit' : 'Add'} agenda item</Button>
      </Form>
    </Formik>
  );
}
