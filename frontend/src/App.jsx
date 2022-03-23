import './index.css';

import React, { useContext, useState } from 'react';
import { Link, Route, Routes } from 'react-router-dom';

import { useUser } from './context/UserContext';
import { AgendaPage } from './pages/agenda';
import { LoginPage } from './pages/login';
import { StudentDashboardPage } from './pages/student-dashboard';
import { SubjectDetailPage } from './pages/subjects/detail';
import { SubjectsPage } from './pages/subjects/subjects';
import { Layout } from './templates/Layout';
import { HomeworkCreatePage } from './pages/subjects/homework-create';
import { HomeworkEditPage } from './pages/subjects/homework-edit';

function App() {
  const { user, isLoading } = useUser();

  if (isLoading) {
    return <div>Loading</div>;
  }
  if (!user) {
    return <LoginPage />;
  }

  return (
    <Layout
      navigationItems={[
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
      ]}
    >
      {/* <nav>
        <Link to="/">Home</Link>
        <Link to="/agenda">Agenda</Link>
        <Link to="/homework">Homework</Link>
      </nav> */}
      <Routes>
        <Route path="/" element={<StudentDashboardPage />} />
        <Route path="/subjects" element={<SubjectsPage />} />
        <Route path="/subjects/add" element={<SubjectDetailPage />} />
        <Route path="/subjects/edit/:id" element={<SubjectDetailPage />} />
        <Route path="/subjects/:subjectId/create-homework" element={<HomeworkCreatePage />} />



        <Route path="/agenda" element={<AgendaPage />} />
        <Route path="/homework/edit/:id" element={<HomeworkEditPage />} />
        <Route path="/login" element={<LoginPage />}></Route>
      </Routes>
    </Layout>
  );

  return <div className="App">Your name is {user.name}</div>;
}

export default App;
