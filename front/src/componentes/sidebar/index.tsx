import { Link } from "react-router-dom";

function Sidebar() {
  return (
    <div className="bg-dark text-white vh-100 p-3" style={{ width: "250px" }}>
      <div className="text-center mb-4">
        <img
          src="data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iODAiIGhlaWdodD0iODAiIHZpZXdCb3g9IjAgMCA1MCA1MCIgZmlsbD0ibm9uZSIgeG1sbnM9Imh0dHA6Ly93d3cudzMu
b3JnLzIwMDAvc3ZnIj4KPHJlY3Qgd2lkdGg9IjUwIiBoZWlnaHQ9IjUwIiByeD0iMTIiIGZpbGw9IiMwMDdCQzgiLz4KPHRleHQgeD0iMjUi
IHk9IjMwIiBmb250LXNpemU9IjE0IiBmb250LWZhbWlseT0iQXJpYWwiIHRleHQtYW5jaG9yPSJtaWRkbGUiIGZpbGw9IndoaXRlIj5TPC90
ZXh0Pgo8L3N2Zz4="
          alt="logo"
          className="img-fluid"
          style={{ maxHeight: "80px" }}
        />
      </div>
      <ul>
        <li>
          <a href="/" className="nav-link text-white">Home</a>
        </li>
        <li>
          <a
            href="#submenucadastro"
            className="nav-link text-white"
            data-bs-toggle="collapse"
            role="button"
            aria-expanded="false"
            aria-controls="submenucadastro"
          >
            Cadastro
          </a>
          <ul className="collapse ps-3" id="submenucadastro">
            <li>
             <Link to="/usuario" 
             className="nav-link text-white">Usuário</Link>
            </li>
            <li>
            <Link to="/carrinho" 
            className="nav-link text-white">Carrinho</Link>
              
            </li>
          </ul>
        </li>
      </ul>
    </div>
  );
}

export default Sidebar;
