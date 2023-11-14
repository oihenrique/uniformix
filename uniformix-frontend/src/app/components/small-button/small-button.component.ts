import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-small-button',
  templateUrl: './small-button.component.html',
  styleUrls: ['./small-button.component.css'],
})
export class SmallButtonComponent implements OnInit {
  @Input() buttonText: string = '';

  constructor() {}

  ngOnInit(): void {}
}
