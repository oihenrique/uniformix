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

  public post(transaction: transactionInterface): Observable<Blob> {
    return this.http.post<Blob>(this.baseUrl, transaction, {
      responseType: 'blob' as 'json',
    });
  }

  public get(pageNumber: number): Observable<transactionInterface[]> {
    return this.http.get<transactionInterface[]>(this.baseUrl + `?page=${pageNumber}`);
  }

  public searchByName(name: String): Observable<transactionInterface[]> {
    return this.http.get<transactionInterface[]>(this.baseUrl + `/search/${name}`);
  }
}
