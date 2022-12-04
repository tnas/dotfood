import { EditOrderComponent } from './components/orders/edit-order/edit-order.component';
import { ListOrderComponent } from './components/orders/list-order/list-order.component';
import { CreateOrderComponent } from './components/orders/create-order/create-order.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DeleteOrderComponent } from './components/orders/delete-order/delete-order.component';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'listOrder',
    pathMatch: 'full'
  },
  {
    path: 'createOrder',
    component: CreateOrderComponent
  },
  {
    path: 'listOrder',
    component: ListOrderComponent
  },
  {
    path: 'orders/deleteOrder/:id',
    component: DeleteOrderComponent
  },
  {
    path: 'orders/editOrder/:id',
    component: EditOrderComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
