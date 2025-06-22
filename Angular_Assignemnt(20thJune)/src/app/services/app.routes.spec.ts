import { TestBed } from '@angular/core/testing';

import { AppRoutes } from './app.routes';

describe('AppRoutes', () => {
  let service: AppRoutes;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AppRoutes);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
