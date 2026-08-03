import { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import axios from 'axios';

export default function GeneratorPage() {
  const { dayId } = useParams();
  const [workout, setWorkout] = useState<any>(null);

  useEffect(() => {
    axios.get(`/generator/day/${dayId}`).then((res) => setWorkout(res.data)).catch(() => {});
  }, [dayId]);

  if (!workout) return <div className="container">Loading...</div>;

  return (
    <div className="container">
      <h1 style={{ fontSize: 28, marginBottom: 8 }}>Generated workout</h1>
      <p style={{ color: '#94a3b8', marginBottom: 16 }}>Recommended exercises and sets for the selected day.</p>
      <div className="card">
        <h3>{workout.name || 'Workout'}</h3>
        <pre style={{ whiteSpace: 'pre-wrap', color: '#cbd5e1' }}>{JSON.stringify(workout, null, 2)}</pre>
      </div>
    </div>
  );
}
