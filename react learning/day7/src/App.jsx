import { useState, useEffect } from "react";
import "./App.css";
import restaurants from "./components/demo";
import RestaurantCard from "./components/RestaurantCard";
import Header from "./components/Header";
import "./components/HeadFoot.css";

function App() {
  const [search, setSearch] = useState("");
  const [vegOnly, setVegOnly] = useState(false);
  const [isDark, setIsDark] = useState(false);

  // This effect updates the 'data-theme' attribute on the <html> element
  // whenever the 'isDark' state changes.
  useEffect(() => {
    document.documentElement.setAttribute("data-theme", isDark ? "dark" : "light");
  }, [isDark]);

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


export default App;