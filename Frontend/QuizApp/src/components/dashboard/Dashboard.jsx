import React, { useState, useEffect } from "react";
import QuizCard from "../quizCard/QuizCard";
import "bootstrap/dist/css/bootstrap.min.css";
import "./Dashboard.css";

const Dashboard = () => {
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [username, setUsername] = useState("");

  // Dummy quiz data
  const attemptedQuizzes = [
    { id: 1, title: "Math Quiz", time: "30 min", level: "Intermediate" },
    { id: 2, title: "Science Quiz", time: "20 min", level: "Beginner" },
  ];

  const unattemptedQuizzes = [
    { id: 3, title: "History Quiz", time: "15 min", level: "Advanced" },
    { id: 4, title: "Geography Quiz", time: "25 min", level: "Beginner" },
  ];

  useEffect(() => {
    // Simulate fetching user data
    const user = { isLoggedIn: true, name: "Raghav" };
    if (user.isLoggedIn) {
      setIsLoggedIn(true);
      setUsername(user.name);
    }
  }, []);

  return (
    <div className="dashboard container mt-5">
      <div className="greeting mb-4">
        <h2 className="text-primary">
          {isLoggedIn ? `Welcome, ${username}` : "Hi!"}
        </h2>
      </div>

      <div className="quiz-section mb-5">
        <h3 className="section-title">Attempted Quizzes</h3>
        <div className="quiz-cards row">
          {attemptedQuizzes.map((quiz) => (
            <div key={quiz.id} className="col-md-4 mb-4">
              <QuizCard quiz={quiz} />
            </div>
          ))}
        </div>
      </div>

      <div className="quiz-section">
        <h3 className="section-title">Unattempted Quizzes</h3>
        <div className="quiz-cards row">
          {unattemptedQuizzes.map((quiz) => (
            <div key={quiz.id} className="col-md-4 mb-4">
              <QuizCard quiz={quiz} />
            </div>
          ))}
        </div>
      </div>
    </div>
  );
};

export default Dashboard;
