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
      Authorization: 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJhdXRoLWFwaSIsInN1YiI6ImpvYW9AdGVzdGUuY29tIiwiZXhwIjoxNzAxOTk5NDc1fQ.qn-itQhoUG5F22N7AMgMmXDPfmsNGCvaWN038P5LDKk'
    })
  };

  post(batch: batchInterface): Observable<batchInterface> {
    
     return this.http.post<batchInterface>(this.apiUrl, batch, this.httpOptions);
  }
}
