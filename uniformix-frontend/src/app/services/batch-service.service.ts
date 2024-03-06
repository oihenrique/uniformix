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
      Authorization: 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJhdXRoLWFwaSIsInN1YiI6ImpvYW9AdGVzdGUuY29tIiwiZXhwIjoxNzA5Njg4ODAzfQ.6stAak2rsVpawBhO1oFeWN5ckrUkxqhpggoG7LGa_P4'
    })
  };

  post(batch: batchInterface): Observable<batchInterface> {
    
     return this.http.post<batchInterface>(this.apiUrl, batch, this.httpOptions);
  }

  hasEmptyFields(batch: any): boolean {
    return ['category', 'description', 'quantity', 'supplier', 'uniform']
      .some(field => batch[field] == null || batch[field] === '' || batch[field] == 0);
  }

  getEmptyFields(batch: any): string {
    const emptyFields: string[] = [];
  
    const fieldsToCheck = ['category', 'description', 'quantity', 'supplier', 'uniform'];
  
    for (const field of fieldsToCheck) {
      if (batch[field] == null || batch[field] === '' || batch[field] == 0) {
        emptyFields.push(field);
      }
    }
  
    return emptyFields.join(', ');
  }

  delete(code: string): void{
    const url = `http://localhost:8080/batch/${code}`;

    this.http.delete(url, this.httpOptions).subscribe();
  }
}
