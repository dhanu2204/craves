import { useState } from 'react';
import { api } from '../api/apiClient';

export default function AuthPage({ onLogin }) {
  const [isLogin, setIsLogin] = useState(true);
  const [formData, setFormData] = useState({ name: '', email: '', password: '', phone_number: '' });
  const [error, setError] = useState('');

  const handleChange = (e) => setFormData({ ...formData, [e.target.name]: e.target.value });

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError('');
    try {
      let user;
      if (isLogin) {
        user = await api.login(formData.email, formData.password);
      } else {
        user = await api.register(formData);
      }
      onLogin(user);
    } catch (err) {
      setError(err.message || 'Authentication failed');
    }
  };

  return (
    <div className="flex justify-center items-center" style={{ minHeight: '70vh' }}>
      <div className="glass glass-panel" style={{ width: '100%', maxWidth: '400px' }}>
        <h2 className="text-center">{isLogin ? 'Welcome Back' : 'Create Account'}</h2>
        
        {error && (
          <div style={{ padding: '12px', background: 'rgba(255, 71, 87, 0.2)', color: 'var(--primary-color)', borderRadius: '8px', marginBottom: '16px', fontSize: '0.9rem' }}>
            {error}
          </div>
        )}

        <form onSubmit={handleSubmit} className="flex-col gap-4">
          {!isLogin && (
            <div className="form-group">
              <label className="form-label">Name</label>
              <input type="text" name="name" className="form-control" placeholder="John Doe" onChange={handleChange} required />
            </div>
          )}
          
          <div className="form-group">
            <label className="form-label">Email</label>
            <input type="email" name="email" className="form-control" placeholder="john@example.com" onChange={handleChange} required />
          </div>

          {!isLogin && (
            <div className="form-group">
              <label className="form-label">Phone</label>
              <input type="tel" name="phone_number" className="form-control" placeholder="1234567890" onChange={handleChange} required />
            </div>
          )}

          <div className="form-group">
            <label className="form-label">Password</label>
            <input type="password" name="password" className="form-control" placeholder="••••••••" onChange={handleChange} required />
          </div>

          <button type="submit" className="btn" style={{ width: '100%', marginTop: '8px' }}>
            {isLogin ? 'Sign In' : 'Sign Up'}
          </button>
        </form>

        <p className="text-center mt-4" style={{ fontSize: '0.9rem' }}>
          {isLogin ? "Don't have an account? " : "Already have an account? "}
          <span 
            onClick={() => setIsLogin(!isLogin)} 
            style={{ color: 'var(--primary-color)', cursor: 'pointer', fontWeight: '500' }}
          >
            {isLogin ? 'Sign Up' : 'Sign In'}
          </span>
        </p>
      </div>
    </div>
  );
}
