import { CartItem } from './cart-item.model';

export type OrderStatus = 'RECEBIDO' | 'PREPARACAO' | 'SAIDA' | 'ENTREGA';

export interface OrderAddress {
  rua: string;
  numero: string;
  bairro: string;
  cidade: string;
  cep: string;
  referencia?: string;
}

export interface Order {
  id: string;
  itens: CartItem[];
  frete: number;
  criadoEm: string; // ISO
  previsaoMinutos: number;
  endereco: OrderAddress;
  status: OrderStatus;
  total: number;
}
