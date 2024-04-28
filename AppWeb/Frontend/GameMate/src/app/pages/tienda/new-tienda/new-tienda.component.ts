import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Tienda } from 'app/model/Tienda';
import { TiendaService } from 'app/service/tienda.service';

@Component({
  selector: 'app-new-tienda',
  templateUrl: './new-tienda.component.html',
  styleUrls: ['./new-tienda.component.css']
})
export class NewTiendaComponent implements OnInit {
  precio: number = 0;
  stock: number = 0;
  nombre: string = "";
  descripcion: string = "";
  categoria: string = "";
  imagen: string = "";

  constructor(private tienda: TiendaService, private router: Router){}

  ngOnInit(): void {}

  onCreate(): void{
    let tiendaT = new Tienda(this.precio, this.stock, this.nombre, this.descripcion, this.categoria, this.imagen);
    this.tienda.save(tiendaT).subscribe(
      data =>{
        alert("Producto añadido correctamente");
        this.router.navigate(['tienda']);
      }, err =>{
        alert("Falló");
        this.router.navigate(['tienda']);
      }
    )
  }

  cancelar(){
    this.router.navigate(['tienda']);
  }

}
