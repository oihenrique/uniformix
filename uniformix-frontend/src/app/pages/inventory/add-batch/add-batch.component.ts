import { Component, OnInit } from '@angular/core';
import { BatchServiceService } from 'src/app/services/batch-service.service';
import { batchInterface } from '../../../interfaces/batchInterface';
import { SupplierServiceService } from 'src/app/services/supplier-service.service';
import { supplierInterface } from 'src/app/interfaces/supplierInterface';
import { CategoryServiceService } from 'src/app/services/category-service.service';
import { categoryInterface } from 'src/app/interfaces/categoryInterface';
import { UniformServiceService } from 'src/app/services/uniform-service.service';
import { uniformInterface } from 'src/app/interfaces/uniformInterface';
import { RouterServiceService } from 'src/app/services/router-service.service';
import { AlertServiceService } from 'src/app/services/alert-service.service';
import { AlertTypeEnum } from 'src/app/components/alert/alertType.enum';

@Component({
  selector: 'app-add-batch',
  templateUrl: './add-batch.component.html',
  styleUrls: ['./add-batch.component.css'],
})
export class AddBatchComponent implements OnInit {
  suppliers: supplierInterface[] = [];
  categories: categoryInterface[] = [];
  alertTypes = AlertTypeEnum;
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

  columnNames: { [key in keyof uniformInterface]: string } = {
    name: 'Nome',
    quantity: 'Quant.',
    sex: 'Gênero',
    size: 'Tam.',
  };

  sm = { width: '14rem' };
  md = { width: '20rem' };
  lg = { width: '32rem' };

  constructor(
    private batchService: BatchServiceService,
    private supplierService: SupplierServiceService,
    private categoryService: CategoryServiceService,
    private uniformService: UniformServiceService,
    private routerService: RouterServiceService,
    private alertService: AlertServiceService
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
    batch.uniform = this.uniformStack;
    batch.quantity = Number(this.calculateTotalQuantity());

    if (!this.batchService.hasEmptyFields(batch)) {
      this.batchService.post(batch).subscribe();
      this.alertService.showAlert(this.alertTypes.success, 'Lote Cadastrado!');

      setTimeout(() => {
        this.routerService.redirectToInventoryRoute();
      }, 1500);
      
    } else {
      this.alertService.showAlert(
        this.alertTypes.error,
        'Preencha todos os campos!'
      );
    }
  }

  onSubmitUniform(uniform: uniformInterface): void {
    if (!this.uniformService.hasEmptyFields(uniform)) {
      const data = this.uniformService.generateUniformObject(uniform);
      this.uniformStack.push(data);

      const batchQuantityInput = document.getElementById(
        'batch-quantity-input'
      ) as HTMLInputElement;
      batchQuantityInput.value = this.calculateTotalQuantity().toString();
    } else {
      this.alertService.showAlert(
        this.alertTypes.error,
        'Preencha todos os campos!'
      );
    }
  }

  calculateTotalQuantity(): string {
    return this.uniformStack
      .reduce((total, uniform) => total + Number(uniform.quantity), 0)
      .toString();
  }
}
