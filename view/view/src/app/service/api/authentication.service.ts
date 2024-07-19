import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment.development';
import { CookieService } from '../web/cookie.service';

@Injectable({
  providedIn: 'root',
})
export class AuthenticationService {
  constructor(private http: HttpClient, private cookieService: CookieService) {
    this.http = http;
  }

  /**
   * Método é responsável por logar um usuário no sistema
   * @param username Nome de usuário
   * @param password Senha do usuário
   * @returns Retorna o token gerado
   */
  private login(username: string, password: string) {
    const headers = new HttpHeaders({
      username: username,
      password: password,
    });

    return this.http.post(environment.api.url + '/login', null, {
      headers: headers,
    });
  }

  loginUser(username: string, password: string) {
    return this.login(username, password);
  }

  /**
   * Método é responsável por registrar um usuário no sistema
   * @param username Nome de usuário
   * @param password Senha do usuário
   * @param role Role do usuário
   * @returns Retorna o token gerado
   */
  private register(username: string, password: string, role: string) {
    const headers = new HttpHeaders({
      Authorization: 'Bearer ' + this.cookieService.getCookies('token'),
      username: username,
      password: password,
      role: role,
    });

    return this.http.post(environment.api.url + '/register', null, {
      headers: headers,
    });
  }

  registerUser(username: string, password: string, role: string) {
    return this.register(username, password, role);
  }
}
