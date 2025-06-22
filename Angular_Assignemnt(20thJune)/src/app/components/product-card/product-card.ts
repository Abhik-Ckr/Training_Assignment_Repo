import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Product } from '../../store/models/product.model';
import { CartService } from '../../services/cart.service';

@Component({
  selector: 'app-product-card',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './product-card.html',
  styleUrl: './product-card.scss'
})
export class ProductCard {
  @Input() product!: Product;
  showWarning = false;
  showSuccess = false;

  constructor(private cartService: CartService) {}

  addToCart() {
    const added = this.cartService.addToCart(this.product);
    if (!added) {
      this.showWarning = true;
      this.showSuccess = false;
    } else {
      this.showWarning = false;
      this.showSuccess = true;
      setTimeout(() => this.showSuccess = false, 1000);
    }
  }
}
