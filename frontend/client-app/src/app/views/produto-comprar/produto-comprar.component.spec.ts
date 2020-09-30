import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProdutoComprarComponent } from './produto-comprar.component';

describe('ProdutoComprarComponent', () => {
  let component: ProdutoComprarComponent;
  let fixture: ComponentFixture<ProdutoComprarComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProdutoComprarComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProdutoComprarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
