import { OrderService } from './../order.service';
import { Order } from './../order/order';
import { Component } from '@angular/core';
import { ActivatedRoute, Route, Router } from '@angular/router';

@Component({
  selector: 'app-delete-order',
  templateUrl: './delete-order.component.html',
  styleUrls: ['./delete-order.component.css']
})
export class DeleteOrderComponent {

  order: Order = {
    id: 0,
    conteudo: '',
    autoria: '',
    modelo: ''
  }

  constructor(
    private service: OrderService,
    private router: Router,
    private route: ActivatedRoute
    ) { }

  ngOnInit(): void {
    const id = parseInt(this.route.snapshot.paramMap.get('id') || '');
    this.service.getById(id).subscribe((order) => {
      this.order = order;
    })
  }

  deleteOrder() {

    if (this.order.id) {
      this.service.delete(this.order.id).subscribe(() => {
        this.router.navigate(['/listOrder']);
      });
    }
  }

  cancel() {
    this.router.navigate(['/listOrder']);
  }
}
