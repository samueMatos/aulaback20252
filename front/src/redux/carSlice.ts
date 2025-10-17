import { createSlice, type PayloadAction } from "@reduxjs/toolkit"
interface Itens{
    quantidade: string,
    valor: string
}

interface CarrinhoState{
    itens: Itens[],
}

const inicialState: CarrinhoState = {
    itens: []
}

const carrinhoSlice = createSlice({
    name :'carrinho',
    initialState: inicialState,
    reducers:{
        adicionarCarrinho : (state, action : PayloadAction<{item: Itens}>) =>{
            state.itens.push(action.payload.item);
            
        }
    }
});

export const {adicionarCarrinho} = carrinhoSlice.actions;
export default carrinhoSlice.reducer;