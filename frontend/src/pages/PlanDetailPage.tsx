import { useEffect, useState } from 'react';
import { useParams, Link } from 'react-router-dom';
import axios from 'axios';

export default function PlanDetailPage() {
  const { id } = useParams();
  const [plan, setPlan] = useState<any>(null);

  useEffect(() => {
    axios.get(`/plans/${id}`).then((res) => setPlan(res.data)).catch(() => {});
  }, [id]);

  if (!plan) return <div className="container">Loading...</div>;

  return (
    <div className="container">
      <h1 style={{ fontSize: 28, marginBottom: 8 }}>{plan.name || `Plan ${plan.id}`}</h1>
      <p style={{ color: '#94a3b8', marginBottom: 16 }}>{plan.description || 'Training plan detail'}</p>
      <div style={{ display: 'grid', gap: 12 }}>
        {plan.workoutDays?.map((day: any) => (
          <div key={day.id} className="card">
            <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
              <h3>{day.name || `Day ${day.id}`}</h3>
              <Link to={`/generator/${day.id}`} style={{ color: '#7dd3fc', textDecoration: 'none' }}>Generate</Link>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}
