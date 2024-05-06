import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DashboardIndexComponent } from './dashboard-index/dashboard-index.component';
import { ContactoComponent } from './contacto/contacto.component';
import { LoginComponent } from './login/login.component';
import { NosotrosComponent } from './nosotros/nosotros.component';
import { ProductoComponent } from './producto/producto.component';
import { RegistroComponent } from './registro/registro.component';
import { TerminosycondicinonesComponent } from './terminosycondicinones/terminosycondicinones.component';
import { TiendaComponent } from './tienda/tienda.component';
import { PagesRoutingModule } from './pages-routing.module';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { NewTiendaComponent } from './tienda/new-tienda/new-tienda.component';
import { EditTiendaComponent } from './tienda/edit-tienda/edit-tienda.component';
import { CarritoComponent } from './carrito/carrito.component';
import { ConfirmacionComponent } from './confirmacion/confirmacion.component';

@NgModule({
  declarations: [DashboardIndexComponent, LoginComponent, RegistroComponent, TerminosycondicinonesComponent, TiendaComponent, ContactoComponent, ProductoComponent, NosotrosComponent, NewTiendaComponent, EditTiendaComponent, CarritoComponent, ConfirmacionComponent],
  imports: [
    CommonModule,
    PagesRoutingModule,
    ReactiveFormsModule,
    FormsModule
  ],

  exports: [DashboardIndexComponent, LoginComponent, RegistroComponent, TerminosycondicinonesComponent, ContactoComponent, ProductoComponent, TiendaComponent, NosotrosComponent]
})

export class PagesModule { }
