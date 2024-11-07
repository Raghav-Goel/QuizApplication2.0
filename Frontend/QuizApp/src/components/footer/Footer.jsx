import React from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import "./Footer.css"; // Custom styling

const Footer = () => {
  return (
    <footer className="footer mt-auto py-3">
      <div className="container text-center">
        <p className="footer-text">
          © {new Date().getFullYear()} Quiz Masters | All Rights Reserved
        </p>
        <p className="footer-text">
          <a href="/privacy-policy" className="footer-link">
            Privacy Policy
          </a>{" "}
          |
          <a href="/terms-of-service" className="footer-link">
            {" "}
            Terms of Service
          </a>{" "}
          |
          <a href="/contact-us" className="footer-link">
            {" "}
            Contact Us
          </a>
        </p>
      </div>
    </footer>
  );
};

export default Footer;
