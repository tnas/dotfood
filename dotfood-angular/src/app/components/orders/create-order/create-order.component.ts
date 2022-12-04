import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
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
      conteudo: ['', Validators.compose([
        Validators.required,
        Validators.pattern(/(.|\s)*\S(.|\s)*/)])],
      autoria: ['', Validators.compose([
        Validators.required,
        Validators.minLength(5)
      ])],
      modelo: ['modelo2']
    });
  }

  criarPensamento() {

    if (this.orderForm.valid) {
      this.service.create(this.orderForm.value).subscribe(() => {
        this.router.navigate(['listOrder']);
      });
    }
  }

  enableButton(): string {

    if (this.orderForm.valid) {
      return 'botao';
    }
    else {
      return 'botao__desabilitado';
    }
  }

  cancelar() {
    this.router.navigate(['listOrder']);
  }
}
