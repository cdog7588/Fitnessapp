import { useState } from 'react';

export default function WorkoutLoggerPage() {
  const [entries, setEntries] = useState([{ exercise: 'Bench Press', setNumber: 1, targetReps: 5, targetWeight: 135, actualReps: 5, actualWeight: 135 }]);

  function updateEntry(index: number, field: string, value: string) {
    const updated = [...entries];
    (updated[index] as any)[field] = value;
    setEntries(updated);
  }

  return (
    <div className="container">
      <h1 style={{ fontSize: 28, marginBottom: 8 }}>Workout logger</h1>
      <p style={{ color: '#94a3b8', marginBottom: 16 }}>Log your completed sets for the session.</p>
      {entries.map((entry, index) => (
        <div key={index} className="card" style={{ marginBottom: 12 }}>
          <h3>{entry.exercise}</h3>
          <div style={{ display: 'grid', gap: 10, gridTemplateColumns: 'repeat(auto-fit, minmax(140px, 1fr))' }}>
            <input value={entry.setNumber} onChange={(e) => updateEntry(index, 'setNumber', e.target.value)} placeholder="Set #" />
            <input value={entry.targetReps} onChange={(e) => updateEntry(index, 'targetReps', e.target.value)} placeholder="Target reps" />
            <input value={entry.targetWeight} onChange={(e) => updateEntry(index, 'targetWeight', e.target.value)} placeholder="Target weight" />
            <input value={entry.actualReps} onChange={(e) => updateEntry(index, 'actualReps', e.target.value)} placeholder="Actual reps" />
            <input value={entry.actualWeight} onChange={(e) => updateEntry(index, 'actualWeight', e.target.value)} placeholder="Actual weight" />
          </div>
        </div>
      ))}
    </div>
  );
}
