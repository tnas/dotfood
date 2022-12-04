import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { OrderService } from '../order.service';
import { Order } from '../order/order';

@Component({
  selector: 'app-create-order',
  templateUrl: './create-order.component.html',
  styleUrls: ['./create-order.component.css']
})
export class CreateOrderComponent {

  orderForm!: FormGroup;

  constructor(
    private service: OrderService,
    private router: Router,
    private formBuilder: FormBuilder
    ) { }

  ngOnInit(): void {
    this.orderForm = this.formBuilder.group({
      conteudo: ['Formulario Reativo'],
      autoria: ['Thiago Nascimento'],
      modelo: ['modelo2']
    });
  }

  criarPensamento() {
    this.service.create(this.orderForm.value).subscribe(() => {
      this.router.navigate(['listOrder']);
    });
  }

  cancelar() {
    this.router.navigate(['listOrder']);
  }
}
