import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export class Usuario {
  id: number = 0;
  nombre: string = "";
  apellido: string = "";
  nickname: string = "";
  email: string = "";
  password: string = "";
  is_active: boolean = true;
  is_admin: boolean = false;
  is_staff: boolean = false;
}


@Injectable({
  providedIn: 'root'
})
export class UsuarioService {
  url = "http://127.0.0.1:8000/api/auth/registro/";

  constructor(private http: HttpClient) { 
    console.log("Servicio Usuarios está corriendo");
  }

  onCrearUsuario(usuario: Usuario): Observable<Usuario> {
    return this.http.post<Usuario>(this.url, usuario);
  }
}
