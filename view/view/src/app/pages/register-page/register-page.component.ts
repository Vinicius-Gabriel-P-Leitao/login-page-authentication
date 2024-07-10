import { Component } from '@angular/core';
import { CookieService } from '../../service/web/cookie.service';

@Component({
  selector: 'app-register-page',
  standalone: true,
  imports: [],
  templateUrl: './register-page.component.html',
  styleUrl: './register-page.component.scss',
})
export class RegisterPageComponent {
  constructor(private cookieService: CookieService) {}

  register() {
    console.log(this.cookieService.getCookies('token'));
  }
}
