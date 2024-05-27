import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class NotificacionService {
  mensaje: string = '';

  mostrarNotificacion(mensaje: string) {
    console.log('Mostrando notificacion:',mensaje); //Agregar para depuracion
    this.mensaje = mensaje;
    setTimeout(() => this.mensaje = '', 3000); // El mensaje desaparece después de 3 segundos
  }
}