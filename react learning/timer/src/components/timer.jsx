import React, { useState,useEffect } from 'react'
import "./timer.css"

const timer = () => {
    const [second, setSecond] = useState(() => {
        const saved = localStorage.getItem("timer");
        return saved ? JSON.parse(saved) : 0;
    });
    const [running, setRunning] = useState(false);

    useEffect(() => {
        let id = null;
        if (running) {
            id = setInterval(() => {
                setSecond(prev => prev + 1);
            }, 1000);
        }
        return () => clearInterval(id);
    }, [running]);

    useEffect(() => {
        localStorage.setItem("timer", JSON.stringify(second));
    }, [second]);

  return (
    <div className='box'>
        <h2>Timer</h2>
        <p>{second}</p>
        <button onClick={()=>setRunning(true)}>Start</button>
        <button onClick={()=>setRunning(false)}>Stop</button>
        <button onClick={()=>setSecond(0)}>Reset</button>
    </div>
  )
}

export default timer