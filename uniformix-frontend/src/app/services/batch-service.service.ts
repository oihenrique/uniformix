import { Injectable } from '@angular/core';
import { batchInterface } from '../interfaces/batchInterface';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class BatchServiceService {
  private apiUrl = 'http://localhost:8080/batch';

  constructor(private http: HttpClient) { }

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      Authorization: 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJhdXRoLWFwaSIsInN1YiI6ImpvYW9AdGVzdGUuY29tIiwiZXhwIjoxNzAyNzQwNTE3fQ.9-OHwDNwt4reKbFnFrX6EQPNMGa2J1h62fgG89QCCkU'
    })
  };

  post(batch: batchInterface): Observable<batchInterface> {
    
     return this.http.post<batchInterface>(this.apiUrl, batch, this.httpOptions);
  }
}
