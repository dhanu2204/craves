import { useEffect, useState } from 'react';
import { X, Minus, Plus, CreditCard } from 'lucide-react';
import { api } from '../api/apiClient';

export default function CartSidebar({ isOpen, onClose, userId }) {
  const [cart, setCart] = useState(null);

  useEffect(() => {
    if (isOpen) {
      loadCart();
    }
  }, [isOpen]);

  const loadCart = async () => {
    try {
      const cartData = await api.getCart(userId);
      setCart(cartData);
    } catch (err) {
      console.error(err);
    }
  };

  const updateQty = async (itemId, currentQty, delta) => {
    const newQty = currentQty + delta;
    try {
      if (newQty <= 0) {
        await api.removeFromCart(itemId);
      } else {
        await api.updateCartItem(itemId, newQty);
      }
      loadCart(); // Reload cart
    } catch (err) {
      console.error(err);
    }
  };

  const handleCheckout = async () => {
    if (!cart || !cart.items || cart.items.length === 0) return;
    try {
      await api.placeOrder(userId, cart.items[0].foodItem.restaurant.id, "Home Address", "CARD");
      alert("Order placed successfully! 🎉");
      onClose();
    } catch (err) {
      alert("Failed to place order: " + err.message);
    }
  };

  if (!isOpen) return null;

  return (
    <>
      <div 
        style={{ position: 'fixed', inset: 0, background: 'rgba(0,0,0,0.5)', zIndex: 100 }} 
        onClick={onClose}
      />
      <div 
        className="glass"
        style={{
          position: 'fixed', top: 0, right: 0, bottom: 0, width: '100%', maxWidth: '400px',
          zIndex: 101, borderRadius: '24px 0 0 24px', borderRight: 'none',
          display: 'flex', flexDirection: 'column', padding: '24px',
          animation: 'slideIn 0.3s ease forwards'
        }}
      >
        <div className="flex justify-between items-center" style={{ borderBottom: '1px solid var(--card-border)', paddingBottom: '16px', marginBottom: '16px' }}>
          <h2>Your Cart</h2>
          <button onClick={onClose} className="btn-secondary" style={{ background: 'transparent', border: 'none', cursor: 'pointer', color: 'var(--text-main)' }}>
            <X size={24} />
          </button>
        </div>

        <div style={{ flex: 1, overflowY: 'auto' }}>
          {!cart || !cart.items || cart.items.length === 0 ? (
            <p className="text-center mt-8">Your cart is empty.</p>
          ) : (
            <div className="flex-col gap-4">
              {cart.items.map(item => (
                <div key={item.id} style={{ background: 'rgba(255,255,255,0.02)', padding: '12px', borderRadius: '12px' }}>
                  <div className="flex justify-between items-center">
                    <span style={{ fontWeight: '500' }}>{item.foodItem.name}</span>
                    <span>${item.price.toFixed(2)}</span>
                  </div>
                  <div className="flex items-center gap-2 mt-2">
                    <button onClick={() => updateQty(item.id, item.quantity, -1)} className="btn-secondary" style={{ padding: '4px', borderRadius: '4px' }}><Minus size={14}/></button>
                    <span style={{ width: '20px', textAlign: 'center' }}>{item.quantity}</span>
                    <button onClick={() => updateQty(item.id, item.quantity, 1)} className="btn-secondary" style={{ padding: '4px', borderRadius: '4px' }}><Plus size={14}/></button>
                  </div>
                </div>
              ))}
            </div>
          )}
        </div>

        {cart && cart.items && cart.items.length > 0 && (
          <div style={{ borderTop: '1px solid var(--card-border)', paddingTop: '16px', marginTop: '16px' }}>
            <div className="flex justify-between items-center mb-4">
              <span style={{ fontSize: '1.2rem', color: 'var(--text-muted)' }}>Total:</span>
              <span style={{ fontSize: '1.5rem', fontWeight: 'bold' }}>${cart.totalPrice.toFixed(2)}</span>
            </div>
            <button className="btn" style={{ width: '100%' }} onClick={handleCheckout}>
              <CreditCard size={20} /> Checkout
            </button>
          </div>
        )}
      </div>

      <style>{`
        @keyframes slideIn {
          from { transform: translateX(100%); }
          to { transform: translateX(0); }
        }
      `}</style>
    </>
  );
}
