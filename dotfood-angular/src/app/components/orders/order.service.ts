import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Order } from './order/order';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  private readonly API = ' http://localhost:3000/pensamentos';

  constructor(private http: HttpClient) { }

  list(): Observable<Order[]> {
    return this.http.get<Order[]>(this.API);
  }

  create(order: Order): Observable<Order> {
    return this.http.post<Order>(this.API, order);
  }

  delete(id: number): Observable<Order> {
    return this.http.delete<Order>(`${this.API}/${id}`);
  }

  edit(order: Order): Observable<Order> {
    return this.http.put<Order>(`${this.API}/${order.id}`, order);
  }

  getById(id: number): Observable<Order> {
    return this.http.get<Order>(`${this.API}/${id}`);
  }

}
