import { TestBed } from '@angular/core/testing';

import { AppModule } from './app.module';

describe('AppModule', () => {
  let service: AppModule;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AppModule);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
