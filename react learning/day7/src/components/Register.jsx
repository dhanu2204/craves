import { useState } from "react"
import "./Register.css"

const Register = () => {
    // State hooks to store user input
    const [name, setName] = useState("")
    const [email, setEmail] = useState("")
    const [password, setPassword] = useState("")
    const [phoneNumber, setPhoneNumber] = useState("")
    // Fixed role as "user"
    const role = "user"

    return (
        <div className="auth-main-wrapper">
            <div className="auth-container">
                <form className="auth-form" action="">
                    <h2>Sign Up</h2>

                    <div className="input-group">
                        <label htmlFor="name">Name</label>
                        <input 
                            type="text" 
                            id="name"
                            placeholder="Enter your name" 
                            value={name} 
                            onChange={(e) => setName(e.target.value)} 
                        />
                    </div>

                    <div className="input-group">
                        <label htmlFor="email">Email</label>
                        <input 
                            type="email" 
                            id="email"
                            placeholder="Enter your email" 
                            value={email} 
                            onChange={(e) => setEmail(e.target.value)} 
                        />
                    </div>

                    <div className="input-group">
                        <label htmlFor="password">Password</label>
                        <input 
                            type="password" 
                            id="password"
                            placeholder="Enter your password" 
                            value={password} 
                            onChange={(e) => setPassword(e.target.value)} 
                        />
                    </div>

                    <div className="input-group">
                        <label htmlFor="phoneNumber">Phone Number</label>
                        <input 
                            type="tel" 
                            id="phoneNumber"
                            placeholder="Enter your phone number" 
                            value={phoneNumber} 
                            onChange={(e) => setPhoneNumber(e.target.value)} 
                        />
                    </div>

                    <div className="btn-div">
                        <button type="submit" className="auth-btn">Sign Up</button>
                    </div>

                    <p className="auth-footer-text">
                        Already have an account? <a href="/login" className="auth-link">Sign In</a>
                    </p>
                </form>
            </div>

            <div className="debug-box">
                <h3>Live Preview</h3>
                <p><strong>Name:</strong> {name || "---"}</p>
                <p><strong>Email:</strong> {email || "---"}</p>
                <p><strong>Phone:</strong> {phoneNumber || "---"}</p>
                <p><strong>Role:</strong> {role}</p>
            </div>
        </div>
    )
}

export default Register
