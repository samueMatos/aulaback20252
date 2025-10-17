import { Outlet } from "react-router-dom";

function LayoutLogin() {
  const backgroundImage = `url("https://recreio.com.br/wp-content/uploads/animacoes/burro_dragao_e_filhotes.jpg")`;

  return (
    <div
      className="d-flex justify-content-center align-items-center vh-100"
      style={{
        backgroundImage,
        backgroundSize: "contain",
        backgroundRepeat: "no-repeat",
        backgroundPosition: "center",
      }}
    >
      <div className="container">
        <div className="row justify-content-center">
          <div className="col-12 col-sm-8 col-md-6 col-lg-4 bg-white p-4 rounded shadow">
            <Outlet />
          </div>
        </div>
      </div>
    </div>
  );
}

export default LayoutLogin;
