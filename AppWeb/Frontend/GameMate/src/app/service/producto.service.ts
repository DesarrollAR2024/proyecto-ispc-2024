import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Producto } from 'app/model/Producto';

@Injectable({
  providedIn: 'root'
})
export class ProductoService {
  private url = 'http://localhost:8000/api/productos/';
  constructor(private http:HttpClient) { }

  public getProducto(): Observable<Producto[]> {
    return this.http.get<Producto[]>(this.url);
  }

  public detail(id: number): Observable<Producto>{
    return this.http.get<any>(this.url + `${id}`);
  }

}
