export type PizzaSize = 'P' | 'M' | 'G';

export interface PizzaPrice {
  P: number;
  M: number;
  G: number;
}

export interface Pizza {
  id: string;
  nome: string;
  descricao?: string;
  preco: PizzaPrice;
  imagem?: string;
}
