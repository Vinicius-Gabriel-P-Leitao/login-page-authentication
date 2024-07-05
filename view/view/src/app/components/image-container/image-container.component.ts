import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-image-container',
  standalone: true,
  imports: [],
  templateUrl: './image-container.component.html',
  styleUrl: './image-container.component.scss',
})
export class ImageContainerComponent {
  @Input({ required: true, alias: 'image-link' }) imageLink: string = '#';
  @Input({ required: true, alias: 'image-alt' }) imageAlt: string = 'Vazio';
}
