import { Component } from '@angular/core';
import { Producto } from 'app/model/Producto';
import { ActivatedRoute, Router } from '@angular/router';
import { ProductoService } from 'app/service/producto.service';
import { CarritoService } from 'app/service/carrito.service';
import { Tienda } from 'app/model/Tienda';

@Component({
  selector: 'app-producto',
  templateUrl: './producto.component.html',
  styleUrls: ['./producto.component.css']
})
export class ProductoComponent {
  producto: any ={};
  public Tienda: any = [];

  constructor(private productoS: ProductoService, private activatedRoute: ActivatedRoute, private router: Router, private carritoService: CarritoService) {}

  ngOnInit(): void{
    this.getProducto();
  }

  getProducto(): void{
    let id = this.activatedRoute.snapshot.params['id'];
    this.productoS.detail(id).subscribe(
      data => {
        this.producto = data;
      }, err => {
        alert("Error de comunicación");
        this.router.navigate(['tienda']);
      }
    )
  }
  
}



