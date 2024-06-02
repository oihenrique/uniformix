import { Injectable } from '@angular/core';
import { batchInterface } from '../interfaces/batchInterface';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tableInfoInterface } from '../interfaces/tableInfoInterface';

@Injectable({
  providedIn: 'root',
})
export class BatchServiceService {
  //private apiUrl = 'http://localhost:8080/batch';
  private apiUrl = 'https://uniformix-repository.onrender.com/batch';

  constructor(private http: HttpClient) {}

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization:
        'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJhdXRoLWFwaSIsInN1YiI6ImpvYW9AdGVzdGUuY29tIiwiZXhwIjoxNzEzNDk3NjMwfQ.hs1iNm3X72juSkXy_5wPstCZZ0LxpEmphnsU4No-nto',
    }),
  };

  post(batch: batchInterface): Observable<batchInterface> {
    return this.http.post<batchInterface>(this.apiUrl, batch);
  }

  hasEmptyFields(batch: any): boolean {
    return ['category', 'description', 'quantity', 'supplier', 'uniform'].some(
      (field) =>
        batch[field] == null || batch[field] === '' || batch[field] == 0
    );
  }

  getEmptyFields(batch: any): string {
    const emptyFields: string[] = [];

    const fieldsToCheck = [
      'category',
      'description',
      'quantity',
      'supplier',
      'uniform',
    ];

    for (const field of fieldsToCheck) {
      if (batch[field] == null || batch[field] === '' || batch[field] == 0) {
        emptyFields.push(field);
      }
    }

    return emptyFields.join(', ');
  }

  getTotalInventory() {
    return this.http.get<number>(this.apiUrl + `/info/totalInventory`);
  }

  getBatchByText(text: string): Observable<tableInfoInterface[]> {
    return this.http.get<tableInfoInterface[]>(this.apiUrl + `/search/${text}`);
  }

  async generateBatchReport() {}

  async downloadBatchReport() {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/csv',
        Accept: 'application/csv',
      }),
      observe: 'response' as 'response',
      responseType: 'blob' as 'json',
    };

    this.http
      .get<Blob>(
        this.apiUrl + `/report/downloadFile/batch_report.csv`,
        httpOptions
      )
      .subscribe(
        (response: HttpResponse<Blob>) => {
          const filename = response.headers
            .get('Content-Disposition')
            ?.split(';')[1]
            .split('=')[1];
          const blob: Blob = response.body as Blob;
          const a = document.createElement('a');
          a.download = filename ? filename : '';
          a.href = window.URL.createObjectURL(blob);
          a.click();
        },
        (error) => {
          console.error('Erro ao baixar o arquivo:', error);
        }
      );
  }

  delete(code: string): void {
    this.http.delete(this.apiUrl + `/${code}`, this.httpOptions).subscribe();
  }
}
