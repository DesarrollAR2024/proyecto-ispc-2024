export class Registro {
  id?: number;
  nombre: string;
  apellido: string;
  nombreDeUsuario: string;
  email: string;
  password: string;

  constructor(nombre: string, apellido: string, nombreDeUsuario: string, email: string, password: string) {
    this.nombre = nombre;
    this.apellido = apellido;
    this.nombreDeUsuario = nombreDeUsuario;
    this.email = email;
    this.password = password;
  }
}
