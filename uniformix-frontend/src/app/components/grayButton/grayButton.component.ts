import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-grayButton',
  templateUrl: './grayButton.component.html',
  styleUrls: ['./grayButton.component.css'],
})
export class GrayButtonComponent implements OnInit {
  @Input() buttonText: string = '';
  @Input() icon: string = '';

  constructor() {}

  ngOnInit(): void {}
}
