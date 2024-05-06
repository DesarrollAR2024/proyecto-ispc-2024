import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginUsuario } from 'app/model/Login';
import { AuthService } from 'app/service/auth.service';
import { TokenService } from 'app/service/token.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  isLogged = false;
  isLogginFail = false;
  form: FormGroup;
  loginUsuario!: LoginUsuario;
  email!: string;
  password!: string;
  roles: string[] = [];
  errMsj!: string;

  constructor(private formBuilder: FormBuilder, private authService: AuthService, private router: Router, private tokenService: TokenService){
    this.form= this.formBuilder.group({
      email:['',[Validators.required, Validators.email]],
      password:['',[Validators.required, Validators.minLength(8)]]
    })
  }

  get Password(){
    return this.form.get("password");
  }
  
  get Email(){
    return this.form.get("email");
  }

  get PasswordValid()
  {
    return this.Password?.touched && !this.Password?.valid;
  }

  get EmailValid()
  {
    return this.Email?.touched && !this.Email?.valid;
  } 

  ngOnInit(): void {
    if(this.tokenService.getToken()){
      this.isLogged = true;
      this.isLogginFail = false;
      this.roles = this.tokenService.getAuthorities();
    }
  }

  onLogin(): void{
    this.loginUsuario = new LoginUsuario(this.email, this.password); 
    this.authService.login(this.loginUsuario).subscribe(data => {
      this.isLogged = true;
      this.isLogginFail = false;
      this.tokenService.setToken(data.token);
      this.tokenService.setUserName(data.nombreUsuario);
      this.tokenService.setAuthorities(data.authorities);
      this.roles = data.authorities;
      window.location.replace('');
      this.router.navigate(['']);
    }, err => {
      this.isLogged = false;
      this.isLogginFail = true;
      this.errMsj = err.error.mensaje;
      console.log(this.errMsj);
    })
  }

  onLogOut():void{
    this.tokenService.logOut();
    window.location.reload();
  }
    
}
