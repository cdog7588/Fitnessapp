import { useEffect, useState } from 'react';
import axios from 'axios';

export default function DashboardPage() {
  const [plans, setPlans] = useState<any[]>([]);
  const [exercises, setExercises] = useState<any[]>([]);

  useEffect(() => {
    axios.get('/plans').then((res) => setPlans(res.data)).catch(() => {});
    axios.get('/exercises').then((res) => setExercises(res.data)).catch(() => {});
  }, []);

  return (
    <div className="container">
      <h1 style={{ fontSize: 32, marginBottom: 8 }}>Training dashboard</h1>
      <p style={{ color: '#94a3b8', marginBottom: 24 }}>Plan, train, and review your strength work.</p>
      <div style={{ display: 'grid', gap: 16, gridTemplateColumns: 'repeat(auto-fit, minmax(220px, 1fr))' }}>
        <div className="card">
          <h3>Available plans</h3>
          <p style={{ color: '#7dd3fc', fontSize: 26, margin: 0 }}>{plans.length}</p>
        </div>
        <div className="card">
          <h3>Exercises</h3>
          <p style={{ color: '#7dd3fc', fontSize: 26, margin: 0 }}>{exercises.length}</p>
        </div>
        <div className="card">
          <h3>Next session</h3>
          <p style={{ color: '#cbd5e1' }}>Open a plan to generate your workout.</p>
        </div>
      </div>
    </div>
  );
}
