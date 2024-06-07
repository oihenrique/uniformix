import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { unitInterface } from '../interfaces/unitInterface';

@Injectable({
  providedIn: 'root'
})
export class UnitServiceService {
  private baseUrl: string = 'https://uniformix-repository.onrender.com/unit'

  constructor(private http: HttpClient) { }

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      Authorization: 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJhdXRoLWFwaSIsInN1YiI6ImpvYW9AdGVzdGUuY29tIiwiZXhwIjoxNzEzNDk3NjMwfQ.hs1iNm3X72juSkXy_5wPstCZZ0LxpEmphnsU4No-nto'
    })
  };

  public getUnitList(): Observable<unitInterface[]> {

    return this.http.get<unitInterface[]>(this.baseUrl);
  }

  public getActiveUnitList(): Observable<unitInterface[]> {

    return this.http.get<unitInterface[]>(this.baseUrl + '/active');
  }

  public post(unit: unitInterface): Observable<unitInterface> {

    return this.http.post<unitInterface>(this.baseUrl, unit); // colocar o options depois
  }

  public setState(name: string, state: boolean): void {

    var body = { 'name': name, 'active': state }

    this.http.patch<unitInterface>(this.baseUrl + `/${name}`, body).subscribe();
  }
}
