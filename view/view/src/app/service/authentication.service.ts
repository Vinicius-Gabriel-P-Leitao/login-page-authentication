import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment.development';

@Injectable({
  providedIn: 'root',
})
export class AuthenticationService {
  constructor(private http: HttpClient) {
    this.http = http;
  }

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
}
