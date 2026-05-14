import "./pfp.css";
export default function Card(props) {
    return (
        
            <div className="card">
                <img src="/src/assets/pfp.jpg" alt="" />
                <h1>{props.name}</h1>
                <h3>Full Stack Developer</h3>
                <p>I am a full stack developer. I am a quick learner and can adapt to new technologies quickly. I am a team player and can work well in a team.</p>
                <div className="links">
                    <a href="">Instagram</a>
                    <a href="">LinkedIn</a>
                    <a href="">Facebook</a>
                </div>
            </div>
       
    )
}