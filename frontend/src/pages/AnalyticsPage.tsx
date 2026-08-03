export default function AnalyticsPage() {
  return (
    <div className="container">
      <h1 style={{ fontSize: 28, marginBottom: 8 }}>Analytics</h1>
      <p style={{ color: '#94a3b8', marginBottom: 16 }}>Review workout summaries, progression, and recommendations.</p>
      <div style={{ display: 'grid', gap: 16, gridTemplateColumns: 'repeat(auto-fit, minmax(240px, 1fr))' }}>
        <div className="card">
          <h3>Workout summary</h3>
          <p style={{ color: '#7dd3fc' }}>Volume and session insights will appear here.</p>
        </div>
        <div className="card">
          <h3>Strength progression</h3>
          <p style={{ color: '#7dd3fc' }}>1RM trend and historical lifts will appear here.</p>
        </div>
        <div className="card">
          <h3>Recommendations</h3>
          <p style={{ color: '#7dd3fc' }}>Suggested loading adjustments will appear here.</p>
        </div>
      </div>
    </div>
  );
}
