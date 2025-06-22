import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NotifyMe } from './notify-me';

describe('NotifyMe', () => {
  let component: NotifyMe;
  let fixture: ComponentFixture<NotifyMe>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [NotifyMe]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NotifyMe);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
