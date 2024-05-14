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

  public post(unit: unitInterface): Observable<unitInterface> {
    const url = 'http://localhost:8080/unit';

    return this.http.post<unitInterface>(url, unit); // colocar o options depois
  }

  public setState(name: string, state: boolean): void {
    const url = `http://localhost:8080/unit/${name}`;

    var body = { 'name': name, 'active': state }

    this.http.patch<unitInterface>(url, body).subscribe();
  }
}
