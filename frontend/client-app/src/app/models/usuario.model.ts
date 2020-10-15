import { Endereco } from './endereco.model';

export interface Usuario {
    cpf: string
    email: string
    nome: string
    sobrenome: string,
    dataNasc: Date,
    endereco: Endereco
    role: Role
}

export enum Role {
    CLIENTE,
    FEIRANTE,
    ENTREGADOR
}