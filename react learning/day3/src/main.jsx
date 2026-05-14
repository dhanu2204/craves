import { createRoot } from "react-dom/client"
import './index.css';

const Profile = () => {
  return (
    <div className="card">
      <div className="pfp">
        <img src="./src/assets/passport pic.jpg" alt="passport" />
        <h1>Dhanush</h1>
        <h3>Full Stack Developer</h3 >
      </div>
      <p>
        I am a full stack developer with 2 years of experience.
        I am a quick learner and can adapt to new technologies quickly.
        I am a team player and can work well in a team.
      </p>
      <nav>
        <ul type="none">
          <li>Instagram</li>
          <li>Linkdein</li>
          <li>GitHub</li>
        </ul>
      </nav>
    </div>
  )
}

createRoot(document.getElementById("root")).render(<Profile />)
