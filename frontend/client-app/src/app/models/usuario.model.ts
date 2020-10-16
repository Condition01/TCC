import { Endereco } from './endereco.model';

export interface Usuario {
    cpf: string
    email: string
    nome: string
    sobrenome: string,
    endereco: Endereco
    senha: string
    dataNasc: Date,
    role: Role
}

export enum Role {
    CLIENTE,
    FEIRANTE,
    ENTREGADOR
}