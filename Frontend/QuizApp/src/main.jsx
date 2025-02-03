import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import App from './App.jsx'
import Login from './components/login/Login.jsx'
import Registration from './components/registration/Registration.jsx'
import Dashboard from './components/dashboard/Dashboard.jsx'
import './index.css'
import { createBrowserRouter, RouterProvider } from 'react-router-dom'
import { Provider } from 'react-redux'
import quizStore from './components/store/index.js'
import About from './components/about/About.jsx'
import CreateQuiz from './components/createQuiz/CreateQuiz.jsx'
const router = createBrowserRouter([
  {
    path: "/",
    element: <App />,
    children: [
      { path: "/", element: <Dashboard /> },
      { path: "/login", element: <Login /> },
      { path: "/signup", element: <Registration /> },
      { path: "/about", element: <About /> },
      { path: "/createQuiz", element: <CreateQuiz /> },
    ],
  },
]);
createRoot(document.getElementById("root")).render(
  <StrictMode>
    <Provider store={quizStore}>
      <RouterProvider router={router} />
    </Provider>
  </StrictMode>
);
