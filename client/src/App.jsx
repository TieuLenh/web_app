import { useState } from 'react'

import { BrowserRouter as Router, Route, Routes } from 'react-router-dom'

import LoginPage from './pages/loginPage'
import RegisterPage from './pages/registerPage'
import ProfilePage from './pages/profilePage'
import MainLayout from './layouts/mainLayout'

import GameList from './contents/gameList'
import GameDetail from './contents/gameDetail'
import GameManage from './contents/gameManage'

import ProtectedRoute from './protectedRoute'



function App() {
    return (
        <Router>
            <Routes>
                <Route path="/" element={<MainLayout content={<p>this is home page</p>} />} />
                <Route path="/games" element={<MainLayout content={<GameList />} />} />
                <Route path="/login" element={<LoginPage />} />
                <Route path="/register" element={<RegisterPage />} />
                <Route path="/profile" element={<ProfilePage />} />
                <Route path="/games/:id" element={<MainLayout content={<GameDetail />} />} />
                <Route
                    path="/manage/games"
                    element={
                        <ProtectedRoute role="ADMIN">
                            <MainLayout content={<GameManage />} />
                        </ProtectedRoute>
                    }
                />
            </Routes>
        </Router>
    )
}

export default App
