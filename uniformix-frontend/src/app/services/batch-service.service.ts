import { Injectable } from '@angular/core';
import { batchInterface } from '../interfaces/batchInterface';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tableInfoInterface } from '../interfaces/tableInfoInterface';


@Injectable({
  providedIn: 'root'
})
export class BatchServiceService {
  private apiUrl = 'http://localhost:8080/batch';

  constructor(private http: HttpClient) { }

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      Authorization: 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJhdXRoLWFwaSIsInN1YiI6ImpvYW9AdGVzdGUuY29tIiwiZXhwIjoxNzEyNzE2MDU5fQ.al1AB4Titfa0UIXzf9KLGYIESq2AeABVB0l_hGLImHQ'
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

  getTotalInventory() {
    const url = 'http://localhost:8080/batch/info/totalInventory';

    return this.http.get<number>(url);
  }

  getBatchByText(text: string): Observable<tableInfoInterface[]> {
    const url = `http://localhost:8080/batch/search/${text}`;

    return this.http.get<tableInfoInterface[]>(url);
  }

  downloadBatchReport() {
    const url = 'http://localhost:8080/batch/report/downloadFile/batch_report.csv';
  
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'text/csv',
        'Accept': 'text/csv'
      }),
      responseType: 'blob' as 'json'  // Define o tipo de resposta como blob
    };
  
    return this.http.get(url, httpOptions);
  }
  

  delete(code: string): void{
    const url = `http://localhost:8080/batch/${code}`;

    this.http.delete(url, this.httpOptions).subscribe();
  }
}
