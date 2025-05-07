import { BrowserRouter, Routes, Route } from 'react-router-dom';
import HomePage from './pages/HomePage';
import Dashboard from './pages/DashboardPage.jsx';
import OAuth2RedirectHandler from './components/OAuth2RedirectHandler';
import { AuthProvider } from './context/AuthContext';

function App() {
    return (
        <AuthProvider>
            <BrowserRouter>
                <Routes>
                    <Route path="/" element={<HomePage />} />
                    <Route path="/dashboard" element={<Dashboard />} />
                    <Route path="/oauth2/redirect" element={<OAuth2RedirectHandler />} />
                </Routes>
            </BrowserRouter>
        </AuthProvider>
    );
}

export default App;
