import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { categoryInterface } from '../interfaces/categoryInterface';

@Injectable({
  providedIn: 'root'
})
export class CategoryServiceService {

  constructor(private http: HttpClient) { }

  public getCategories(): Observable<categoryInterface[]> {
    //const url = 'http://localhost:8080/category';
    const url = 'https://uniformix-repository.onrender.com/category';

    return this.http.get<categoryInterface[]>(url);
  }

  public post(category: categoryInterface): Observable<categoryInterface> {
    const url = 'http://localhost:8080/category';

    return this.http.post<categoryInterface>(url, category);
  }
  
  public setState(name: string, state: boolean): void {
    const url = `http://localhost:8080/category/${name}`;

    var body = { 'name': name, 'state': state }

    this.http.patch<categoryInterface>(url, body).subscribe();
  }
}
