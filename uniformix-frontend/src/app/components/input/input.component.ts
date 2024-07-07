import { Component, Input, OnInit, ElementRef, ViewChild } from '@angular/core';

@Component({
  selector: 'app-input',
  templateUrl: './input.component.html',
  styleUrls: ['./input.component.css']
})
export class InputComponent implements OnInit {
  @ViewChild('inputElement') inputElementRef!: ElementRef<HTMLInputElement>;

  public sm = { width: '14rem' };
  public md = { width: '20rem' };
  public lg = { width: '32rem' };

  public disabledStyle = {
    'background-color': '#EBEBE4'
  };

  @Input() size: object = this.md;
  @Input() placeholder: string = '';
  @Input() isLabel: boolean = false;
  @Input() isDisabled: boolean = false;
  @Input() initValue: string = '';
  @Input() labelText: string = '';
  @Input() type: string = 'text';
  @Input() class: string = '';

  constructor() { }

  ngOnInit(): void {
    if (this.type === 'number') {
      this.inputElementRef.nativeElement.addEventListener('input', () => {
        const value = parseInt(this.inputElementRef.nativeElement.value);
        if (value < 0) {
          this.inputElementRef.nativeElement.value = '0';
        }
      });
    }
  }
}
