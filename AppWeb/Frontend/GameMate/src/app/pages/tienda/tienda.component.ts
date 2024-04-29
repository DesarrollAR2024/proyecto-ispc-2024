import { Component } from '@angular/core';
import { TiendaService } from 'app/service/tienda.service';
import { Tienda } from 'app/model/Tienda';
import { TokenService } from 'app/service/token.service';
import { CarritoService } from 'app/service/carrito.service';

@Component({
  selector: 'app-tienda',
  templateUrl: './tienda.component.html',
  styleUrls: ['./tienda.component.css']
})
export class TiendaComponent {
  tienda: Tienda[] = [];
  public TiendaList: any;

  constructor(private tiendaS: TiendaService, private tokenService: TokenService, private carritoService: CarritoService) { }
  isLogged = false;

  ngOnInit(): void {
    this.getTienda();
    if (this.tokenService.getToken()) {
      this.isLogged = true;
    } else {
      this.isLogged = false;
    }
    if (this.TiendaList) {
      this.TiendaList.forEach((a: any) => {
        Object.assign(a, { cantidad: 1, total: a.precio });
      });
    }
  }
  
  getTienda(): void {
    this.tiendaS.cargarTienda().subscribe(
      data => {
        data.forEach((element, i) => {
          this.tienda.push({ ...element, cantidad: 0 });
        });
      }
      )
    }
    
  addcart(tienda: any) {
    this.carritoService.addtoCart(tienda);
  }
  
  delete(id?: number){
    if(id != undefined){
      this.tiendaS.delete(id).subscribe(
        data => {
          this.tiendaElim();
        }, err => {
          alert("No se pudo eliminar");
        }
        )
      }
    }

  tiendaElim(): void{
  this.tiendaS.cargarTienda().subscribe(
      data => {
        this.tienda = data;
      }
    )
  }
}
