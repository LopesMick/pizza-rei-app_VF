import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { CartItem, PizzaCartItem, BeverageCartItem } from '../models/cart-item.model';
import { Pizza, PizzaSize } from '../models/pizza.model';
import { Beverage, BeverageSize } from '../models/beverage.model';

const STORAGE_KEY = 'pdr_cart_v1';

@Injectable({ providedIn: 'root' })
export class CartService {
  private items$ = new BehaviorSubject<CartItem[]>(this.load());

  cartItems$ = this.items$.asObservable();

  get snapshot(): CartItem[] {
    return this.items$.value;
  }

  addPizza(pizza: Pizza, tamanho: PizzaSize) {
    const precoUnitario = pizza.preco[tamanho];
    const id = `PIZZA:${pizza.id}:${tamanho}`;

    const existing = this.snapshot.find((i) => i.id === id);
    if (existing && existing.tipo === 'PIZZA') {
      this.updateQuantity(id, existing.quantidade + 1);
      return;
    }

    const item: PizzaCartItem = {
      id,
      tipo: 'PIZZA',
      quantidade: 1,
      pizza,
      tamanho,
      precoUnitario,
    };

    this.set([...this.snapshot, item]);
  }

  addBebida(bebida: Beverage, tamanho: BeverageSize) {
    const precoUnitario = bebida.preco[tamanho];
    const id = `BEBIDA:${bebida.id}:${tamanho}`;

    const existing = this.snapshot.find((i) => i.id === id);
    if (existing && existing.tipo === 'BEBIDA') {
      this.updateQuantity(id, existing.quantidade + 1);
      return;
    }

    const item: BeverageCartItem = {
      id,
      tipo: 'BEBIDA',
      quantidade: 1,
      bebida,
      tamanho,
      precoUnitario,
    };

    this.set([...this.snapshot, item]);
  }

  updateQuantity(id: string, quantidade: number) {
    const next = this.snapshot
      .map((i) => (i.id === id ? { ...i, quantidade } : i))
      .filter((i) => i.quantidade > 0);

    this.set(next);
  }

  clear() {
    this.set([]);
  }

  subtotal(): number {
    return this.snapshot.reduce((acc, i) => acc + i.precoUnitario * i.quantidade, 0);
  }

  private set(items: CartItem[]) {
    this.items$.next(items);
    localStorage.setItem(STORAGE_KEY, JSON.stringify(items));
  }

  private load(): CartItem[] {
    try {
      const raw = localStorage.getItem(STORAGE_KEY);
      return raw ? JSON.parse(raw) : [];
    } catch {
      return [];
    }
  }
}
