import { Component } from '@angular/core';
import { CarritoService } from 'app/service/carrito.service';

@Component({
  selector: 'app-confirmacion',
  templateUrl: './confirmacion.component.html',
  styleUrls: ['./confirmacion.component.css']
})
export class ConfirmacionComponent {
  transactionId = "";

  constructor(private carrito: CarritoService) { }

  ngOInit(): void {
    this.transactionId = this.carrito.transactionID;

  }


}
