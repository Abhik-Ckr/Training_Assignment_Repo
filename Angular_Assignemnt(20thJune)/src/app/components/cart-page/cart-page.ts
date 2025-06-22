import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CartService } from '../../services/cart.service';
import { CartItem } from '../../store/models/cart-item.model';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-cart-page',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './cart-page.html',
  styleUrl: './cart-page.scss'
})
export class CartPage {
  cartItems$: Observable<CartItem[]>;

  constructor(private cartService: CartService) {
    this.cartItems$ = this.cartService.cart$;
  }

  removeFromCart(productId: number) {
    this.cartService.removeFromCart(productId);
  }
}
