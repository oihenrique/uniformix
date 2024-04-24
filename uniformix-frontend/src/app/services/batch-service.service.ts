import { Injectable } from '@angular/core';
import { batchInterface } from '../interfaces/batchInterface';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Observable, generate } from 'rxjs';
import { tableInfoInterface } from '../interfaces/tableInfoInterface';


@Injectable({
  providedIn: 'root'
})
export class BatchServiceService {
  //private apiUrl = 'http://localhost:8080/batch';
  private apiUrl = 'https://uniformix-repository.onrender.com/batch';

  constructor(private http: HttpClient) { }

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      Authorization: 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJhdXRoLWFwaSIsInN1YiI6ImpvYW9AdGVzdGUuY29tIiwiZXhwIjoxNzEzNDk3NjMwfQ.hs1iNm3X72juSkXy_5wPstCZZ0LxpEmphnsU4No-nto'
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
    //const url = 'http://localhost:8080/batch/info/totalInventory';
    const url = 'https://uniformix-repository.onrender.com/batch/info/totalInventory';

    return this.http.get<number>(url);
  }

  getBatchByText(text: string): Observable<tableInfoInterface[]> {
    //const url = `http://localhost:8080/batch/search/${text}`;
    const url = `https://uniformix-repository.onrender.com/batch/search/${text}`;

    return this.http.get<tableInfoInterface[]>(url);
  }

  async generateBatchReport() {

  }

  async downloadBatchReport() {
    //const url = 'http://localhost:8080/batch/report/downloadFile/batch_report.csv';
    const url = 'https://uniformix-repository.onrender.com/batch/report/downloadFile/batch_report.csv';

    //const generateFileUrl = 'http://localhost:8080/batch/report/generateCsv';
    const generateFileUrl = 'https://uniformix-repository.onrender.com/batch/report/generateCsv';

    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/csv',
        'Accept': 'application/csv',
      }),
      observe: 'response' as 'response',
      responseType: 'blob' as 'json'
    };

    await this.http.get(generateFileUrl).toPromise();

    this.http.get<Blob>(url, httpOptions).subscribe(
      (response: HttpResponse<Blob>) => {
        const filename = response.headers.get('Content-Disposition')?.split(";")[1].split('=')[1];
          const blob: Blob = response.body as Blob;
          const a = document.createElement('a');
          a.download = filename ? filename : "";
          a.href = window.URL.createObjectURL(blob);
          a.click();
      },
      (error) => {
        console.error('Erro ao baixar o arquivo:', error);
      }
    );    
  }

  delete(code: string): void{
    const url = `http://localhost:8080/batch/${code}`;
    //const url = `https://uniformix-repository.onrender.com/batch/${code}`;

    this.http.delete(url, this.httpOptions).subscribe();
  }
}
