import { useRef, useState } from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import "./Login.css";
import { getUserByEmailId } from "../../handleData/ApiService";
import { useDispatch, useSelector } from "react-redux";
function login() {
  const isLogged=useDispatch();
  const emailElement = useRef("");
  const passwordElement = useRef("");
  const [flag, setFlag] = useState(false);
  const [passwordVisible, setPasswordVisible] = useState(false);

  const togglePasswordVisibility = () => {
    setPasswordVisible(!passwordVisible);
  }
  function submitForm(event) {
    const name = emailElement.current.value;
    const password = passwordElement.current.value;
    event.preventDefault();
    console.log(name + "  " + password);
    
    getUserByEmailId(name)
      .then((response) => {
        console.log(response.success);
        if (response.success) {
          if(response.data.password==password){
            setFlag(false);
            isLogged({type:"true"});
          }else{
            setFlag(true);
          }
          
        } else {
          setFlag(true);
        }
      })
      .catch((error) => {
        console.log("Error:",error);
        setFlag(true);
      });
  }
  const isLoggedww = useSelector((quizStore) => quizStore.isLoggedIn);
  return (
    <div className="main">
      <div className="login-container">
        <h2 className="login-title">Login</h2>
        <form onSubmit={submitForm}>
          <div className="form-group">
            <label className="form-label"> Email </label>
            <input
              type="email"
              className="form-control"
              ref={emailElement}
              placeholder="Enter email"
            />
          </div>
          <div className="form-group">
            <label className="form-label">Password</label>
            <div className="password-container">
              <input
                type={passwordVisible ? "text" : "password"}
                className="password-input form-control"
                placeholder="Enter your password"
                ref={passwordElement}
              />
              <span className="toggle-btn" onClick={togglePasswordVisibility}>
                <i
                  className={
                    passwordVisible ? "fas fa-eye-slash" : "fas fa-eye"
                  }
                ></i>
              </span>
            </div>
          </div>
          <button className="btn btn-primary w-100 py-2" type="submit">
            Login
          </button>
          {flag && (
            <p className="small mt-2 text-danger">
              Please provide correct emailId or Password
            </p>
          )}
          <a href="#" className="forgot-password">
            Forgot your password?
          </a>
        </form>
      </div>
      <h1>{isLoggedww}</h1>
    </div>
  );
}
export default login;
