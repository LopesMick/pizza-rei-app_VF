export type BeverageSize = 'LATA' | '600ML' | '2L';

export interface BeveragePrice {
  LATA: number;
  '600ML': number;
  '2L': number;
}

export interface Beverage {
  id: string;
  nome: string;
  preco: BeveragePrice;
  imagem?: string;
}
