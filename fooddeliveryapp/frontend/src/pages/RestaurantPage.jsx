import { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { Plus } from 'lucide-react';
import { api } from '../api/apiClient';

export default function RestaurantPage({ user }) {
  const { id } = useParams();
  const navigate = useNavigate();
  const [restaurant, setRestaurant] = useState(null);
  const [foodItems, setFoodItems] = useState([]);

  useEffect(() => {
    loadData();
  }, [id]);

  const loadData = async () => {
    try {
      const restData = await api.getAllRestaurants();
      setRestaurant(restData.find(r => r.id === parseInt(id)));
      const items = await api.getFoodItemsByRestaurant(id);
      setFoodItems(items);
    } catch (err) {
      console.error(err);
    }
  };

  const handleAddToCart = async (foodId) => {
    try {
      await api.addToCart(user.id, foodId, 1);
      // alert('Added to cart!');
    } catch (err) {
      console.error(err);
    }
  };

  if (!restaurant) return <div>Loading...</div>;

  return (
    <div>
      <button onClick={() => navigate(-1)} className="btn btn-secondary" style={{ marginBottom: '24px' }}>
        ← Back
      </button>

      <div className="glass glass-panel" style={{ marginBottom: '32px' }}>
        <h1>{restaurant.name}</h1>
        <p>{restaurant.address}</p>
      </div>

      <h2 style={{ marginBottom: '24px' }}>Menu</h2>

      {foodItems.length === 0 ? (
        <p>No food items available.</p>
      ) : (
        <div style={{ display: 'flex', flexDirection: 'column', gap: '16px' }}>
          {foodItems.map(item => (
            <div key={item.id} className="glass flex justify-between items-center" style={{ padding: '16px 24px' }}>
              <div>
                <h3 style={{ margin: 0, fontSize: '1.2rem' }}>{item.name}</h3>
                <p style={{ margin: '4px 0 0 0', fontSize: '0.9rem' }}>{item.description}</p>
              </div>
              <div className="flex items-center gap-4">
                <span style={{ fontSize: '1.2rem', fontWeight: '600' }}>${item.price.toFixed(2)}</span>
                <button 
                  onClick={() => handleAddToCart(item.id)}
                  className="btn" 
                  style={{ padding: '8px', borderRadius: '8px' }}
                >
                  <Plus size={20} />
                </button>
              </div>
            </div>
          ))}
        </div>
      )}
    </div>
  );
}
