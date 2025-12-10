import { Component, signal } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  standalone: true,
  imports: [CommonModule],
  templateUrl: './cancelar-pedido.component.html',
  styleUrl: './cancelar-pedido.component.scss',
})
export class CancelarPedidoComponent {
  confirmado = signal(false);

  cancelar() {
    this.confirmado.set(true);
  }
}
