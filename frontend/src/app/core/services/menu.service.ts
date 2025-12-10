import { Injectable } from '@angular/core';
import { Pizza } from '../models/pizza.model';
import { Beverage } from '../models/beverage.model';

@Injectable({ providedIn: 'root' })
export class MenuService {
  private pizzas: Pizza[] = [
    { id: 'marg', nome: 'Margherita', preco: { P: 29.9, M: 39.9, G: 49.9 } },
    { id: 'pepp', nome: 'Pepperoni', preco: { P: 34.9, M: 44.9, G: 54.9 } },
    { id: '4q', nome: 'Quatro Queijos', preco: { P: 36.9, M: 46.9, G: 56.9 } },
    { id: 'veg', nome: 'Vegetariana', preco: { P: 32.9, M: 42.9, G: 52.9 } },
    { id: 'cala', nome: 'Calabresa', preco: { P: 33.9, M: 43.9, G: 53.9 } },
    { id: 'frcat', nome: 'Frango com Catupiry', preco: { P: 35.9, M: 45.9, G: 55.9 } },
    { id: 'fresp', nome: 'Frango Especial', preco: { P: 37.9, M: 47.9, G: 57.9 } },
    { id: 'champ', nome: 'Champignon', preco: { P: 38.9, M: 48.9, G: 58.9 } },
    { id: 'bacon', nome: 'Bacon com Cream Cheese', preco: { P: 39.9, M: 49.9, G: 59.9 } },
  ];

  private bebidas: Beverage[] = [
    { id: 'coca', nome: 'Coca Cola', preco: { LATA: 8, '600ML': 10, '2L': 14 } },
    { id: 'coca0', nome: 'Coca Cola Zero', preco: { LATA: 8, '600ML': 10, '2L': 14 } },
    { id: 'guara0', nome: 'Guaraná Antártica Zero', preco: { LATA: 8, '600ML': 10, '2L': 14 } },
    { id: 'guara', nome: 'Guaraná Antártica', preco: { LATA: 8, '600ML': 10, '2L': 14 } },
  ];

  getPizzas(): Pizza[] {
    return this.pizzas;
  }

  getBebidas(): Beverage[] {
    return this.bebidas;
  }
}
