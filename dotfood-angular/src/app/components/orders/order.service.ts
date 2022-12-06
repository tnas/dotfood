import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http'
import { Order } from './order/order';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  private readonly API = ' http://localhost:3000/pensamentos';

  constructor(private http: HttpClient) { }

  list(page: number): Observable<Order[]> {

    const itemsPerPage = 6;

    let params = new HttpParams()
      .set('_page', page)
      .set('_limit', itemsPerPage);

    return this.http.get<Order[]>(this.API, { params });
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
