import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RegistroService {
  private apiUrl = 'http://127.0.0.1:8000/api/auth/registro/'; 

  constructor(private http: HttpClient) { }

  getDatos(): Observable<any> {
    return this.http.get<any>(this.apiUrl);
  }

  crearRegistro(data: any): Observable<any> {
    return this.http.post<any>(this.apiUrl, data);
  }

  actualizarRegistro(id: string, data: any): Observable<any> {
    const url = `${this.apiUrl}/${id}`;
    return this.http.put<any>(url, data);
  }

  eliminarRegistro(id: string): Observable<any> {
    const url = `${this.apiUrl}/${id}`;
    return this.http.delete<any>(url);
  }
}


