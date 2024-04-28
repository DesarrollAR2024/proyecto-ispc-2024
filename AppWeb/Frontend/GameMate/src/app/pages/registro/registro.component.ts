import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UsuarioService, Usuario } from 'app/service/usuario.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-registro',
  templateUrl: './registro.component.html',
  styleUrls: ['./registro.component.css']
})
export class RegistroComponent implements OnInit {
  registroForm: FormGroup;
  usuarioNuevo: Usuario = new Usuario();

  constructor(private formBuilder: FormBuilder, private usuarioService: UsuarioService, private router: Router) {
    this.registroForm = this.formBuilder.group({
      usuario: ['', Validators.required],
      nombre: ['', Validators.required],
      apellido: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(8)]]
    });
  }

  ngOnInit() {}

  submitRegistroForm(event: Event): void {
    event.preventDefault();

    if (this.registroForm.valid) {
      console.log("Enviando al servidor...");
      this.usuarioNuevo = this.registroForm.value; // Obtener el objeto Usuario del formulario

      this.usuarioNuevo.is_active = true;
      this.usuarioNuevo.is_admin = false;
      this.usuarioNuevo.is_staff = false;

      this.usuarioService.onCrearUsuario(this.usuarioNuevo).subscribe(
        data => {
          console.log(data);
          (data.id > 0)
            alert("El registro ha sido creado satisfactoriamente. A continuación, por favor inicie sesión.");
            this.router.navigate(['/login']);
          }, error => {
          console.log("Error al crear el usuario:", error);
        }
      );
    } else {
      this.registroForm.markAllAsTouched();
      console.log('El formulario es inválido');
    }
  }

  get password() {
    return this.registroForm.get('password');
  }

  get email() {
    return this.registroForm.get('email');
  }

  get nombre() {
    return this.registroForm.get('nombre');
  }

  get apellido() {
    return this.registroForm.get('apellido');
  }

  get usuario() {
    return this.registroForm.get('usuario');
  }
}
