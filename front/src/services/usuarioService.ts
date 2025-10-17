import api from "./api"

export interface Usuario {
    id: number,
    nome: string,
    CPF: string,
    email : string
}

export async function buscarTodosUsuarios():Promise<Usuario[]> {
    const response = await api.get<Usuario[]>("/usuarios");
    return response.data;
}