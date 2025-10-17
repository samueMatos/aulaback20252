import { Link } from "react-router-dom";

function Cadastrese() {
  return (
    <>
      <h2 className="text-center mb-4">Cadastrar</h2>
      <form>
        <div className="mb-3">
          <label htmlFor="nome" className="form-label">Nome completo</label>
          <input type="text" className="form-control" id="nome" placeholder="Digite seu nome" />
        </div>
        <div className="mb-3">
          <label htmlFor="email" className="form-label">E-mail</label>
          <input type="email" className="form-control" id="email" placeholder="Digite seu e-mail" />
        </div>
        <div className="mb-3">
          <label htmlFor="senha" className="form-label">Senha</label>
          <input type="password" className="form-control" id="senha" placeholder="Crie uma senha" />
        </div>
        <button type="submit" className="btn btn-success w-100">Cadastrar</button>
      </form>
      <div className="text-center mt-3">
        <span>Já tem uma conta? </span>
        <Link to="/auth/login">Entrar</Link>
      </div>
    </>
  );
}

export default Cadastrese;
