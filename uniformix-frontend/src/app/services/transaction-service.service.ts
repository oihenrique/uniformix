import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { transactionInterface } from '../interfaces/transactionInterface';

@Injectable({
  providedIn: 'root'
})
export class TransactionServiceService {
  private baseUrl: string = 'http://localhost:8080/transaction';

  constructor(
    private http: HttpClient
  ) { }

  public post(transaction: transactionInterface): Observable<transactionInterface> {
    return this.http.post<transactionInterface>(this.baseUrl, transaction);
  }
}
