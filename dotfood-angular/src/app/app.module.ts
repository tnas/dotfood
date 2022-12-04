import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import { CreateOrderComponent } from './components/orders/create-order/create-order.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ListOrderComponent } from './components/orders/list-order/list-order.component';
import { OrderComponent } from './components/orders/order/order.component';
import { DeleteOrderComponent } from './components/orders/delete-order/delete-order.component';
import { EditOrderComponent } from './components/orders/edit-order/edit-order.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    HeaderComponent,
    FooterComponent,
    CreateOrderComponent,
    ListOrderComponent,
    OrderComponent,
    DeleteOrderComponent,
    EditOrderComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
