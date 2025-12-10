import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { OrdersService } from '../../../core/services/orders.service';

@Component({
  standalone: true,
  imports: [CommonModule],
  templateUrl: './pedidos-anteriores.component.html',
  styleUrl: './pedidos-anteriores.component.scss',
})
export class PedidosAnterioresComponent {
  private ordersService = inject(OrdersService);
  orders = this.ordersService.list();
}
