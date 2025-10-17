import { configureStore } from "@reduxjs/toolkit";
import authReducer from "./authSlice"
import carrinhoReducer from "./carSlice"


export const store = configureStore({
    reducer: {
        auth : authReducer,
        carrinho: carrinhoReducer
    }
});

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;
