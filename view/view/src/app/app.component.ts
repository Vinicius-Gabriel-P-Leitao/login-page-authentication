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
import { RouterOutlet } from '@angular/router';
import { ResponseToken } from './@types/response-token';
import { ButtonComponent } from './components/button/button.component';
import { InputComponent } from './components/input/input.component';
import { AuthenticationService } from './service/authentication.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    RouterOutlet,
    ButtonComponent,
    InputComponent,
    ReactiveFormsModule,
    MatIcon,
    NgIf,
  ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss',
})
export class AppComponent {
  form: FormGroup;

  constructor(
    private authService: AuthenticationService,
    private snackbar: MatSnackBar
  ) {
    this.form = new FormGroup({
      username: new FormControl(''),
      password: new FormControl(''),
    });
  }

  getFormControl(control: AbstractControl | null): FormControl {
    return control as FormControl;
  }

  onSubmit() {
    const username = this.form.get('username')?.value;
    const password = this.form.get('password')?.value;

    this.authService.loginUser(username, password).subscribe({
      next: (response: ResponseToken | any) => {
        document.cookie = `token=${response.token}`;

        this.snackbar.open('Login efetuado com sucesso!', 'Fechar', {
          duration: 5000,
          verticalPosition: 'top',
          horizontalPosition: 'center',
        });
      },
      error: (error: any) => {
        this.snackbar.open(
          'Falha ao fazer login. Verifique suas credenciais.', 'Fechar',
          {
            duration: 5000,
            verticalPosition: 'top',
            horizontalPosition: 'center',
          }
        );
      },
    });

    this.form.reset();
  }

  onClean() {
    this.form.reset();
  }
}
