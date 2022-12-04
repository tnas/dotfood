import { OrderService } from './../order.service';
import { Component } from '@angular/core';
import { Order } from '../order/order';

@Component({
  selector: 'app-list-order',
  templateUrl: './list-order.component.html',
  styleUrls: ['./list-order.component.css']
})
export class ListOrderComponent {

  ordersList: Order[] = [];

  constructor(private service: OrderService) { }

  ngOnInit(): void {
    this.service.list().subscribe((list) => {
      this.ordersList = list;
    });
  }
}
