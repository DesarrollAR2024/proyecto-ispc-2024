import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CarritoService } from 'app/service/carrito.service';
import { TokenService } from 'app/service/token.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  isLogged = false;
  public totalTienda: number = 0;
  constructor(private router: Router, private tokenService: TokenService, private carritoService: CarritoService) { }

  ngOnInit(): void {
    if (this.tokenService.getToken()) {
      this.isLogged = true;
    } else {
      this.isLogged = false;
    }
    this.carritoService.getTienda()
      .subscribe(res => {
        this.totalTienda = 0;
        res.forEach((a: any) => {
          this.totalTienda += (a.cantidad + 1)
        })
      })
  }

  onLogOut(): void {
    this.tokenService.logOut();
    window.location.reload();
  }

  login() {
    this.router.navigate(['/login'])
  }

}
