import { Link } from "react-router-dom";


function Header(){
    return(
        <header className="bg-dark">
            <nav className="container d-flex justify-content-start gap-3 py-3">
                <Link to="/" className="text-white text-decoration-none">Home</Link>
                <Link to="/usuarios" className="text-white text-decoration-none">Usuarios</Link>
            </nav>
        </header>
    );
}

export default Header;