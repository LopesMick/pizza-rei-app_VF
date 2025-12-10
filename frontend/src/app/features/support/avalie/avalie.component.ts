import { Component, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './avalie.component.html',
  styleUrl: './avalie.component.scss',
})
export class AvalieComponent {
  humor = signal<'PESSIMA' | 'RUIM' | 'NORMAL' | 'BOA' | 'EXCELENTE' | null>(null);
  eficiencia = 3;
  solucao = 3;
  comentario = '';
  enviado = false;

  enviar() {
    this.enviado = true;
    setTimeout(() => (this.enviado = false), 900);
  }
}
