import { useState, useEffect } from 'react';
import { Routes, Route, Navigate } from 'react-router-dom';
import Navbar from './components/Navbar';
import HomePage from './pages/HomePage';
import AuthPage from './pages/AuthPage';
import RestaurantPage from './pages/RestaurantPage';

function App() {
  const [user, setUser] = useState(null);

  // Load user from local storage on app start
  useEffect(() => {
    const savedUser = localStorage.getItem('user');
    if (savedUser) {
      setUser(JSON.parse(savedUser));
    }
  }, []);

  const handleLogin = (userData) => {
    setUser(userData);
    localStorage.setItem('user', JSON.stringify(userData));
  };

  const handleLogout = () => {
    setUser(null);
    localStorage.removeItem('user');
  };

  return (
    <>
      <Navbar user={user} onLogout={handleLogout} />
      <main className="container animate-fade-in" style={{ paddingTop: '100px', paddingBottom: '40px' }}>
        <Routes>
          <Route path="/" element={user ? <HomePage user={user} /> : <Navigate to="/auth" />} />
          <Route path="/auth" element={!user ? <AuthPage onLogin={handleLogin} /> : <Navigate to="/" />} />
          <Route path="/restaurant/:id" element={user ? <RestaurantPage user={user} /> : <Navigate to="/auth" />} />
        </Routes>
      </main>
    </>
  );
}

export default App;
