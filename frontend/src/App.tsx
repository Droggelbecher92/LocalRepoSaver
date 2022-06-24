import React from 'react';
import './App.css';
import {BrowserRouter, Route, Routes} from "react-router-dom";
import HomePage from "./pages/HomePage";
import FoundUserReposPage from "./pages/FoundUserReposPage";
import LoginPage from "./pages/LoginPage";

export default function App() {
  return (
      <BrowserRouter>
        <Routes>
            <Route path={'/'} element={<LoginPage/>}/>
            <Route path={'/home/:username'} element={<HomePage/>}/>
            <Route path={'/repos/:username'} element={<FoundUserReposPage/>}/>
        </Routes>
      </BrowserRouter>
  );
}