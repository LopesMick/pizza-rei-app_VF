import { Component, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { CartService } from '../../../core/services/cart.service';
import { OrdersService } from '../../../core/services/orders.service';

@Component({
  standalone: true,
  imports: [CommonModule],
  templateUrl: './pagamento.component.html',
  styleUrl: './pagamento.component.scss',
})
export class PagamentoComponent {
  pagamento = signal<'CARTAO' | 'DINHEIRO' | 'MAQUINA'>('CARTAO');
  frete = 10;

  endereco = signal({
    rua: 'Rua do Faminto',
    numero: '110',
    bairro: 'Farinha Branca',
    cidade: 'Rio de Janeiro',
    cep: '22000-000',
    referencia: 'Perto da torre torta',
  });

  constructor(public cart: CartService, private orders: OrdersService, private router: Router) {}

  finalizar() {
    if (this.cart.snapshot.length === 0) return;

    const order = this.orders.create(this.cart.snapshot, this.endereco(), this.frete, 60);

    this.cart.clear();
    this.router.navigate(['/acompanhe-pedido'], { queryParams: { id: order.id } });
  }
}
