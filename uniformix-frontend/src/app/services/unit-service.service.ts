import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { unitInterface } from '../interfaces/unitInterface';

@Injectable({
  providedIn: 'root'
})
export class UnitServiceService {

  constructor(private http: HttpClient) { }

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      Authorization: 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJhdXRoLWFwaSIsInN1YiI6ImpvYW9AdGVzdGUuY29tIiwiZXhwIjoxNzEzNDk3NjMwfQ.hs1iNm3X72juSkXy_5wPstCZZ0LxpEmphnsU4No-nto'
    })
  };

  public getUnitList(): Observable<unitInterface[]> {
    const url = 'http://localhost:8080/unit';

    return this.http.get<unitInterface[]>(url);
  }
}
