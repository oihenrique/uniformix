import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-add-batch',
  templateUrl: './add-batch.component.html',
  styleUrls: ['./add-batch.component.css']
})
export class AddBatchComponent implements OnInit {
  sm = {width: '14rem'};
  md = {width: '20rem'};
  lg = {width: '32rem'};
  
  constructor() { }

  ngOnInit(): void {
  }

}
