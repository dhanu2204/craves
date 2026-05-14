import { useState, useEffect } from "react";
import "./RestaurantPage.css";

// --- DATA ---
// This is the data for our restaurants. We put it here so the component is self-contained.
const restaurants = [
    {
        id: 1,
        name: "Empire",
        cuisine: "North Indian",
        rating: 4.4,
        type: "Non-Veg",
        image: "https://images.unsplash.com/photo-1517248135467-4c7edcad34c4?auto=format&fit=crop&q=80&w=600"
    },
    {
        id: 2,
        name: "Mylari",
        cuisine: "South Indian",
        rating: 4.8,
        type: "Veg",
        image: "https://images.unsplash.com/photo-1610192244261-3f33de3f55e4?auto=format&fit=crop&q=80&w=600"
    },
    {
        id: 3,
        name: "Papa Johns",
        cuisine: "Pizza",
        rating: 4.0,
        type: "Non-Veg",
        image: "https://images.unsplash.com/photo-1513104890138-7c749659a591?auto=format&fit=crop&q=80&w=600"
    },
    {
        id: 4,
        name: "Anapurna",
        cuisine: "South Indian",
        rating: 3.8,
        type: "Veg",
        image: "https://images.unsplash.com/photo-1589302168068-964664d93dc0?auto=format&fit=crop&q=80&w=600"
    },
    {
        id: 5,
        name: "GTR (Gayathri Tiffin Room)",
        cuisine: "South Indian",
        rating: 4.7,
        type: "Veg",
        image: "https://images.unsplash.com/photo-1626074353765-517a681e40be?auto=format&fit=crop&q=80&w=600"
    },
    {
        id: 6,
        name: "Hotel Hanumanthu",
        cuisine: "Biryani/Nati Style",
        rating: 4.6,
        type: "Non-Veg",
        image: "https://images.unsplash.com/photo-1563379091339-03b21ef4a4f8?auto=format&fit=crop&q=80&w=600"
    },
    {
        id: 7,
        name: "Mahesh Prasad",
        cuisine: "South Indian/Veg",
        rating: 4.5,
        type: "Veg",
        image: "https://images.unsplash.com/photo-1512621776951-a57141f2eefd?auto=format&fit=crop&q=80&w=600"
    },
    {
        id: 8,
        name: "Tiger Trail",
        cuisine: "Mughlai/Indian",
        rating: 4.3,
        type: "Non-Veg",
        image: "https://images.unsplash.com/photo-1544124499-58912cbddaad?auto=format&fit=crop&q=80&w=600"
    },
    {
        id: 9,
        name: "Oyster Bay",
        cuisine: "Multi-Cuisine",
        rating: 4.2,
        type: "Non-Veg",
        image: "https://images.unsplash.com/photo-1552566626-52f8b828add9?auto=format&fit=crop&q=80&w=600"
    },
    {
        id: 10,
        name: "Indra Bhavan",
        cuisine: "South Indian",
        rating: 4.4,
        type: "Veg",
        image: "https://images.unsplash.com/photo-1567337710282-00832b415979?auto=format&fit=crop&q=80&w=600"
    },
    {
        id: 11,
        name: "Nalpak",
        cuisine: "South Indian/Veg",
        rating: 4.3,
        type: "Veg",
        image: "https://images.unsplash.com/photo-1601050633647-81a3175c20d1?auto=format&fit=crop&q=80&w=600"
    },
    {
        id: 12,
        name: "Depth N Height",
        cuisine: "Fast Food/Cafe",
        rating: 4.1,
        type: "Veg",
        image: "https://images.unsplash.com/photo-1504674900247-0877df9cc836?auto=format&fit=crop&q=80&w=600"
    },
    {
        id: 13,
        name: "RRR Restaurant",
        cuisine: "Andhra Style/Biryani",
        rating: 4.7,
        type: "Non-Veg",
        image: "https://images.unsplash.com/photo-1589187151053-5ec8818e661b?auto=format&fit=crop&q=80&w=600"
    },
    {
        id: 15,
        name: "The Old House",
        cuisine: "Italian/Cafe",
        rating: 4.6,
        type: "Veg",
        image: "https://images.unsplash.com/photo-1551183053-bf91a1d81141?auto=format&fit=crop&q=80&w=600"
    },
    {
        id: 16,
        name: "Parklane Hotel",
        cuisine: "Indian/Chinese",
        rating: 4.0,
        type: "Non-Veg",
        image: "https://images.unsplash.com/photo-1514362545857-3bc16c4c7d1b?auto=format&fit=crop&q=80&w=600"
    },
    {
        id: 17,
        name: "By The Way",
        cuisine: "Continental/Indian",
        rating: 4.2,
        type: "Non-Veg",
        image: "https://images.unsplash.com/photo-1555396273-367ea4eb4db5?auto=format&fit=crop&q=80&w=600"
    },
    {
        id: 18,
        name: "Tegu Mess",
        cuisine: "South Indian/Nati Style",
        rating: 4.5,
        type: "Non-Veg",
        image: "https://images.unsplash.com/photo-1604382354936-07c5d9983bd3?auto=format&fit=crop&q=80&w=600"
    },
    {
        id: 19,
        name: "Coastal Terrace",
        cuisine: "Seafood/Mangalorean",
        rating: 4.3,
        type: "Non-Veg",
        image: "https://images.unsplash.com/photo-1519708227418-c8fd9a32b7a2?auto=format&fit=crop&q=80&w=600"
    }
];

