import { Formik, Form, Field } from 'formik';
import { useQuery } from 'react-query';
import { Link } from 'react-router-dom';

import { apiClient } from '../services/api';
import { Button } from './Button';
import { FormField } from './FormField';

const defaultValues = {
  name: '',
  username: '',
  email: '',
  password: '',
  roleId: '',
};

export function SignUpForm({ onSubmit, initialValues = defaultValues }) {
  const { data: roles } = useQuery('roles', async () => {
    const { data } = await apiClient.get('/role');
    return data;
  });

  return (
    <>
      <h1>Sign up</h1>

      <Formik onSubmit={onSubmit} initialValues={initialValues}>
        {({ isSubmitting }) => {
          return (
            <Form>
              <FormField type="text" name="name" label="Name" placeholder="First + Last Name" />

              <FormField type="text" name="username" label="Username" placeholder="Username" />

              <FormField type="email" name="email" label="E-mail" placeholder="E-mail" />

              <FormField type="password" name="password" label="Password" placeholder="Password" />

              <FormField as="select" name="roleId" label="Role">
                <option value="" disabled selected>
                  Pick one
                </option>
                {roles?.map(role => (
                  <option key={role.id} value={role.id}>
                    {role.name}
                  </option>
                ))}
              </FormField>

              <Button className="create-account-button" loading={isSubmitting} type="submit">
                Create account
              </Button>
            </Form>
          );
        }}
      </Formik>

      <p className="divider">or</p>

      <Link
        to={{
          search: '?mode=login',
        }}
      >
        <Button className="button signup-button button-outline" type="submit">
          Log in
        </Button>
      </Link>
    </>
  );
}
