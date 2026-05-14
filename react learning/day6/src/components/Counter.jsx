import {useState} from "react"
import "./counter.css"
function Counter(){

        let [count, setCount] = useState(0)
        function handleClick() {
            setCount(count + 1)
            console.log(count)
        }
        return(
            <div className="counter">
           <div className="count-display">{count}</div>
            <button onClick={handleClick}>CLICK HERE</button>
            </div>
        )
       
        
}
export default Counter