import { Component } from '@angular/core';
import { AbstractControl, FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { RouterOutlet } from '@angular/router';
import { ButtonComponent } from './components/button/button.component';
import { ImageContainerComponent } from './components/image-container/image-container.component';
import { InputComponent } from './components/input/input.component';
import { MatIcon } from '@angular/material/icon';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    RouterOutlet,
    ButtonComponent,
    InputComponent,
    ImageContainerComponent,
    ReactiveFormsModule,
    MatIcon
  ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss',
})
export class AppComponent {
  form: FormGroup;

  constructor() {
    this.form = new FormGroup({
      username: new FormControl(''),
      password: new FormControl(''),
    });
  }

  getFormControl(control: AbstractControl | null): FormControl {
    return control as FormControl;
  }

  onSubmit() {
    console.log(this.form.value);
  }
}
