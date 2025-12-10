import { Pizza, PizzaSize } from './pizza.model';
import { Beverage, BeverageSize } from './beverage.model';

export type CartItemType = 'PIZZA' | 'BEBIDA';

export interface CartItemBase {
  id: string;
  tipo: CartItemType;
  quantidade: number;
}

export interface PizzaCartItem extends CartItemBase {
  tipo: 'PIZZA';
  pizza: Pizza;
  tamanho: PizzaSize;
  precoUnitario: number;
}

export interface BeverageCartItem extends CartItemBase {
  tipo: 'BEBIDA';
  bebida: Beverage;
  tamanho: BeverageSize;
  precoUnitario: number;
}

export type CartItem = PizzaCartItem | BeverageCartItem;
