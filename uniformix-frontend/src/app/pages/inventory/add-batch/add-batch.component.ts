import { Component, OnInit } from '@angular/core';
import { BatchServiceService } from 'src/app/services/batch-service.service';
import { batchInterface } from '../../../interfaces/batchInterface';
import { SupplierServiceService } from 'src/app/services/supplier-service.service';
import { supplierInterface } from 'src/app/interfaces/supplierInterface';
import { CategoryServiceService } from 'src/app/services/category-service.service';
import { categoryInterface } from 'src/app/interfaces/categoryInterface';

@Component({
  selector: 'app-add-batch',
  templateUrl: './add-batch.component.html',
  styleUrls: ['./add-batch.component.css']
})

export class AddBatchComponent implements OnInit {
  suppliers: supplierInterface[] = [];
  categories: categoryInterface[] = [];
  
  sm = { width: '14rem' };
  md = { width: '20rem' };
  lg = { width: '32rem' };


  constructor(
    private batchService: BatchServiceService,
    private supplierService: SupplierServiceService,
    private categoryService: CategoryServiceService) { }

  ngOnInit(): void {
    this.supplierService.getSuppliers()
    .subscribe((data: supplierInterface[]) => {
      this.suppliers = data;
    })

    this.categoryService.getCategories()
    .subscribe((data: categoryInterface[]) => {
      this.categories = data;
    })
  }

  onSubmit(batch: batchInterface): void {
    this.batchService.post(batch).subscribe();
  }
}
