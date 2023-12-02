import { Injectable } from '@angular/core';
import { tableInfoInterface } from '../components/table/tableInfoInterface';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';


@Injectable({
  providedIn: 'root',
})
export class TableInfoServiceService {
  constructor(private httpClient: HttpClient) {}

  getInfo(): Observable<tableInfoInterface[]> {
    const url = 'http://localhost:8080/batch';
    return this.httpClient.get<tableInfoInterface[]>(url);
  }
}
