import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ItemCarrinhoEditarComponent } from './item-carrinho-editar.component';

describe('ItemCarrinhoEditarComponent', () => {
  let component: ItemCarrinhoEditarComponent;
  let fixture: ComponentFixture<ItemCarrinhoEditarComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ItemCarrinhoEditarComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ItemCarrinhoEditarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
