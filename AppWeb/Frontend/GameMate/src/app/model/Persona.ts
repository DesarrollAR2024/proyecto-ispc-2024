export class Persona{
    id?: number;
    usuario: string;
    nombre: string;
    apellido: string;
    email: string;
    password: string;

    constructor(usuario: string, nombre: string, apellido: string, email: string, password: string){
        this.usuario = usuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
    }
}