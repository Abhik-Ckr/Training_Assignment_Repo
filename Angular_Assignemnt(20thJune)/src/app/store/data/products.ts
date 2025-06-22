import { Product } from '../models/product.model';

export const PRODUCTS: Product[] = [
  {
    id: 1,
    title: 'JOHN CENA - How did he kick out at 2.99 seconds?',
    image: 'https://a.pinatafarm.com/320x180/e394c7c201/john-cena-confuses.jpg',
    price: 299,
    stock: 10,
    inStock: true
  },
  {
    id: 2,
    title: 'JOHN CENA - BIOGRAPHY (Mostly lies)',
    image: 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS7YY4UzdIxgmZjuoKyIcZedLTnETF7Itxt8g&s',
    price: 349,
    stock: 5,
    inStock: true
  },
  {
    id: 3,
    title: 'John Cena - You can\'t see this book',
    image: 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR0yv4aB_ZajPMQS0CE84mW2xXvu-pPNGlZwQ&usqp=CAU',
    price: 199,
    stock: 0,
    inStock: false
  }
]; 