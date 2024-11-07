import React from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import "./About.css";

const About = () => {
  return (
    <div className="about-page container mt-5">
      <div className="row justify-content-center">
        <div className="col-md-8">
          <h2 className="about-title text-center text-primary mb-4">
            About Quiz Masters
          </h2>
          <p className="about-text">
            Welcome to Quiz Masters! We are passionate about creating engaging
            and educational quizzes that challenge users while providing fun
            learning experiences. Whether you're a student, a professional, or
            simply a curious learner, we have quizzes designed for every level.
          </p>
          <p className="about-text">
            Our mission is to foster a love for learning by offering quizzes
            that not only test knowledge but also expand it. With a wide range
            of topics and difficulty levels, Quiz Masters caters to a global
            audience with diverse interests.
          </p>
          <p className="about-text">
            Join us on this journey of learning and self-improvement. Let's make
            learning fun, interactive, and rewarding!
          </p>
        </div>
      </div>
    </div>
  );
};

export default About;
