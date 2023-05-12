import { ComponentFixture, TestBed } from '@angular/core/testing';

import { View2reviewComponent } from './view2review.component';

describe('View2reviewComponent', () => {
  let component: View2reviewComponent;
  let fixture: ComponentFixture<View2reviewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ View2reviewComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(View2reviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
