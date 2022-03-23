import { Formik, Form, Field } from 'formik';
import { Link } from 'react-router-dom';

import { Button } from './Button';
import { FormField } from './FormField';

const defaultValues = {};

export function LoginForm({ onSubmit, initialValues = defaultValues }) {
  return (
    <>
      <h1>Log in</h1>

      <Formik onSubmit={onSubmit} initialValues={initialValues}>
        {({ isSubmitting }) => {
          return (
            <Form>
              <FormField
                type="text"
                name="usernameOrEmail"
                label="Username / e-mail"
                placeholder="Username / e-mail"
              />

              <FormField type="password" name="password" label="Password" placeholder="Password" />

              <Button loading={isSubmitting} type="submit">
                Sign in
              </Button>
            </Form>
          );
        }}
      </Formik>

      <p>or</p>

      <Link
        to={{
          search: '?mode=sign-up',
        }}
      >
        Sign up
      </Link>
    </>
  );
}
