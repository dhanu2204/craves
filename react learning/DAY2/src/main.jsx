import { createRoot } from "react-dom/client"


const root = createRoot(document.getElementById("root"))
let a = "dhanu"
root.render(
  <div className="card">
    <nav>
      <img src="./src/assets/images.png" alt="" />
      <h1>Rules of JSX</h1>
    </nav>
    <ul className="lists">
      <li>A root can have only one parent tag</li>
      <li>Every tags must be closed</li>
      <li>Use flower brackets for javascript expression</li>
      <li>use camelcase for attributes</li>
      <li>cannot use class word so use classname</li>
    </ul>
  </div>
)