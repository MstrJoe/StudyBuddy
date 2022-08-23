import dayjs from 'dayjs';
import { Form, Formik } from 'formik';

import { Button } from './Button';
import { FormField } from './FormField';
import { isDateToday } from '../utils/isDateToday';

export function AgendaItemForm({ initialValues, onSubmit, mode, subjects }) {
  const defaultValues = {
    title: '',
    subjectId: '',
    homeworkId: '',
    description: '',
    link: '',
    startDate: dayjs(new Date()).format('YYYY-MM-DD'),
    startTime: dayjs(new Date()).format('HH:mm'),
  };

  return (
    <Formik enableReinitialize initialValues={initialValues || defaultValues} onSubmit={onSubmit}>
      {({ values }) => (
        <Form>
          {mode === 'create' && (
            <FormField
              name="subjectId"
              placeholder="Choose subject"
              label="Subject"
              as="select"
              disabled={mode === 'edit'}
            >
              <option value="" disabled hidden>
                Choose subject
              </option>
              {subjects.map(subject => (
                <option key={subject.id} value={subject.id}>
                  {subject.name}
                </option>
              ))}
            </FormField>
          )}

          {values.subjectId && (
            <FormField name="homeworkId" label="Homework" as="select" disabled={mode === 'edit'}>
              <option value="" disabled hidden>
                Choose homework
              </option>
              {subjects
                .find(subject => subject.id === +values.subjectId)
                ?.homework.map(homework => (
                  <option key={homework.id} value={homework.id}>
                    {homework.name}
                  </option>
                ))}
            </FormField>
          )}

          <FormField
            required
            name="title"
            label="Title"
            placeholder="Insert title here"
          ></FormField>
          <FormField
            as="textarea"
            name="description"
            label="Description"
            placeholder="Insert description"
            required
          ></FormField>
          <FormField
            name="link"
            type="url"
            label="Link"
            placeholder="http://example.com"
          ></FormField>
          <FormField
            required
            name="startDate"
            type="date"
            min={dayjs(new Date()).format('YYYY-MM-DD')}
            label="Start date"
          ></FormField>
          <FormField
            required
            name="startTime"
            type="time"
            min={isDateToday(values.startDate) ? dayjs(new Date()).format('HH:mm') : undefined}
            label="Start time"
          ></FormField>
          <Button type="submit">{mode === 'edit' ? 'Edit' : 'Add'} agenda item</Button>
        </Form>
      )}
    </Formik>
  );
}
