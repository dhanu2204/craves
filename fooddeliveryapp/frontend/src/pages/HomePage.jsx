import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { api } from '../api/apiClient';

export default function HomePage({ user }) {
  const [restaurants, setRestaurants] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    loadRestaurants();
  }, []);

  const loadRestaurants = async () => {
    try {
      const data = await api.getAllRestaurants();
      setRestaurants(data);
    } catch (err) {
      console.error(err);
    }
  };

  return (
    <div>
      <div style={{ textAlign: 'center', marginBottom: '3rem' }}>
        <h1>Hungry, {user.name.split(' ')[0]}?</h1>
        <p>Discover the best food & drinks in your city</p>
      </div>

      {restaurants.length === 0 ? (
        <div className="glass glass-panel text-center">
          <p>No restaurants found. We need to add some to the database!</p>
        </div>
      ) : (
        <div style={{ 
          display: 'grid', 
          gridTemplateColumns: 'repeat(auto-fill, minmax(300px, 1fr))', 
          gap: '24px' 
        }}>
          {restaurants.map(rest => (
            <div 
              key={rest.id} 
              className="glass" 
              style={{ padding: '24px', cursor: 'pointer', transition: 'transform 0.2s' }}
              onClick={() => navigate(`/restaurant/${rest.id}`)}
              onMouseEnter={(e) => e.currentTarget.style.transform = 'translateY(-4px)'}
              onMouseLeave={(e) => e.currentTarget.style.transform = 'translateY(0)'}
            >
              <h3>{rest.name}</h3>
              <p style={{ margin: '8px 0', fontSize: '0.9rem' }}>📍 {rest.address}, {rest.city}</p>
              <div className="flex justify-between items-center mt-4">
                <span style={{ 
                  background: rest.open ? 'rgba(46, 213, 115, 0.2)' : 'rgba(255, 71, 87, 0.2)', 
                  color: rest.open ? 'var(--secondary-color)' : 'var(--primary-color)',
                  padding: '4px 12px', 
                  borderRadius: '12px',
                  fontSize: '0.8rem',
                  fontWeight: '600'
                }}>
                  {rest.open ? 'OPEN' : 'CLOSED'}
                </span>
                <span style={{ fontSize: '0.9rem', color: '#feca57' }}>★ {rest.rating || 'New'}</span>
              </div>
            </div>
          ))}
        </div>
      )}
    </div>
  );
}
