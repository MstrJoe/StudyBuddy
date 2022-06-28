import './index.css';
import utc from 'dayjs/plugin/utc';
import timezone from 'dayjs/plugin/timezone';
import dayjs from 'dayjs';
import React from 'react';
import { Route, Routes, Navigate } from 'react-router-dom';

import { Layout } from './components/templates/Layout';
import { useUser } from './context/UserContext';
import { AgendaPage } from './pages/agenda/agenda';
import { AgendaItemCreatePage } from './pages/agenda/agenda-create';
import { AgendaItemEditPage } from './pages/agenda/agenda-edit';
import { LoginPage } from './pages/login';
import { ProfilePage } from './pages/profile';
import { SubjectDetailPage } from './pages/subjects/detail';
import { HomeworkCreatePage } from './pages/subjects/homework-create';
import { HomeworkEditPage } from './pages/subjects/homework-edit';
import { SubjectsPage } from './pages/subjects/subjects';

// set the default timezone
dayjs.extend(utc);
dayjs.extend(timezone);
dayjs.tz.setDefault('Europe/Amsterdam');

const teacherNav = [
  {
    label: 'Subjects',
    to: 'subjects',
    icon: null,
  },
  {
    label: 'Agenda',
    to: 'agenda',
    icon: null,
  },
];

const studentNav = [
  {
    label: 'Agenda',
    to: 'agenda',
    icon: null,
  },
];

function App() {
  const { user, isLoading } = useUser();

  if (isLoading) {
    return null;
  }

  if (!user) {
    return <LoginPage />;
  }

  const isTeacher = user.role.name === 'TEACHER';

  return (
    <Layout navigationItems={isTeacher ? teacherNav : studentNav}>
      <Routes>
        {isTeacher && (
          <Route path="/subjects" element={<SubjectsPage />}>
            <Route path="add" element={<SubjectDetailPage />} />
            <Route path="edit/:id" element={<SubjectDetailPage />} />
            <Route path=":subjectId/create-homework" element={<HomeworkCreatePage />} />
            <Route path="homework/edit/:id" element={<HomeworkEditPage />} />
          </Route>
        )}

        <Route path="/agenda" element={<AgendaPage />}>
          <Route path="add" element={<AgendaItemCreatePage />} />
          <Route path="edit/:id" element={<AgendaItemEditPage />} />
        </Route>

        <Route path="/profile" element={<ProfilePage />} />

        <Route path="*" element={<Navigate to={'/agenda'} />} />
      </Routes>
    </Layout>
  );
}

export default App;
