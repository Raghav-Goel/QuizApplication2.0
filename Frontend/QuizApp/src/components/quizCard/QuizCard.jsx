import React from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import "./QuizCard.css"; // Custom styling

const QuizCard = ({ quiz }) => {
  return (
    <div className="quiz-card card shadow-sm">
      <div className="card-body">
        <h5 className="card-title text-dark">{quiz.title}</h5>
        <p className="card-text">
          <strong>Time:</strong> {quiz.time} <br />
          <strong>Level:</strong> {quiz.level}
        </p>
        <button className="btn btn-primary">Start Quiz</button>
      </div>
    </div>
  );
};

export default QuizCard;
