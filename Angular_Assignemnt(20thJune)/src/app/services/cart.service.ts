import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { CartItem } from '../store/models/cart-item.model';
import { Product } from '../store/models/product.model';

@Injectable({ providedIn: 'root' })
export class CartService {
  private cartItems: CartItem[] = [];
  private cartSubject = new BehaviorSubject<CartItem[]>([]);

  cart$ = this.cartSubject.asObservable();

  addToCart(product: Product): boolean {
    const item = this.cartItems.find(i => i.product.id === product.id);
    if (item) {
      if (item.quantity >= 2) {
        return false; // Cannot add more than 2
      }
      item.quantity++;
    } else {
      this.cartItems.push({ product, quantity: 1 });
    }
    this.cartSubject.next([...this.cartItems]);
    return true;
  }

  removeFromCart(productId: number): void {
    const item = this.cartItems.find(i => i.product.id === productId);
    if (item) {
      if (item.quantity > 1) {
        item.quantity--;
      } else {
        this.cartItems = this.cartItems.filter(i => i.product.id !== productId);
      }
      this.cartSubject.next([...this.cartItems]);
    }
  }

  getCartItems(): CartItem[] {
    return [...this.cartItems];
  }

  getTotalCount(): number {
    return this.cartItems.reduce((sum, item) => sum + item.quantity, 0);
  }
} 