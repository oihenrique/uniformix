import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-orangeButton',
  templateUrl: './orangeButton.component.html',
  styleUrls: ['./orangeButton.component.css'],
})
export class OrangeButtonComponent implements OnInit {
  @Input() buttonText: string = '';
  @Input() icon: string = '';
  @Input() type: string = 'button';

  constructor() {}
  ngOnInit(): void {}
}
