import { Component, computed, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterLink } from '@angular/router';
import { CartService } from '../../../core/services/cart.service';
import { MenuService } from '../../../core/services/menu.service';

@Component({
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './carrinho.component.html',
  styleUrl: './carrinho.component.scss',
})
export class CarrinhoComponent {
  private cart = inject(CartService);
  private menu = inject(MenuService);
  private router = inject(Router);

  // ✅ agora não dá erro
  items$ = this.cart.cartItems$;
  subtotal = computed(() => this.cart.subtotal());
  frete = 10;

  coca2L = this.menu.getBebidas().find((b) => b.id === 'coca');

  inc(id: string, current: number) {
    this.cart.updateQuantity(id, current + 1);
  }

  dec(id: string, current: number) {
    this.cart.updateQuantity(id, current - 1);
  }

  incluirCoca2L() {
    if (this.coca2L) {
      this.cart.addBebida(this.coca2L, '2L');
      // se seu TS reclamar do tipo do tamanho, use:
      // this.cart.addBebida(this.coca2L, '2L' as const);
    }
  }

  irPagamento() {
    if (this.cart.snapshot.length === 0) return;
    this.router.navigate(['/pagamento']);
  }
}
