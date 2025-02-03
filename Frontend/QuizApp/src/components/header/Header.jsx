// src/Header.js
import React from "react";
import "./Header.css";

const Header = () => {
  return (
    <header className="header">
      <div className="header-content">
        <h1 className="logo">Quiz Masters</h1>
        <nav className="nav-links">
          <a href="/createQuiz" className="nav-link special-link">
            Create Quiz
          </a>
          <a href="/" className="nav-link">
            Home
          </a>
          <a href="/about" className="nav-link">
            About
          </a>
          <a href="/login" className="nav-link">
            Login
          </a>
          <a href="/signup" className="nav-link">
            Signup
          </a>
        </nav>
      </div>
    </header>
  );
};

export default Header;
