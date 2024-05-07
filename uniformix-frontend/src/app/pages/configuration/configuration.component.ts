import { Component, OnInit } from '@angular/core';
import { categoryInterface } from 'src/app/interfaces/categoryInterface';
import { supplierInterface } from 'src/app/interfaces/supplierInterface';
import { unitInterface } from 'src/app/interfaces/unitInterface';
import { CategoryServiceService } from 'src/app/services/category-service.service';
import { SupplierServiceService } from 'src/app/services/supplier-service.service';
import { UniformServiceService } from 'src/app/services/uniform-service.service';
import { UnitServiceService } from 'src/app/services/unit-service.service';

@Component({
  selector: 'app-configuration',
  templateUrl: './configuration.component.html',
  styleUrls: ['./configuration.component.css']
})
export class ConfigurationComponent implements OnInit {

  categorie$: categoryInterface[] = [];
  supplier$: supplierInterface[] = [];
  unit$: unitInterface[] = [];
  
  constructor(
    private categoryService: CategoryServiceService,
    private supplierService: SupplierServiceService,
    private unitService: UnitServiceService
  ) { }

  ngOnInit(): void {
    this.fetchCategoryData();
    this.fetchSupplierData();
    this.fetchUnitData();
  }

  fetchCategoryData(): void {
    this.categoryService.getCategories().subscribe((categories => {
      this.categorie$ = categories;
    }));
  }

  fetchSupplierData(): void {
    this.supplierService.getSuppliers().subscribe((suppliers) => {
      this.supplier$ = suppliers;
    })
  }

  fetchUnitData(): void {
    this.unitService.getUnitList().subscribe((units) => {
      this.unit$ = units;
    })
  }
}
