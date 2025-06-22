import { Routes } from '@angular/router';
import { ProductList } from './components/product-list/product-list';
import { CartPage } from './components/cart-page/cart-page';

export const routes: Routes = [
  { path: '', component: ProductList },
  { path: 'cart', component: CartPage },
  // Add other routes here
];
