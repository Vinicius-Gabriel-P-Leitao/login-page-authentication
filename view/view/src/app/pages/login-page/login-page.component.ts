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
import { ResponseToken } from '../../@types/response-token';
import { ButtonComponent } from '../../components/button/button.component';
import { InputComponent } from '../../components/input/input.component';
import { AuthenticationService } from '../../service/api/authentication.service';
import { Router, RouterLink } from '@angular/router';
import { CookieService } from '../../service/web/cookie.service';

@Component({
  selector: 'app-login-page',
  standalone: true,
  imports: [
    ButtonComponent,
    InputComponent,
    RouterLink,
    ReactiveFormsModule,
    MatIcon,
    NgIf,
  ],
  templateUrl: './login-page.component.html',
  styleUrl: './login-page.component.scss',
})
export class LoginPageComponent {
  form: FormGroup;

  constructor(
    private authService: AuthenticationService,
    private cookieService: CookieService,
    private snackbar: MatSnackBar,
    private router: Router
  ) {
    this.form = new FormGroup({
      username: new FormControl(''),
      password: new FormControl(''),
    });
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
   * Método responsável por enviar os dados capturados dos inputs, ele pegar o username e password, manda para um service e após isso guarda o token e manda uma mensagem de sucesso ou erro na tela
   * @returns Token ou erro na tela do usuário
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

    this.authService.loginUser(username, password).subscribe({
      next: (response: ResponseToken | any) => {
        this.cookieService.setCookie('token', response.token);

        this.snackbar.open('Login efetuado com sucesso!', 'Fechar', {
          duration: 5000,
          verticalPosition: 'top',
          horizontalPosition: 'center',
        });

        this.router.navigate(['/register']);
      },
      error: (error: any) => {
        let errorMessage = 'Falha ao fazer login. Verifique suas credenciais.';

        if (error.status === 403) {
          errorMessage =
            'Credenciais inválidas. Verifique seu username e password.';
        } else if (error.status === 500) {
          errorMessage =
            'Ocorreu um erro no servidor. Por favor, tente novamente mais tarde.';
        }

        // Exibir mensagem de erro usando MatSnackBar
        this.snackbar.open(errorMessage, 'Fechar', {
          duration: 5000,
          verticalPosition: 'top',
          horizontalPosition: 'center',
        });
      },
    });

    this.form.reset();
  }

  /**
   * Método para limpar o formulário
   */
  onClean() {
    this.form.reset();
  }
}
