import { NgIf } from '@angular/common';
import { Component } from '@angular/core';
import {
  AbstractControl,
  FormControl,
  FormGroup,
  ReactiveFormsModule,
} from '@angular/forms';
import { MatIcon } from '@angular/material/icon';
import { MatSnackBar } from '@angular/material/snack-bar';
import { RouterLink } from '@angular/router';
import { ResponseToken } from '../../@types/response-token';
import { ButtonComponent } from '../../components/button/button.component';
import { InputComponent } from '../../components/input/input.component';
import { AuthenticationService } from '../../service/api/authentication.service';
import { CookieService } from '../../service/web/cookie.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-register-page',
  standalone: true,
  imports: [
    ButtonComponent,
    InputComponent,
    RouterLink,
    ReactiveFormsModule,
    MatIcon,
    NgIf,
  ],
  templateUrl: './register-page.component.html',
  styleUrl: './register-page.component.scss',
})
export class RegisterPageComponent {
  form: FormGroup;

  constructor(
    private cookieService: CookieService,
    private snackbar: MatSnackBar,
    private authService: AuthenticationService
  ) {
    if (this.cookieService.getCookies('token').length === 0) {
      this.snackbar.open('Você está sem um token de autenticação!', 'Fechar', {
        duration: 5000,
        verticalPosition: 'top',
        horizontalPosition: 'center',
      });
    }

    this.form = new FormGroup({
      username: new FormControl(''),
      password: new FormControl(''),
      role: new FormControl(''),
    });
  }

  /**
   * Método responsável por verificar se o control é inválido e enviar os dados para o backend, assim registrando um novo usuário
   * @returns Retorna o control do formulário
   */
  onSubmit() {
    // Valida se o formulário é valido
    if (this.form.invalid) {
      this.snackbar.open('Formulário foi preenchido erroneamente!', 'Fechar', {
        duration: 5000,
        verticalPosition: 'top',
        horizontalPosition: 'center',
      });

      return;
    }

    const username = this.form.get('username')?.value;
    const password = this.form.get('password')?.value;
    const role = this.form.get('role')?.value;

    // Registra o usuário na API
    this.authService.registerUser(username, password, role).subscribe({
      next: (response: ResponseToken | any) => {
        this.snackbar.open('Usuário cadastrado com sucesso', 'Fechar', {
          duration: 5000,
          verticalPosition: 'top',
          horizontalPosition: 'center',
        });
      },
      error: (error: HttpErrorResponse) => {
        this.snackbar.open('Erro ao cadastrar usuário!', 'Fechar', {
          duration: 5000,
          verticalPosition: 'top',
          horizontalPosition: 'center',
        });

        console.log(error);
      },
    });

    this.form.reset();
  }

  /**
   * Método para gerenciar o controle do formulário
   * @param control recebe uma classe de abstração
   * @returns retorna meu form como um FormControl
   */
  getFormControl(control: AbstractControl | null): FormControl {
    return control as FormControl;
  }

  /**
   * Método para limpar o formulário
   */
  onClean() {
    this.form.reset();
  }
}
