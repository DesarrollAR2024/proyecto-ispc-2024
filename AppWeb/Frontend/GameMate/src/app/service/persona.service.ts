import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Persona } from 'app/model/Persona';

@Injectable({
  providedIn: 'root'
})
export class PersonaService {
  url="http://localhost:8000/usuarios/"; 

  constructor(private http: HttpClient) { }

  public lista(): Observable<Persona[]>{
    return this.http.get<Persona[]>(this.url + 'lista');
  }

  public detail(id:number): Observable<Persona>{
    return this.http.get<Persona>(this.url + `detail/${id}`);
  }

  public save(persona: Persona): Observable<any>{
    return this.http.post<any>(this.url + `create`, persona);
  }

  public update(id: number, persona: Persona): Observable<any>{
    return this.http.put<any>(this.url + `update/${id}`, persona);
  }

  public delete(id: number): Observable<any>{
    return this.http.delete<any>(this.url + `delete/${id}`);
  }

}
