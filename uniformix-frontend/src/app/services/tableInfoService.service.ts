import { Injectable } from '@angular/core';
import { tableInfoInterface } from '../interfaces/tableInfoInterface';
import { Observable, of } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { BatchServiceService } from './batch-service.service';

@Injectable({
  providedIn: 'root',
})
export class TableInfoServiceService {
  public batchCode: string = "";

  constructor(
    private httpClient: HttpClient,
    private batchService: BatchServiceService
  ) {}

  getInfo(pageNumber: Number): Observable<tableInfoInterface[]> {
    const url = `http://localhost:8080/batch?page=${pageNumber}`;
    return this.httpClient.get<tableInfoInterface[]>(url);
  }

  public deleteBatch(info: tableInfoInterface[], code: string): Observable<void> {
    let index = info.findIndex((e) => e.codigo === code);
    if (index !== -1) {
      this.batchService.delete(code);
    }

    return of();
  }
}
