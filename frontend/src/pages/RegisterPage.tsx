import { useState } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import axios from 'axios';

export default function RegisterPage() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [message, setMessage] = useState('');
  const navigate = useNavigate();

  async function handleSubmit(e: React.FormEvent) {
    e.preventDefault();
    try {
      await axios.post('/auth/register', { username, password });
      setMessage('Account created. You can sign in now.');
      setTimeout(() => navigate('/login'), 800);
    } catch {
      setMessage('Could not create account.');
    }
  }

  return (
    <div style={{ minHeight: '100vh', display: 'flex', alignItems: 'center', justifyContent: 'center', background: 'linear-gradient(135deg, #07111f, #0f172a)' }}>
      <div className="card" style={{ width: 420, padding: 24 }}>
        <h1 style={{ fontSize: 28, marginBottom: 8 }}>Create account</h1>
        <p style={{ color: '#94a3b8', marginBottom: 20 }}>Start building your strength plan.</p>
        <form onSubmit={handleSubmit} style={{ display: 'flex', flexDirection: 'column', gap: 12 }}>
          <input value={username} onChange={(e) => setUsername(e.target.value)} placeholder="Username" style={{ padding: 12, borderRadius: 10, border: '1px solid #334155' }} />
          <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} placeholder="Password" style={{ padding: 12, borderRadius: 10, border: '1px solid #334155' }} />
          {message && <div style={{ color: '#7dd3fc' }}>{message}</div>}
          <button type="submit" style={{ padding: 12, borderRadius: 10, background: '#38bdf8', color: '#07111f', border: 'none', cursor: 'pointer' }}>Create account</button>
        </form>
        <p style={{ marginTop: 16, color: '#94a3b8' }}><Link to="/login" style={{ color: '#7dd3fc' }}>Back to sign in</Link></p>
      </div>
    </div>
  );
}
