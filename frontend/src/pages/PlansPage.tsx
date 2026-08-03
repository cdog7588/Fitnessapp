import { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import axios from 'axios';

export default function PlansPage() {
  const [plans, setPlans] = useState<any[]>([]);

  useEffect(() => {
    axios.get('/plans').then((res) => setPlans(res.data)).catch(() => {});
  }, []);

  return (
    <div className="container">
      <h1 style={{ fontSize: 28, marginBottom: 8 }}>Workout plans</h1>
      <p style={{ color: '#94a3b8', marginBottom: 16 }}>Browse training plans from the backend.</p>
      <div style={{ display: 'grid', gap: 12 }}>
        {plans.map((plan) => (
          <div key={plan.id} className="card" style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
            <div>
              <h3>{plan.name || `Plan ${plan.id}`}</h3>
              <p style={{ color: '#94a3b8', margin: 0 }}>{plan.description || 'Structured training plan'}</p>
            </div>
            <Link to={`/plans/${plan.id}`} style={{ color: '#7dd3fc', textDecoration: 'none' }}>View</Link>
          </div>
        ))}
      </div>
    </div>
  );
}
