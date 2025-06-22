# AmazonBook

AmazonBook is a modern Angular application for browsing and shopping books online. It features a clean UI, product cards, and a shopping cart experience similar to Amazon. The app demonstrates state management patterns inspired by NgRx for handling cart operations.

## Features

- Browse a list of books with images, prices, and stock status
- Add books to the shopping cart (max 2 per book)
- View cart contents on a dedicated cart page
- See quantity for each book in the cart
- Remove books from the cart or decrease their quantity
- Responsive, card-based UI for both product and cart pages

## State Management (NgRx-like Functionality)

While this project does not use the full NgRx library, it implements similar state management concepts:

- **Cart State**: Managed globally using a `CartService` with a `BehaviorSubject`, allowing all components to reactively access and update the cart.
- **Actions**: Methods like `addToCart` and `removeFromCart` act as actions to update the cart state.
- **Selectors**: Components subscribe to the cart observable (`cart$`) to select and display cart data.

This approach provides a simple, scalable pattern for state management in Angular apps, inspired by NgRx principles.

## Getting Started

### Run the App

1. Install dependencies:
   ```bash
   npm install
   ```
2. Start the development server:
   ```bash
   ng serve
   ```
3. Open your browser and navigate to [http://localhost:4200/](http://localhost:4200/)

The application will automatically reload whenever you modify any of the source files.

## Code scaffolding

Angular CLI includes powerful code scaffolding tools. To generate a new component, run:

```bash
ng generate component component-name
```

For a complete list of available schematics (such as `components`, `directives`, or `pipes`), run:

```bash
ng generate --help
```

## Building

To build the project run:

```bash
ng build
```

This will compile your project and store the build artifacts in the `dist/` directory. By default, the production build optimizes your application for performance and speed.

## Running unit tests

To execute unit tests with the [Karma](https://karma-runner.github.io) test runner, use the following command:

```bash
ng test
```

## Running end-to-end tests

For end-to-end (e2e) testing, run:

```bash
ng e2e
```

Angular CLI does not come with an end-to-end testing framework by default. You can choose one that suits your needs.

## Additional Resources

For more information on using the Angular CLI, including detailed command references, visit the [Angular CLI Overview and Command Reference](https://angular.dev/tools/cli) page.
