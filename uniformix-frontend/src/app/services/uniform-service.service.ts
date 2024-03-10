import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { uniformInterface } from '../interfaces/uniformInterface';

@Injectable({
  providedIn: 'root'
})
export class UniformServiceService {
  private url = 'http://localhost:8080/uniform';

  constructor(private http: HttpClient) { }

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      Authorization: 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJhdXRoLWFwaSIsInN1YiI6ImpvYW9AdGVzdGUuY29tIiwiZXhwIjoxNzA5NjA3NjU4fQ.A8vLPWbUr-CJOlLGs6X0H2cjD3IY6i7VKaxoTdP0GTI'
    })
  };

  generateUniformObject(uniform: uniformInterface): uniformInterface {
    return uniform;
  }

  post(uniform: uniformInterface): Observable<HttpResponse<any>> {
    return this.http.post(this.url, uniform, { observe: 'response', headers:this.httpOptions.headers}).pipe(
      map((response: HttpResponse<any>) => response)
    );
  }

  hasEmptyFields(uniform: any): boolean {
    return ['name', 'quantity', 'sex', 'size']
    .some(field => uniform[field] == null || uniform[field] === '' || uniform[field] == 0)
  }
}
