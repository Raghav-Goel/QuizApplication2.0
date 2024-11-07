import { useRef, useState } from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import "./Registration.css";
import { User } from "../../entity/User";
import { addUser } from "../../handleData/ApiService";
function Registration() {
  const nameElement = useRef("");
  const emailElement = useRef("");
  const phoneElement = useRef("");
  const passwordElement = useRef("");
  const confirmPasswordElement = useRef("");
  const [flag, setFlag] = useState(null);
  const [passwordVisible, setPasswordVisible] = useState(false);
  const [confirmPasswordVisible, setConfirmPasswordVisible] =useState(false);
  const toggleConfirmPasswordVisibility = () => {
    setConfirmPasswordVisible(!confirmPasswordVisible);
  };
  const togglePasswordVisibility = () => {
    setPasswordVisible(!passwordVisible);
  };
  function submitForm(event) {
    event.preventDefault();
    User.name = nameElement.current.value;
    User.emailId = emailElement.current.value;
    User.password = passwordElement.current.value;
    User.phoneNumber = phoneElement.current.value;
    addUser(User)
      .then((response) => {
        console.log(response);
        if (response) {
          setFlag(false);
        } else {
          setFlag(true);
        }
      })
      .catch((error) => {
        console.log(error);
        setFlag(true);
      });
  }
  return (
    <div className="main">
      <div className="registration">
        <form onSubmit={submitForm}>
          <h1 className="registration-title">Registration</h1>
          <div className="form-group">
            <label className="form-label"> Name </label>
            <input type="text" className="form-control" ref={nameElement} />
          </div>
          <div className="form-group">
            <label className="form-label"> Email </label>
            <input type="email" className="form-control" ref={emailElement} />
          </div>
          <div className="form-group">
            <label className="form-label"> Phone Number </label>
            <input type="text" className="form-control" ref={phoneElement} />
          </div>
          <div className="form-group">
            <label className="form-label"> Password </label>
            <div className="password-container">
              <input
                type={passwordVisible ? "text" : "password"}
                className="password-input form-control"
                // placeholder="Enter your password"
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
          <div className="form-group">
            <label className="form-label">Confirm Password </label>
            <div className="password-container">
              <input
                type={confirmPasswordVisible ? "text" : "password"}
                className="password-input form-control"
                // placeholder="Enter your password"
                ref={confirmPasswordElement}
              />
              <span
                className="toggle-btn"
                onClick={toggleConfirmPasswordVisibility}
              >
                <i
                  className={
                    confirmPasswordVisible ? "fas fa-eye-slash" : "fas fa-eye"
                  }
                ></i>
              </span>
            </div>
          </div>
          <button className="btn btn-primary w-100 py-2" type="submit">
            Sign in
          </button>
        </form>
        <div id="success" className="submitMessage">
          {flag && (
            <p className="text-success">
              The user has been created successfully
            </p>
          )}
        </div>
        <div id="fail" className="submitMessage">
          {flag == false && (
            <p className="text-danger">Please provide the correct details.</p>
          )}
        </div>
      </div>
    </div>
  );
}
export default Registration;
