import { useQuery, useQueryClient } from 'react-query';
import { Link, useNavigate, useParams } from 'react-router-dom';

import { BackButton } from '../../components/BackButton';
import { Drawer } from '../../components/Drawer';
import { SubjectForm } from '../../components/SubjectForm';
import { apiClient } from '../../services/api';

export function SubjectDetailPage() {
  const { id: subjectId } = useParams();
  const navigate = useNavigate();
  const queryClient = useQueryClient();

  const { data, isLoading } = useQuery(
    'subject',
    async () => {
      const { data } = await apiClient.get(`/subject/${subjectId}`);
      return data;
    },
    {
      enabled: Boolean(subjectId),
    },
  );

  async function submitHandler(values, form) {
    try {
      if (subjectId) {
        await apiClient.put(`/subject/${subjectId}`, values);
      } else {
        await apiClient.post('/subject', values);
      }
      await queryClient.invalidateQueries('subjects');
      navigate('/subjects');
    } catch {
      alert('Something went wrong');
    }
  }

  return (
    <Drawer onClose={() => navigate('/subjects')}>
      <BackButton to="/subjects" />

      <h2>{subjectId ? 'Edit' : 'Add'} subject</h2>
      {!isLoading && (
        <SubjectForm
          mode={subjectId ? 'edit' : 'create'}
          onSubmit={submitHandler}
          initialValues={subjectId && data}
        />
      )}
    </Drawer>
  );
}
