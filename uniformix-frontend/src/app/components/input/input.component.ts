import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-input',
  templateUrl: './input.component.html',
  styleUrls: ['./input.component.css']
})
export class InputComponent implements OnInit {
  sm = {width: '14rem'};
  md = {width: '20rem'};
  lg = {width: '32rem'};

  @Input() size: object = this.md;
  @Input() placeholder: string = '';

  constructor() { }

  ngOnInit(): void {
  }

}
