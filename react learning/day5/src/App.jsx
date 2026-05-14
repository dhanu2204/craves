import RestaurantCard from "./RestaurantCard";
import restaurants from "./demo";
import "./App.css";

function App(){
  return(
    <div className="container">
      {restaurants.map(restaurants =>(
        <RestaurantCard
          key = {restaurants.id}
          name = {restaurants.name}
          cuisine = {restaurants.cuisine}
          rating = {restaurants.rating}
          image = {restaurants.image}
          />
        ))}
    </div>
  )
}

export default App