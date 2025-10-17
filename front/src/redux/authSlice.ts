import { createSlice, type PayloadAction } from "@reduxjs/toolkit"


interface Usuario{
    email: string,
    nome: string
}

interface AuthState{
    isAutenticado: boolean,
    usuario: Usuario | null,
    token: string | null
}

const inicialState: AuthState = {
    isAutenticado: false,
    usuario: null,
    token : null
}

const authSlice = createSlice({
    name :'auth',
    initialState: inicialState,
    reducers:{
        loginSucesso : (state, action : PayloadAction<{usuario: Usuario , token: string}>) =>{
            state.isAutenticado = true;
            state.token = action.payload.token;
            state.usuario = action.payload.usuario;
        },
        logout:(state) =>{
            state.isAutenticado =false;
            state.token = null;
            state.usuario = null;
        }
    }
});

export const {loginSucesso, logout} = authSlice.actions;
export default authSlice.reducer;