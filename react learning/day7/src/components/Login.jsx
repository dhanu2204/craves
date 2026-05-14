import { useState } from "react"
import "./Login.css"

const Login = () => {
  const [email, setEmail] = useState("")
  const [password, setPassword] = useState("")

  return (
    <div className="auth-main-wrapper">
      <div className="auth-container">
        <form className="auth-form" action="">
          <h2>Sign In</h2>

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

          <div className="btn-div">
            <button type="submit" className="auth-btn">Sign In</button>
          </div>

          <p className="auth-footer-text">
            Don't have an account? <a href="/register" className="auth-link">Sign Up</a>
          </p>
        </form>
      </div>

      <div className="debug-box">
        <h3>Live Preview</h3>
        <p><strong>Email:</strong> {email || "---"}</p>
        <p><strong>Password:</strong> {password ? "********" : "---"}</p>
      </div>
    </div>
  )
}

export default Login