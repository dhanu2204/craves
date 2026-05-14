import { Link } from 'react-router-dom';
import { ShoppingBag, LogOut, User } from 'lucide-react';
import { useState } from 'react';
import CartSidebar from './CartSidebar';

export default function Navbar({ user, onLogout }) {
  const [isCartOpen, setIsCartOpen] = useState(false);

  return (
    <>
      <nav style={{
        position: 'fixed', top: 0, left: 0, right: 0, zIndex: 50,
        background: 'rgba(11, 15, 25, 0.7)',
        backdropFilter: 'blur(16px)',
        borderBottom: '1px solid var(--card-border)',
        padding: '16px 0'
      }}>
        <div className="container flex justify-between items-center">
          <Link to="/" style={{ textDecoration: 'none' }}>
            <h2 style={{ color: 'var(--primary-color)', margin: 0, display: 'flex', alignItems: 'center', gap: '8px' }}>
              <span style={{ fontSize: '1.5rem' }}>🍕</span> FoodDash
            </h2>
          </Link>

          {user && (
            <div className="flex items-center gap-4">
              <div className="flex items-center gap-2" style={{ color: 'var(--text-muted)' }}>
                <User size={18} />
                <span>Hi, {user.name || 'User'}</span>
              </div>
              
              <button 
                onClick={() => setIsCartOpen(true)}
                className="btn btn-secondary" 
                style={{ padding: '8px', borderRadius: '50%' }}
              >
                <ShoppingBag size={20} color="var(--text-main)" />
              </button>
              
              <button 
                onClick={onLogout}
                className="btn btn-secondary" 
                style={{ padding: '8px', borderRadius: '50%' }}
              >
                <LogOut size={20} color="var(--primary-color)" />
              </button>
            </div>
          )}
        </div>
      </nav>

      {user && (
        <CartSidebar 
          isOpen={isCartOpen} 
          onClose={() => setIsCartOpen(false)} 
          userId={user.id} 
        />
      )}
    </>
  );
}
