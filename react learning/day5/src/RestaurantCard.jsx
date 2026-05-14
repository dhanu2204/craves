import "./res.css";


function RestaurantCard({name , cuisine , rating , image}){
    return(
        <div className="Card">
            <img src="https://img.magnific.com/free-photo/restaurant-hall-with-red-brick-walls-wooden-tables-pipes-ceiling_140725-8504.jpg?semt=ais_hybrid&w=740&q=80" alt="" />
            <h2>{name}</h2>
            <div className="desc">
                <p>{cuisine}</p>
                <p className={rating >= 4 ? "rating-green" : "rating-yellow"}>
                    {rating} ★
                </p> 
            </div>
        </div>
    )
}

export default RestaurantCard