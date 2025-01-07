import React from "react";
import "./LoginForm.css";
import { FaUser } from "react-icons/fa";
import { FaLock } from "react-icons/fa";

const LoginForm = () => {
  return (
    <div className="wrapper">
      <div className="header">Login</div>
      <form>
        <div className="input-group">
          <label>Email</label>
          <input type="email" placeholder="example@gmail.com" required />
        </div>
        <div className="input-group">
          <label>Passkey</label>
          <input type="Passkey" placeholder="Passkey" required />
        </div>
        <div className="input-group">
          <label>Password</label>
          <input type="password" placeholder="Password" required />
        </div>
        <div className="forgot-password">
          <a href="#">Forgot Password?</a>
        </div>
        <button type="submit">Login</button>
        <div className="register-link">
          <p className="create-account">Don't have an account? <a href="#">Create an account</a></p>
        </div>
      </form>
    </div>
  );
};

export default LoginForm;
