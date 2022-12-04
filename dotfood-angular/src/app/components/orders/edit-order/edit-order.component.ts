import { OrderService } from './../order.service';
import { Order } from './../order/order';
import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-edit-order',
  templateUrl: './edit-order.component.html',
  styleUrls: ['./edit-order.component.css']
})
export class EditOrderComponent {

  pensamento: Order = {
    id: 0,
    conteudo: '',
    autoria: '',
    modelo: ''
  };

  constructor(
    private service: OrderService,
    private router: Router,
    private route: ActivatedRoute
    ) { }

  ngOnInit(): void {
    const id = parseInt(this.route.snapshot.paramMap.get('id') || '');
    this.service.getById(id).subscribe((order) => this.pensamento = order);
  }

  editOrder() {
    this.service.edit(this.pensamento).subscribe(() => this.router.navigate(['/listOrder']));
  }

  cancel() {
    this.router.navigate(['/listOrder']);
  }
}
