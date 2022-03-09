import { Link } from 'react-router-dom';

import { SubjectForm } from '../../components/SubjectForm';
import { apiClient } from '../../services/api';

export function SubjectDetailPage() {
  function submitHandler(values, form) {
    apiClient.post('/subject', values);
  }

  return (
    <div>
      <Link to="/subjects">Back</Link>
      <h2>Add form</h2>
      <SubjectForm onSubmit={submitHandler} />
    </div>
  );
}
