import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { supplierInterface } from '../interfaces/supplierInterface';

@Injectable({
  providedIn: 'root',
})
export class SupplierServiceService {
  private baseUrl: string =
    'https://uniformix-repository.onrender.com/supplier';

  constructor(private http: HttpClient) {}

  getSuppliers(): Observable<supplierInterface[]> {
    return this.http.get<supplierInterface[]>(this.baseUrl);
  }

  public post(supplier: supplierInterface): Observable<supplierInterface> {
    return this.http.post<supplierInterface>(this.baseUrl, supplier);
  }

  public setState(name: string, state: boolean): void {
    const url = `http://localhost:8080/supplier/${name}`;

    var body = { name: name, state: state };

    this.http
      .patch<supplierInterface>(this.baseUrl + `/${name}`, body)
      .subscribe();
  }
}
