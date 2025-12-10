import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './fale-conosco.component.html',
  styleUrl: './fale-conosco.component.scss',
})
export class FaleConoscoComponent {
  mensagem = '';
  enviado = false;

  enviar() {
    if (!this.mensagem.trim()) return;
    this.enviado = true;
    this.mensagem = '';
    setTimeout(() => (this.enviado = false), 800);
  }
}
