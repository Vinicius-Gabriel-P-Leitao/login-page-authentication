import { Component, Input } from '@angular/core';
import { FormControl, ReactiveFormsModule } from '@angular/forms';

/**
 * Componente de input, recebe como parâmetros o tipo de input, placeholder e o control do formControl
 *
 * Forma de usar:
 * <app-input
 *  [typeInput]="'email'"
 *  [placeholder]="'email'"
 *  [formControl]="form.controls['email']"
 * >
 * </app-input>
 */
@Component({
  selector: 'app-input',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './input.component.html',
  styleUrl: './input.component.scss',
})
export class InputComponent {
  @Input({ required: true, alias: 'type-input' }) typeInput: string = 'text';
  @Input({ required: true }) placeholder: string = 'vazio';
  @Input({ alias: 'form-control' }) control: FormControl | any;
}
