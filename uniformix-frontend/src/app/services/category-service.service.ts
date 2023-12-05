import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { categoryInterface } from '../interfaces/categoryInterface';

@Injectable({
  providedIn: 'root'
})
export class CategoryServiceService {

  constructor(private http: HttpClient) { }

  getCategories(): Observable<categoryInterface[]> {
    const url = 'http://localhost:8080/category';
    return this.http.get<categoryInterface[]>(url);
  }
  
}
