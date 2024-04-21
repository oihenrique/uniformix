import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { supplierInterface } from '../interfaces/supplierInterface';

@Injectable({
  providedIn: 'root'
})
export class SupplierServiceService {
  
  constructor(private http: HttpClient) { }

  getSuppliers(): Observable<supplierInterface[]> {
    //const url = 'http://localhost:8080/supplier';
    const url = 'https://uniformix-repository.onrender.com/supplier';

    return this.http.get<supplierInterface[]>(url);
  }
}
