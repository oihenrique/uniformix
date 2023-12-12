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
      Authorization: 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJhdXRoLWFwaSIsInN1YiI6ImpvYW9AdGVzdGUuY29tIiwiZXhwIjoxNzAyMzQ1NjUzfQ.VfPqF-W3OonXuiGvPz8PGkI19JLvLF96U75JZL9HJlE'
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
}
