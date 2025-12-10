import { Injectable } from '@angular/core';
import { Order, OrderAddress } from '../models/order.model';
import { CartItem } from '../models/cart-item.model';

const STORAGE_KEY = 'pdr_orders_v1';

@Injectable({ providedIn: 'root' })
export class OrdersService {
  list(): Order[] {
    return this.load();
  }

  getById(id: string): Order | undefined {
    return this.load().find((o) => o.id === id);
  }

  create(itens: CartItem[], endereco: OrderAddress, frete = 10, previsaoMinutos = 60): Order {
    const subtotal = itens.reduce((acc, i) => acc + i.precoUnitario * i.quantidade, 0);
    const total = subtotal + frete;

    const order: Order = {
      id: crypto.randomUUID(),
      itens,
      frete,
      criadoEm: new Date().toISOString(),
      previsaoMinutos,
      endereco,
      status: 'RECEBIDO',
      total,
    };

    const all = this.load();
    all.unshift(order);
    this.save(all);

    return order;
  }

  updateStatus(id: string, status: Order['status']) {
    const all = this.load().map((o) => (o.id === id ? { ...o, status } : o));
    this.save(all);
  }

  private load(): Order[] {
    try {
      const raw = localStorage.getItem(STORAGE_KEY);
      return raw ? JSON.parse(raw) : [];
    } catch {
      return [];
    }
  }

  private save(orders: Order[]) {
    localStorage.setItem(STORAGE_KEY, JSON.stringify(orders));
  }
}
