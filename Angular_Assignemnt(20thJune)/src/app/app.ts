import { Component } from '@angular/core';
import { RouterOutlet, RouterLink } from '@angular/router';
import { CartIcon } from './components/cart-icon/cart-icon';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, RouterLink, CartIcon],
  templateUrl: './app.html',
  styleUrl: './app.scss'
})
export class App {
  protected title = 'amazon_book';
}
