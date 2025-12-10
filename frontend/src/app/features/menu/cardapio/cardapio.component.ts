import { Component, inject, signal } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MenuService } from '../../../core/services/menu.service';
import { CartService } from '../../../core/services/cart.service';

import { Pizza, PizzaSize } from '../../../core/models/pizza.model';
import { Beverage, BeverageSize } from '../../../core/models/beverage.model';

@Component({
  standalone: true,
  imports: [CommonModule],
  templateUrl: './cardapio.component.html',
  styleUrl: './cardapio.component.scss',
})
export class CardapioComponent {
  private menu = inject(MenuService);
  private cart = inject(CartService);

  // ✅ agora o TS não reclama
  pizzas = signal<Pizza[]>(this.menu.getPizzas());
  bebidas = signal<Beverage[]>(this.menu.getBebidas());

  selectedPizzaSize = signal<PizzaSize>('M');
  selectedBeverageSize = signal<BeverageSize>('2L');

  addPizza(p: Pizza) {
    this.cart.addPizza(p, this.selectedPizzaSize());
  }

  addBebida(b: Beverage) {
    this.cart.addBebida(b, this.selectedBeverageSize());
  }
}
