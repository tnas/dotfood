import { Component, Input } from '@angular/core';
import { Order } from './order';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent {

  @Input() pensamento: Order = {
    id: 0,
    conteudo: 'Learning Angular',
    autoria: 'Eu mesmo',
    modelo: 'modelo3'
  }

  larguraPensamento(): string {
    if (this.pensamento.conteudo.length > 256) {
      return 'pensamento-g';
    }
    else {
      return 'pensamento-p';
    }
  }
}
