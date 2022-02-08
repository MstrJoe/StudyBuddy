import { Formik, Form, Field } from 'formik';
import { useQuery } from 'react-query';
import { Link } from 'react-router-dom';

import { apiClient } from '../services/api';
import { Button } from './Button';
import { FormField } from './FormField';

export function SignUpForm({ onSubmit, initialValues }) {
  const { data: roles } = useQuery('roles', () => apiClient.get('/role').then(res => res.data));

  return (
    <>
      <h1>Sign up</h1>

      <Formik onSubmit={onSubmit} initialValues={initialValues}>
        {({ isSubmitting }) => {
          return (
            <Form>
              <FormField type="text" name="name" label="Name" placeholder="Name" />

              <FormField type="text" name="username" label="Username" placeholder="Username" />

              <FormField type="email" name="email" label="E-mail" placeholder="E-mail" />

              <FormField type="password" name="password" label="Password" placeholder="Password" />

              <FormField as="select" name="roleId" label="Role">
                {roles?.map(role => (
                  <option key={role.id} value={role.id}>
                    {role.name}
                  </option>
                ))}
              </FormField>

              <Button loading={isSubmitting} type="submit">
                Create account
              </Button>
            </Form>
          );
        }}
      </Formik>

      <p>or</p>

      <Link
        to={{
          search: '?mode=login',
        }}
      >
        Log in
      </Link>
    </>
  );
}
