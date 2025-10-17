import { useState } from "react";
import { useDispatch } from "react-redux";
import { Link, useNavigate } from "react-router-dom";
import { loginSucesso } from "../../redux/authSlice";
import { LoginNovo, type LoginRequest } from "../../services/authService";


function Login() {
  const navigator = useNavigate();
  const dispatch = useDispatch();

  const [formData, setFormData] = useState<LoginRequest>({
    email: '',
    senha: ''
  });

  const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = event.target;
    setFormData(prevState => ({
      ...prevState,
      [name]: value,
    }));
  };

  const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    debugger;
    event.preventDefault();

    try {

      const loginResponse = await LoginNovo(formData);
      const token = loginResponse.token;

      console.log(token);
      if (token != null) {

        const usuarioLogin = {
          usuario: { email: formData.email, nome: "" },
          token: token
        };
        
        dispatch(loginSucesso(usuarioLogin));

        navigator("/")
      }


      // Exemplo de fetch
      // const response = await fetch(API_URL + "auth/login", {
      //   method: 'POST',
      //   headers: {
      //     'Content-Type': 'application/json',
      //   },
      //   body: JSON.stringify(formData)
      // });

      // if(!response.ok){
      //   throw new Error("Erro ao Logar usuário!")
      // }

      // const data : LoginResponse = await response.json();

      // console.log(data.token);




    } catch (error) {

    }
  }

  return (
    <form onSubmit={handleSubmit}>
      <h2 className="text-center mb-4">Entrar</h2>
      <div className="mb-3">
        <label htmlFor="email" className="form-label">E-mail</label>
        <input
          type="text"
          name="email"
          className="form-control"
          id="email"
          value={formData.email}
          onChange={handleChange}
          placeholder="Digite seu e-mail" />
      </div>
      <div className="mb-3">
        <label htmlFor="senha" className="form-label">Senha</label>
        <input
          name="senha"
          type="password"
          className="form-control"
          id="senha"
          value={formData.senha}
          onChange={handleChange}
          placeholder="Digite sua senha" />
      </div>
      <button type="submit" className="btn btn-primary w-100">Entrar</button>
      <div className="text-center mt-3">
        <span>Não tem uma conta? </span>
        <Link to="/auth/cadastro">Cadastre-se</Link>
      </div>
    </form>
  );
}

export default Login;
