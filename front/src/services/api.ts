import axios from "axios";
import { store } from "../redux/store";

const api = axios.create({
    baseURL: import.meta.env.VITE_API_URL || 'http://localhost:8080/'
});

api.interceptors.request.use(
    (config)=>{
        const state =store.getState();
        const token = state.auth.token;
        if(token){
            config.headers.Authorization =`Bearer ${token}`;
        }
        return config;
    },
    (error)=>{
        return Promise.reject(error);
    }
);
export default api;