import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { OrderService } from '../order.service';
import { Order } from '../order/order';

@Component({
  selector: 'app-create-order',
  templateUrl: './create-order.component.html',
  styleUrls: ['./create-order.component.css']
})
export class CreateOrderComponent {

  pensamento: Order = {
    conteudo: '',
    autoria: '',
    modelo: ''
  }

  constructor(
    private service: OrderService,
    private router: Router
    ) { }

  criarPensamento() {
    this.service.create(this.pensamento).subscribe(() => {
      this.router.navigate(['listOrder']);
    });
  }

  cancelar() {
    this.router.navigate(['listOrder']);
  }
}
