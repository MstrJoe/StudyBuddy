import './index.css';

import React, { useContext, useState } from 'react';
import { Link, Route, Routes } from 'react-router-dom';

import { useUser } from './context/UserContext';
import { AgendaPage } from './pages/agenda';
import { HomeworkPage } from './pages/homework';
import { LoginPage } from './pages/login';
import { StudentDashboardPage } from './pages/student-dashboard';
import { SubjectDetailPage } from './pages/subjects/detail';
import { SubjectsPage } from './pages/subjects/subjects';
import { Layout } from './templates/Layout';

const DeveloperContext = React.createContext(null);

function AddAgeButton() {
  const { addYear } = useContext(DeveloperContext);

  return <button onClick={() => addYear()}>Increase age</button>;
}

function Developer({ name, age: initialAge }) {
  const [age, setAge] = useState(initialAge);

  function addYear(e) {
    setAge(age + 1);
  }

  return (
    <DeveloperContext.Provider
      value={{
        age,
        addYear,
      }}
    >
      <h2>
        {name} is {age} year old
      </h2>

      <AddAgeButton />
    </DeveloperContext.Provider>
  );
}

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
          label: 'Homework',
          to: '/homework',
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

        <Route path="/agenda" element={<AgendaPage />} />
        <Route path="/homework" element={<HomeworkPage />} />
        <Route path="/login" element={<LoginPage />}></Route>
      </Routes>
    </Layout>
  );

  return <div className="App">Your name is {user.name}</div>;
}

export default App;
