import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

const TOKEN_KEY = 'AuthToken';
const USERNAME_KEY = 'AuthUsername';
const AUTHORITIES_KEY = "AuthAuthorities";
const ADMIN_KEY = "AdminToken";

@Injectable({
  providedIn: 'root'
})

export class TokenService {
  roles: Array<string> = [];

  constructor(public router: Router) { }

  public setToken(token: string): void {
    window.sessionStorage.removeItem(TOKEN_KEY);
    window.sessionStorage.setItem(TOKEN_KEY, token);
  }

  public getToken():string {
    return sessionStorage.getItem(TOKEN_KEY)!;
  }

  public setUserName(userName: string): void {
    window.sessionStorage.removeItem(USERNAME_KEY);
    window.sessionStorage.setItem(USERNAME_KEY, userName);
  }

  public getUserName():string {
    return sessionStorage.getItem(USERNAME_KEY)!;
  }

  public setAuthorities(authorities: string[]): void {
    window.sessionStorage.removeItem(AUTHORITIES_KEY);
    window.sessionStorage.setItem(AUTHORITIES_KEY, JSON.stringify(authorities));
  }




  public getAuthorities(): string[] {
    this.roles = [];
    if(sessionStorage.getItem(AUTHORITIES_KEY)){
      JSON.parse(sessionStorage.getItem(AUTHORITIES_KEY)!).forEach((authority:any) => {
        this.roles.push(authority.authority);
      });
    }
    return this.roles;
  }


  public setAdmin(is_admin: boolean) {
    window.sessionStorage.setItem(ADMIN_KEY, JSON.stringify(is_admin));
  }

  public getAdmin(): boolean {
    var isAdmin = Boolean(JSON.parse(window.sessionStorage.getItem(ADMIN_KEY) || 'false'));
    return  isAdmin;
  }


  public logOut(): void{
    window.sessionStorage.clear();
    this.router.navigate([''])
  }

}