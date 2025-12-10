import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { OrdersService } from '../../../core/services/orders.service';

@Component({
  standalone: true,
  imports: [CommonModule],
  templateUrl: './acompanhe-pedido.component.html',
  styleUrl: './acompanhe-pedido.component.scss',
})
export class AcompanhePedidoComponent {
  private route = inject(ActivatedRoute);
  private orders = inject(OrdersService);

  // pega por queryParam (como você já estava fazendo)
  // e deixa um fallback opcional por paramMap
  orderId: string | null =
    this.route.snapshot.queryParamMap.get('id') ?? this.route.snapshot.paramMap.get('id');

  order = this.orderId ? this.orders.getById(this.orderId) : undefined;

  steps = [
    { key: 'RECEBIDO', label: 'Pedido Recebido' },
    { key: 'PREPARACAO', label: 'Em Preparação' },
    { key: 'SAIDA', label: 'Saída para Entrega' },
    { key: 'ENTREGA', label: 'Entrega' },
  ] as const;

  isActive(key: string) {
    if (!this.order) return false;

    const orderIndex = this.steps.findIndex((s) => s.key === this.order!.status);
    const stepIndex = this.steps.findIndex((s) => s.key === key);
    return stepIndex <= orderIndex;
  }
}
