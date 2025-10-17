import { Route, Routes } from "react-router-dom";
import Home from "./paginas/home";
import LayoutAdmin from "./componentes/LayoutAdmin";
import Login from "./paginas/login";
import Usuario from "./paginas/usuario";
import LayoutLogin from "./componentes/LayoutLogin";
import Cadastrese from "./paginas/cadastrese";

function AppRoutes() {
    return (
        <Routes>
            <Route path="/auth" element={<LayoutLogin />}>
                <Route path="login" element={<Login />} />
                <Route path="cadastro" element={<Cadastrese />} />
            </Route>
            <Route element={<LayoutAdmin />}>
                <Route path="/" element={<Home />} />
                <Route path="/usuario" element={<Usuario />} />
            </Route>
        </Routes>
    );
}


export default AppRoutes;