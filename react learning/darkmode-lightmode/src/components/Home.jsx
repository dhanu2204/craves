import { useState } from 'react'
import './Home.css'
function Home() {
    const [toggle, setToggle] = useState(false)
    return (
        <div className={toggle ? "dark" : "light"}>
            <label className="bg">
                <input onChange={() => setToggle(!toggle)}
                    type="checkbox"
                    className='slider' />
            </label>
        </div>
    )
}
export default Home