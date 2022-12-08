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
  currentPage: number = 1;
  moreOrders:boolean = true;
  filtro: string = '';

  constructor(private service: OrderService) { }

  ngOnInit(): void {
    this.service.list(this.currentPage, this.filtro).subscribe((list) => {
      this.ordersList = list;
    });
  }

  loadMoreOrders() {
    this.service.list(this.currentPage++, this.filtro)
      .subscribe(list => {
        this.ordersList.push(...list);
        if (!list.length) {
          this.moreOrders = false;
        }
      });
  }

  searchOrder() {
    this.currentPage = 1;
    this.moreOrders = true;
    this.service.list(this.currentPage, this.filtro)
      .subscribe((list) => this.ordersList = list);
  }
}
