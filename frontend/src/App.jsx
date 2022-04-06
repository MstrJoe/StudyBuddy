import './index.css';

import React, { useContext, useState } from 'react';
import { Link, Route, Routes } from 'react-router-dom';

import { useUser } from './context/UserContext';
import { AgendaPage } from './pages/agenda/agenda';
import { AgendaItemCreatePage } from './pages/agenda/agenda-create';
import { AgendaItemEditPage } from './pages/agenda/agenda-edit';
import { LoginPage } from './pages/login';
import { ProfilePage } from './pages/profile';
import { StudentDashboardPage } from './pages/student-dashboard';
import { SubjectDetailPage } from './pages/subjects/detail';
import { HomeworkCreatePage } from './pages/subjects/homework-create';
import { HomeworkEditPage } from './pages/subjects/homework-edit';
import { SubjectsPage } from './pages/subjects/subjects';
import { Layout } from './templates/Layout';

const teacherNav = [
  {
    label: 'Subjects',
    to: '/subjects',
    icon: null,
  },
  {
    label: 'Agenda',
    to: '/agenda',
    icon: null,
  },
];

const studentNav = [
  {
    label: 'Agenda',
    to: '/agenda',
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

  return (
    <Layout navigationItems={user.role.name === 'TEACHER' ? teacherNav : studentNav}>
      <Routes>
        <Route path="/" element={<StudentDashboardPage />} />
        <Route path="/subjects" element={<SubjectsPage />}>
          <Route path="add" element={<SubjectDetailPage />} />
          <Route path="edit/:id" element={<SubjectDetailPage />} />
          <Route path=":subjectId/create-homework" element={<HomeworkCreatePage />} />
          <Route path="homework/edit/:id" element={<HomeworkEditPage />} />
        </Route>

        <Route path="/agenda" element={<AgendaPage />}>
          <Route path="add" element={<AgendaItemCreatePage />} />
          <Route path="edit/:id" element={<AgendaItemEditPage />} />
        </Route>

        <Route path="/profile" element={<ProfilePage />} />
      </Routes>
    </Layout>
  );
}

export default App;
