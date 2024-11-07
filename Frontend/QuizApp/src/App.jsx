import { useState } from 'react'
import "./App.css";
import "bootstrap/dist/css/bootstrap.css";
import Header from './components/header/Header';
import { Outlet } from 'react-router-dom';
import Footer from './components/footer/Footer';

function App() {
  const [count, setCount] = useState(0)

  return (
    <>
      <Header />
      <Outlet/>
      <Footer/>
    </>
  );
}

export default App
