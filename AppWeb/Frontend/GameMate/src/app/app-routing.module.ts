import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ContactoComponent } from './pages/contacto/contacto.component';
import { DashboardIndexComponent } from './pages/dashboard-index/dashboard-index.component';
import { LoginComponent } from './pages/login/login.component';
import { NosotrosComponent } from './pages/nosotros/nosotros.component';
import { ProductoComponent } from './pages/producto/producto.component';
import { RegistroComponent } from './pages/registro/registro.component';
import { TerminosycondicinonesComponent } from './pages/terminosycondicinones/terminosycondicinones.component';
import { TiendaComponent } from './pages/tienda/tienda.component';
import { EditTiendaComponent } from './pages/tienda/edit-tienda/edit-tienda.component';
import { CarritoComponent } from './pages/carrito/carrito.component';
import { ConfirmacionComponent } from './pages/confirmacion/confirmacion.component';
const routes: Routes = [
  {
    path: 'pages',
    loadChildren: () =>
      import('./pages/pages.module').then((m) => m.PagesModule),
  },
  { path: '', component: DashboardIndexComponent },
  { path: '', redirectTo: 'tienda', pathMatch: 'full' },
  { path: 'contacto', component: ContactoComponent },
  { path: 'login', component: LoginComponent },
  { path: 'nosotros', component: NosotrosComponent },
  { path: 'producto', component: ProductoComponent },
  { path: 'registro', component: RegistroComponent },
  { path: 'terminosycondiciones', component: TerminosycondicinonesComponent },
  { path: 'tienda', component: TiendaComponent },
  { path: 'edittienda', component: EditTiendaComponent },
  { path: 'carrito', component: CarritoComponent },
  { path: 'confirmacion', component: ConfirmacionComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
