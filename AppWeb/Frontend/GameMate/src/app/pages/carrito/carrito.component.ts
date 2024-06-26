import { Component, ElementRef, ViewChild, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Tienda } from 'app/model/Tienda';
import { CarritoService } from 'app/service/carrito.service';

@Component({
  selector: 'app-carrito',
  templateUrl: './carrito.component.html',
  styleUrls: ['./carrito.component.css']
})
export class CarritoComponent implements OnInit {
  public Tienda: any = [];
  public grandTotal: number = 0;
  public mp: any;
  public bricksBuilder: any;
  public cuponDescuento: any;
  public porcentajeDescuento: number = 0;
  public descuentoTotal: number = 0;
  public precioFinal: number = 0;


  @ViewChild('paymentRef', { static: true }) paymentRef!: ElementRef;


  constructor(private carritoServicio: CarritoService, private router: Router) {
  }
  mostrarPaypal() {
    const paypal = document.getElementById("paypal")
    if (paypal) paypal.classList.add("show");
  }

  ocultarPaypal() {
    const paypal = document.getElementById("paypal")
    if (paypal) paypal.classList.remove("show")
  }

  ngOnInit(): void {
    this.carritoServicio.getTienda()
      .subscribe(res => {
        this.Tienda = res;
        this.grandTotal = this.carritoServicio.getTotalPrecio();
        this.precioFinal = this.grandTotal
      })

    if (window.paypal) {
      window.paypal.Buttons(
        {
          createOrden: (data: any, actions: any) => {
            return actions.order.create({
              purchase_units: [
                {
                  grandTotal: {
                    value: this.grandTotal.toString(),
                    currency_code: 'USD'
                  }
                }

              ]
            })
          },
          onApprove: (data: any, actions: any) => {
            return actions.order.capture().then((details: any) => {
              if (details.status === 'COMPLETED') {
                this.carritoServicio.transactionID = details.id;
                this.router.navigate(['confirmacion']);
                console.log(details)
                this.emptycarrito();
              }
            })
          },
          onError: (error: any) => {
            console.log(error);
          }
        }).render(this.paymentRef.nativeElement);
    } else {
      // Handle the case when PayPal SDK script is not loaded
      console.error('PayPal SDK script not loaded');
    }
  }

  cancel() {
    this.router.navigate(['cart']);
    console.log(window.paypal);
  }

  removeproducto(Tienda: any) {
    this.carritoServicio.removeCarritoItem(Tienda);
  }
  emptycarrito() {
    this.carritoServicio.removeAllCart();
  }
  getProductoTotal(tienda: any) {
    return (tienda.cantidad + 1) * parseFloat(tienda.precio);
  }

  calcularDescuento() {
    if (this.cuponDescuento == 'gamemate10') {
      this.porcentajeDescuento = 10;
      this.descuentoTotal = this.grandTotal * (this.porcentajeDescuento / 100);
    }

    else if (this.cuponDescuento == 'gamemate20') {
      this.porcentajeDescuento = 20;
      this.descuentoTotal = this.grandTotal * (this.porcentajeDescuento / 100);
    }

    else if (this.cuponDescuento == 'gamemate30') {
      this.porcentajeDescuento = 30;
      this.descuentoTotal = this.grandTotal * (this.porcentajeDescuento / 100);
    }

    else {
      this.descuentoTotal = 0;
    }

    this.precioFinal = this.grandTotal - this.descuentoTotal
  }
}
    
  


