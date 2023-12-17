import { Injectable, NgZone } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { alertInterface } from '../interfaces/alertInterface';
import { AlertTypeEnum } from '../components/alert/alertType.enum';

@Injectable({
  providedIn: 'root'
})
export class AlertServiceService {
  private alert$ = new Subject<alertInterface>();
  
  constructor(private ngZone: NgZone) { }

  setAlert(alert: alertInterface): void {
    this.alert$.next(alert);
  }

  getAlert(): Observable<alertInterface> {
    return this.alert$.asObservable();
  }

  showAlert(type: AlertTypeEnum, text: string) {
    this.setAlert({
      type,
      text
    })
  }
}
