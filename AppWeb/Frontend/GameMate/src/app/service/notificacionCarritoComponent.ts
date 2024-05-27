import { Component } from '@angular/core';
import { NotificacionService } from "app/service/notificacionService";

@Component({
  selector: 'app-notificacion',
  template: `<div *ngIf="notificacionService.mensaje" class="notificacion">{{ notificacionService.mensaje }}</div>`,
  styles: [`
    .notificacion {
      position: fixed;
      top: 10px;
      right: 10px;
      background-color: #90ee90;
      padding: 10px;
      border-radius: 5px;
      box-shadow: 0 2px 4px rgba(0,0,0,0.2);
    }
  `]
})
export class NotificacionCarritoComponent {
  constructor(public notificacionService: NotificacionService) {}
}