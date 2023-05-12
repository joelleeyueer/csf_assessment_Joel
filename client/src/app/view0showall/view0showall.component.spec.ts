import { ComponentFixture, TestBed } from '@angular/core/testing';

import { View0showallComponent } from './view0showall.component';

describe('View0showallComponent', () => {
  let component: View0showallComponent;
  let fixture: ComponentFixture<View0showallComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ View0showallComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(View0showallComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