// --- COMPONENTS ---

// This is the Header component. It's now an internal function.
function Header({ search, setSearch, vegOnly, setVegOnly, isDark, setIsDark }) {
    return (
        <header className="header-container">
            <div className="top-bar">
                <a href="/" className="logo">Craves</a>
                <nav className="nav-links">
                    <button className="theme-toggle" onClick={() => setIsDark(!isDark)}>
                        {isDark ? "☀️" : "🌙"}
                    </button>
                    <button className="nav-btn">Sign In</button>
                    <button className="nav-btn">Sign Up</button>
                </nav>
            </div>
            <div className="search-bar-container">
                <div className="search-input-wrapper">
                    <input 
                        className="search-input"
                        placeholder="Search Restaurant Name/Dish"
                        value={search}
                        onChange={(e) => setSearch(e.target.value)}
                    />
                </div>

                <div className="filter-group">
                    <div className="toggle-div">
                        <label className="switch">
                            <input 
                                type="checkbox"
                                checked={vegOnly}
                                onChange={(e) => setVegOnly(e.target.checked)}
                            />
                            <span className="slider round"></span>
                        </label>
                        <span className="veg-label">Veg Only</span>
                    </div>
                    <button className="cart-btn">🛒</button>
                </div>
            </div>
        </header>
    );
}

// This is the RestaurantCard component. Also an internal function.
function RestaurantCard({ name, cuisine, rating, image }) {
    return (
        <div className="Card">
            <img src={image} alt={name} />
            <h2>{name}</h2>
            <div className="desc">
                <p>{cuisine}</p>
                <p className={rating >= 4 ? "rating-green" : "rating-yellow"}>
                    {rating} ★
                </p> 
            </div>
        </div>
    );
}

// --- MAIN PAGE COMPONENT ---
// This is the main component that brings everything together.
function RestaurantPage() {
  const [search, setSearch] = useState("");
  const [vegOnly, setVegOnly] = useState(false);
  const [isDark, setIsDark] = useState(false);

  // This updates the website theme (Light/Dark mode)
  useEffect(() => {
    document.documentElement.setAttribute("data-theme", isDark ? "dark" : "light");
  }, [isDark]);

  // This logic filters the restaurants based on search and Veg toggle
  const filteredRestaurants = restaurants.filter((res) => {
    const matchesSearch = res.name.toLowerCase().includes(search.toLowerCase()) || 
                          res.cuisine.toLowerCase().includes(search.toLowerCase());
    const matchesVeg = vegOnly ? res.type === "Veg" : true;
    return matchesSearch && matchesVeg;
  });

  return (
    <div className="app">
      <Header 
        search={search} 
        setSearch={setSearch} 
        vegOnly={vegOnly} 
        setVegOnly={setVegOnly} 
        isDark={isDark}
        setIsDark={setIsDark}
      />
      
      <div className="container">
        {filteredRestaurants.length > 0 ? (
          filteredRestaurants.map((res) => (
            <RestaurantCard
              key={res.id}
              name={res.name}
              cuisine={res.cuisine}
              rating={res.rating}
              image={res.image}
            />
          ))
        ) : (
          <h2 style={{ textAlign: "center", width: "100%" }}>No Restaurants Found</h2>
        )}
      </div>
    </div>
  );
}

export default RestaurantPage;
