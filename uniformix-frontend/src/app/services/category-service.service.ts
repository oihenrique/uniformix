import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { categoryInterface } from '../interfaces/categoryInterface';

@Injectable({
  providedIn: 'root'
})
export class CategoryServiceService {
  private baseUrl: string = 'https://uniformix-repository.onrender.com/category';

  constructor(private http: HttpClient) { }

  public getCategories(): Observable<categoryInterface[]> {
    //const url = 'http://localhost:8080/category';
    return this.http.get<categoryInterface[]>(this.baseUrl);
  }

  public post(category: categoryInterface): Observable<categoryInterface> {
    const url = 'http://localhost:8080/category';

    return this.http.post<categoryInterface>(this.baseUrl, category);
  }
  
  public setState(name: string, state: boolean): void {
    const url = `http://localhost:8080/category/${name}`;

    var body = { 'name': name, 'state': state }

    this.http.patch<categoryInterface>(this.baseUrl + `/${name}`, body).subscribe();
  }
}
