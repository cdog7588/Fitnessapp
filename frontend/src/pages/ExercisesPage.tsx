import { useEffect, useState } from 'react';
import axios from 'axios';

export default function ExercisesPage() {
  const [exercises, setExercises] = useState<any[]>([]);

  useEffect(() => {
    axios.get('/exercises').then((res) => setExercises(res.data)).catch(() => {});
  }, []);

  return (
    <div className="container">
      <h1 style={{ fontSize: 28, marginBottom: 8 }}>Exercise library</h1>
      <p style={{ color: '#94a3b8', marginBottom: 16 }}>Browse the available exercises from the backend.</p>
      <div style={{ display: 'grid', gap: 12, gridTemplateColumns: 'repeat(auto-fit, minmax(240px, 1fr))' }}>
        {exercises.map((exercise) => (
          <div key={exercise.id} className="card">
            <h3>{exercise.name}</h3>
            <p style={{ color: '#94a3b8' }}>{exercise.description || 'No description available.'}</p>
            <p style={{ color: '#7dd3fc' }}>{exercise.equipmentType || 'Equipment not specified'}</p>
          </div>
        ))}
      </div>
    </div>
  );
}
