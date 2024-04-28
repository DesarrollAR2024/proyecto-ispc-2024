import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Jwt } from 'app/model/Jwt';
import { LoginUsuario } from 'app/model/Login';
import { NuevoUsuario } from 'app/model/Nuevo-usuario';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  url="http://localhost:8000/api/auth/login/";

  constructor(private http:HttpClient) {}

  public nuevo(nuevoUsuario: NuevoUsuario): Observable<any>{
    return this.http.post<any>(this.url, nuevoUsuario);
  }

   public login(loginUsuario: LoginUsuario): Observable<Jwt>{
    return this.http.post<any>(this.url, loginUsuario)
   }
}
