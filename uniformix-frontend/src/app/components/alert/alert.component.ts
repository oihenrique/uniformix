import { Component, OnInit } from '@angular/core';
import { alertInterface } from 'src/app/interfaces/alertInterface';
import { AlertServiceService } from 'src/app/services/alert-service.service';

@Component({
  selector: 'app-alert',
  templateUrl: './alert.component.html',
  styleUrls: ['./alert.component.css'],
})
export class AlertComponent implements OnInit {
  alert?: alertInterface;
  timoutId?: number;

  constructor(private alertService: AlertServiceService) {}

  ngOnInit(): void {
    this.alertService.getAlert().subscribe((alert) => {
      this.alert = alert;
      this.resetTimer();
    });
  }

  resetTimer(): void {
    if (this.timoutId) {
      window.clearTimeout(this.timoutId);
    }
    this.timoutId = window.setTimeout(() => {
      this.alert = undefined;
    }, 3000);
  }
}
