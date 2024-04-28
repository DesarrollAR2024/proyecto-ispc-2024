import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Tienda } from 'app/model/Tienda';

@Injectable({
  providedIn: 'root'
})
export class TiendaService {
  private url = 'http://localhost:8000/api/productos/';
  private urlEdit = 'http://localhost:8000/api/modificarproducto/';
  private urlAdd = 'http://localhost:8000/api/agregarproducto/';

  constructor(private http:HttpClient) { }

  public cargarTienda(): Observable<Tienda[]>{
    return this.http.get<Tienda[]>(this.url);
  }

  public detail(id: number): Observable<Tienda>{
    return this.http.get<any>(this.urlEdit + `${id}`);
  }

  public save(tienda: Tienda): Observable<any>{
    return this.http.post<any>(this.urlAdd, tienda);
  }

  public update(id_producto: number, tienda: Tienda): Observable<any>{
    return this.http.put<any>(this.urlEdit + `${id_producto}/`, tienda);
  }

  public delete(id_producto: number): Observable<any>{
    return this.http.delete<any>(this.urlEdit + `${id_producto}/`);
  }
}
