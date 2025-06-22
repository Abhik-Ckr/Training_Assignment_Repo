import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CartService } from '../../services/cart.service';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Component({
  selector: 'app-cart-icon',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './cart-icon.html',
  styleUrl: './cart-icon.scss'
})
export class CartIcon {
  itemCount$: Observable<number>;

  constructor(private cartService: CartService) {
    this.itemCount$ = this.cartService.cart$.pipe(
      // Map to total count
      map(items => items.reduce((sum, item) => sum + item.quantity, 0))
    );
  }
}
