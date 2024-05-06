import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardIndexComponent } from './dashboard-index/dashboard-index.component';
import { LoginComponent } from './login/login.component';
import { RegistroComponent } from './registro/registro.component';
import { TerminosycondicinonesComponent } from './terminosycondicinones/terminosycondicinones.component';
import { ContactoComponent } from './contacto/contacto.component';
import { ProductoComponent } from './producto/producto.component';
import { TiendaComponent } from './tienda/tienda.component';
import { NosotrosComponent } from './nosotros/nosotros.component';
import { NewTiendaComponent } from './tienda/new-tienda/new-tienda.component';
import { EditTiendaComponent } from './tienda/edit-tienda/edit-tienda.component';
import { CarritoComponent } from './carrito/carrito.component';
import { ConfirmacionComponent } from './confirmacion/confirmacion.component';

const routes: Routes = [
  { path: '', component: DashboardIndexComponent },
  { path: 'login', component: LoginComponent },
  { path: 'registro', component: RegistroComponent },
  { path: 'terminos', component: TerminosycondicinonesComponent },
  { path: 'contacto', component: ContactoComponent },
  { path: 'producto/:id', component: ProductoComponent },
  { path: 'tienda', component: TiendaComponent },
  { path: 'nosotros', component: NosotrosComponent },
  { path: 'newtienda', component: NewTiendaComponent },
  { path: 'carrito', component: CarritoComponent },
  { path: 'edittienda/:id', component: EditTiendaComponent },
  { path: 'confirmacion', component: ConfirmacionComponent }
];

@NgModule({
  declarations: [],
  imports: [
    RouterModule.forChild(routes)
  ],
  exports: [RouterModule]
})
export class PagesRoutingModule { }
