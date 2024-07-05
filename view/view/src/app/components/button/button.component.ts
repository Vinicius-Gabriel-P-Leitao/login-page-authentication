import { Component, Input } from '@angular/core';
import { FormsModule } from '@angular/forms';

/**
 * Componente de botão, recebe como um ng-content para os valores internos ao botão
 *
 * Forma de usar:
 * <button app-button>
 *    {{componente interno}}
 * </button>
 */
@Component({
  selector: 'button[app-button]',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './button.component.html',
  styleUrl: './button.component.scss',
})
export class ButtonComponent {}
