import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProdutoListarComponent } from './produto-listar.component';

describe('ProdutoListarComponent', () => {
  let component: ProdutoListarComponent;
  let fixture: ComponentFixture<ProdutoListarComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProdutoListarComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProdutoListarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
