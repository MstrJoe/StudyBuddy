import { Formik, Form, Field } from 'formik';
import { Link } from 'react-router-dom';

import { Button } from './Button';
import { FormField } from './FormField';

const defaultValues = {};

export function LoginForm({ onSubmit, initialValues = defaultValues }) {
  return (
    <>
      <h1 className="login-title">Log in</h1>

      <Formik onSubmit={onSubmit} initialValues={initialValues}>
        {({ isSubmitting }) => {
          return (
            <Form>
              <FormField
                className="full-width"
                type="text"
                name="usernameOrEmail"
                label="Username / e-mail"
                placeholder="Username / e-mail"
              />

              <FormField type="password" name="password" label="Password" placeholder="Password" />

              <Button className="signin-button" loading={isSubmitting} type="submit">
                Log in
              </Button>
            </Form>
          );
        }}
      </Formik>

      <p className="divider">or</p>

      <Link
        className="button signup-button button-outline "
        to={{
          search: '?mode=sign-up',
        }}
      >
        Sign up
      </Link>
    </>
  );
}
