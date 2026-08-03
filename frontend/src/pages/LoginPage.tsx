import { useState } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import axios from 'axios';

export default function LoginPage() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const navigate = useNavigate();

  async function handleSubmit(e: React.FormEvent) {
    e.preventDefault();
    try {
      const res = await axios.post('/auth/login', { username, password });
      localStorage.setItem('token', res.data.token);
      navigate('/');
    } catch (err) {
      setError('Invalid username or password');
    }
  }

  return (
    <div style={{ minHeight: '100vh', display: 'flex', alignItems: 'center', justifyContent: 'center', background: 'linear-gradient(135deg, #07111f, #0f172a)' }}>
      <div className="card" style={{ width: 420, padding: 24 }}>
        <h1 style={{ fontSize: 28, marginBottom: 8 }}>Welcome back</h1>
        <p style={{ color: '#94a3b8', marginBottom: 20 }}>Sign in to your training dashboard.</p>
        <form onSubmit={handleSubmit} style={{ display: 'flex', flexDirection: 'column', gap: 12 }}>
          <input value={username} onChange={(e) => setUsername(e.target.value)} placeholder="Username" style={{ padding: 12, borderRadius: 10, border: '1px solid #334155' }} />
          <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} placeholder="Password" style={{ padding: 12, borderRadius: 10, border: '1px solid #334155' }} />
          {error && <div style={{ color: '#fda4af' }}>{error}</div>}
          <button type="submit" style={{ padding: 12, borderRadius: 10, background: '#38bdf8', color: '#07111f', border: 'none', cursor: 'pointer' }}>Sign In</button>
        </form>
        <p style={{ marginTop: 16, color: '#94a3b8' }}>No account yet? <Link to="/register" style={{ color: '#7dd3fc' }}>Create one</Link></p>
      </div>
    </div>
  );
}
