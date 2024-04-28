import { Injectable } from '@angular/core';
import { Tienda } from 'app/model/Tienda';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CarritoService {

  public carritoItemList: any = []
  public TiendaList = new BehaviorSubject<any>([]);
  transactionID: any;

  constructor() { }
  getTienda() {
    return this.TiendaList.asObservable();
  }

  setTienda(tienda: any) {
    this.carritoItemList.push(...tienda);
    this.TiendaList.next(tienda);
  }
  addtoCart(tienda: any) {
    if (this.carritoItemList.length > 0) {
      var found = false;
      this.carritoItemList.forEach((a: any) => {
        if (a.id_producto === tienda.id_producto) {
          a.cantidad += 1;
          found = true;
          return
        }
      })

      if (!found) {
        this.carritoItemList.push(tienda);
      }
    } else {
      this.carritoItemList.push(tienda);
    }

    this.TiendaList.next(this.carritoItemList);
    this.getTotalPrecio();
  }
  getTotalPrecio() {
    let grandTotal = 0;
    this.carritoItemList.map((a: any) => {
      grandTotal += parseFloat(a.precio) * (parseInt(a.cantidad) + 1);
    })
    return grandTotal;
  }

  removeCarritoItem(tienda: any) {
    this.carritoItemList.map((a: any, index: any) => {
      if (tienda.id_producto == a.id_producto) {
        this.carritoItemList.splice(index, 1);
      }
    });
    this.TiendaList.next(this.carritoItemList);
  }

  removeAllCart() {
    this.carritoItemList = [];
    this.TiendaList.next(this.carritoItemList);
  }
}
