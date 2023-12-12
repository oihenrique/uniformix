import { Component, OnInit } from '@angular/core';
import { BatchServiceService } from 'src/app/services/batch-service.service';
import { batchInterface } from '../../../interfaces/batchInterface';
import { SupplierServiceService } from 'src/app/services/supplier-service.service';
import { supplierInterface } from 'src/app/interfaces/supplierInterface';
import { CategoryServiceService } from 'src/app/services/category-service.service';
import { categoryInterface } from 'src/app/interfaces/categoryInterface';
import { UniformServiceService } from 'src/app/services/uniform-service.service';
import { uniformInterface } from 'src/app/interfaces/uniformInterface';

@Component({
  selector: 'app-add-batch',
  templateUrl: './add-batch.component.html',
  styleUrls: ['./add-batch.component.css'],
})
export class AddBatchComponent implements OnInit {
  suppliers: supplierInterface[] = [];
  categories: categoryInterface[] = [];
  sex: object[] = [{ name: 'M' }, { name: 'F' }, { name: 'Unisex' }];

  size: object[] = [
    { name: 'PP' },
    { name: 'P' },
    { name: 'P BL' },
    { name: 'M' },
    { name: 'M BL' },
    { name: 'G' },
    { name: 'G BL' },
    { name: 'GG' },
    { name: 'XGG' },
  ];

  uniformStack: Array<uniformInterface> = [];
  info: Array<uniformInterface> = [];
  columns: Array<keyof uniformInterface> = ['name', 'quantity', 'sex', 'size'];
  
  sm = { width: '14rem' };
  md = { width: '20rem' };
  lg = { width: '32rem' };

  constructor(
    private batchService: BatchServiceService,
    private supplierService: SupplierServiceService,
    private categoryService: CategoryServiceService,
    private uniformService: UniformServiceService
  ) {}

  ngOnInit(): void {
    this.supplierService
      .getSuppliers()
      .subscribe((data: supplierInterface[]) => {
        this.suppliers = data;
      });

    this.categoryService
      .getCategories()
      .subscribe((data: categoryInterface[]) => {
        this.categories = data;
      });
  }
  
  onSubmit(batch: batchInterface): void {
    batch.uniforms = this.uniformStack;
    this.batchService.post(batch).subscribe();
    console.log(batch);
  }
  
  

  onSubmitUniform(uniform: uniformInterface): void {
    const data = this.uniformService.generateUniformObject(uniform);
    this.uniformStack.push(data);
  }
}
