import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { uniformInterface } from '../interfaces/uniformInterface';

@Injectable({
  providedIn: 'root',
})
export class UniformServiceService {
  //private baseUrl = 'http://localhost:8080/uniform';
  private baseUrl: string = 'https://uniformix-repository.onrender.com/uniform';

  constructor(private http: HttpClient) {}

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization:
        'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJhdXRoLWFwaSIsInN1YiI6ImpvYW9AdGVzdGUuY29tIiwiZXhwIjoxNzEzNDk3NjMwfQ.hs1iNm3X72juSkXy_5wPstCZZ0LxpEmphnsU4No-nto',
    }),
  };

  getUniforms(pageNumber: Number): Observable<uniformInterface[]> {
    const url = `http://localhost:8080/uniform?page=${pageNumber}`;

    return this.http.get<uniformInterface[]>(
      this.baseUrl + `?page=${pageNumber}`
    );
  }

  generateUniformObject(uniform: uniformInterface): uniformInterface {
    return uniform;
  }

  post(uniform: uniformInterface): Observable<HttpResponse<any>> {
    return this.http
      .post(this.baseUrl, uniform, { observe: 'response' })
      .pipe(map((response: HttpResponse<any>) => response));
  }

  hasEmptyFields(uniform: any): boolean {
    return ['name', 'quantity', 'sex', 'size'].some(
      (field) =>
        uniform[field] == null || uniform[field] === '' || uniform[field] == 0
    );
  }
}
