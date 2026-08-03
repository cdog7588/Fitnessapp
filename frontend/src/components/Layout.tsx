import { NavLink, Outlet } from 'react-router-dom';

const navItems = [
  { to: '/', label: 'Dashboard' },
  { to: '/exercises', label: 'Exercises' },
  { to: '/plans', label: 'Plans' },
  { to: '/logger', label: 'Log Workout' },
  { to: '/analytics', label: 'Analytics' }
];

export default function Layout() {
  return (
    <div style={{ minHeight: '100vh', background: '#07111f', color: '#f5f7fb' }}>
      <div style={{ display: 'flex', minHeight: '100vh' }}>
        <aside style={{ width: 220, padding: '24px 16px', borderRight: '1px solid #1f2e46' }}>
          <h2 style={{ fontSize: 24, marginBottom: 24 }}>FitnessApp</h2>
          <nav style={{ display: 'flex', flexDirection: 'column', gap: 8 }}>
            {navItems.map((item) => (
              <NavLink
                key={item.to}
                to={item.to}
                style={({ isActive }) => ({
                  padding: '10px 12px',
                  borderRadius: 10,
                  textDecoration: 'none',
                  color: isActive ? '#7dd3fc' : '#cbd5e1'
                })}
              >
                {item.label}
              </NavLink>
            ))}
          </nav>
        </aside>
        <main style={{ flex: 1, padding: 24 }}>
          <Outlet />
        </main>
      </div>
    </div>
  );
}
