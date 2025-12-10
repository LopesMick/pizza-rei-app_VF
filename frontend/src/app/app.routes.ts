import { Routes } from '@angular/router';
import { LayoutComponent } from './layout/layout.component';

export const routes: Routes = [
  {
    path: '',
    component: LayoutComponent,
    children: [
      { path: '', pathMatch: 'full', redirectTo: 'cardapio' },

      // AUTH
      {
        path: 'cadastro',
        loadComponent: () =>
          import('./features/auth/cadastro/cadastro.component').then((m) => m.CadastroComponent),
      },
      {
        path: 'login',
        loadComponent: () =>
          import('./features/auth/login/login.component').then((m) => m.LoginComponent),
      },
      {
        path: 'recuperar-senha',
        loadComponent: () =>
          import('./features/auth/recuperar-senha/recuperar-senha.component').then(
            (m) => m.RecuperarSenhaComponent
          ),
      },

      // MENU
      {
        path: 'cardapio',
        loadComponent: () =>
          import('./features/menu/cardapio/cardapio.component').then((m) => m.CardapioComponent),
      },

      // CART
      {
        path: 'carrinho',
        loadComponent: () =>
          import('./features/cart/carrinho/carrinho.component').then((m) => m.CarrinhoComponent),
      },
      {
        path: 'pagamento',
        loadComponent: () =>
          import('./features/cart/pagamento/pagamento.component').then((m) => m.PagamentoComponent),
      },

      // ORDERS
      {
        path: 'pedidos-anteriores',
        loadComponent: () =>
          import('./features/orders/pedidos-anteriores/pedidos-anteriores.component').then(
            (m) => m.PedidosAnterioresComponent
          ),
      },
      {
        path: 'acompanhe-pedido',
        loadComponent: () =>
          import('./features/orders/acompanhe-pedido/acompanhe-pedido.component').then(
            (m) => m.AcompanhePedidoComponent
          ),
      },
      {
        path: 'cancelar-pedido',
        loadComponent: () =>
          import('./features/orders/cancelar-pedido/cancelar-pedido.component').then(
            (m) => m.CancelarPedidoComponent
          ),
      },

      // SUPPORT
      {
        path: 'fale-conosco',
        loadComponent: () =>
          import('./features/support/fale-conosco/fale-conosco.component').then(
            (m) => m.FaleConoscoComponent
          ),
      },
      {
        path: 'avalie',
        loadComponent: () =>
          import('./features/support/avalie/avalie.component').then((m) => m.AvalieComponent),
      },

      // STATIC
      {
        path: 'sobre',
        loadComponent: () =>
          import('./features/static/sobre/sobre.component').then((m) => m.SobreComponent),
      },
      {
        path: 'termos',
        loadComponent: () =>
          import('./features/static/termos/termos.component').then((m) => m.TermosComponent),
      },
      {
        path: 'privacidade',
        loadComponent: () =>
          import('./features/static/privacidade/privacidade.component').then(
            (m) => m.PrivacidadeComponent
          ),
      },
    ],
  },

  { path: '**', redirectTo: 'cardapio' },
];
