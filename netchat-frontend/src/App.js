import { Route, Routes } from "react-router-dom";
import HomePage from "./Components/HomePage"
import Status from "./Components/Status/Status"
import StatusViewer from "./Components/Status/StatusViewer";
import Signin from "./Components/Register/Signin";
import SignUp from "./Components/Register/SignUp";

function App() {
  return (
    <div>
      <Routes>
        <Route path="/" element={<HomePage />}></Route>
        <Route path="/status" element={<Status />}></Route>
        <Route path="/status/:userId" element={<StatusViewer />}></Route>
        <Route path="/signin" element={<Signin />}></Route>
        <Route path="/signup" element={<SignUp />}></Route>
      
      </Routes>
    </div>
  );
}

export default App;
