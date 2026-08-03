import { Navigate, Route, Routes } from 'react-router-dom';
import Layout from './components/Layout';
import LoginPage from './pages/LoginPage';
import RegisterPage from './pages/RegisterPage';
import DashboardPage from './pages/DashboardPage';
import ExercisesPage from './pages/ExercisesPage';
import PlansPage from './pages/PlansPage';
import PlanDetailPage from './pages/PlanDetailPage';
import GeneratorPage from './pages/GeneratorPage';
import WorkoutLoggerPage from './pages/WorkoutLoggerPage';
import AnalyticsPage from './pages/AnalyticsPage';

const token = localStorage.getItem('token');

function ProtectedRoute({ children }: { children: JSX.Element }) {
  return token ? children : <Navigate to="/login" replace />;
}

export default function App() {
  return (
    <Routes>
      <Route path="/login" element={<LoginPage />} />
      <Route path="/register" element={<RegisterPage />} />
      <Route path="/" element={<ProtectedRoute><Layout /></ProtectedRoute>}>
        <Route index element={<DashboardPage />} />
        <Route path="exercises" element={<ExercisesPage />} />
        <Route path="plans" element={<PlansPage />} />
        <Route path="plans/:id" element={<PlanDetailPage />} />
        <Route path="generator/:dayId" element={<GeneratorPage />} />
        <Route path="logger" element={<WorkoutLoggerPage />} />
        <Route path="analytics" element={<AnalyticsPage />} />
      </Route>
    </Routes>
  );
}
