import { Component, OnInit } from '@angular/core';
import { AlertTypeEnum } from 'src/app/components/alert/alertType.enum';
import { supplierInterface } from 'src/app/interfaces/supplierInterface';
import { AlertServiceService } from 'src/app/services/alert-service.service';
import { RouterServiceService } from 'src/app/services/router-service.service';
import { SupplierServiceService } from 'src/app/services/supplier-service.service';

@Component({
  selector: 'app-add-supplier',
  templateUrl: './add-supplier.component.html',
  styleUrls: ['./add-supplier.component.css'],
})
export class AddSupplierComponent implements OnInit {
  public sm = { width: '14rem' };
  public md = { width: '20rem' };
  public lg = { width: '32rem' };

  alertTypes = AlertTypeEnum;
  supplier$: supplierInterface[] = [];
  supplierName: string = '';

  constructor(
    private supplierService: SupplierServiceService,
    private alertService: AlertServiceService,
    private routerService: RouterServiceService
  ) {}

  ngOnInit(): void {
    this.fetchSuppliertData();
  }

  fetchSuppliertData(): void {
    this.supplierService.getSuppliers().subscribe((suppliers) => {
      this.supplier$ = suppliers;
    });
  }

  getSupplierName(name: string): void {
    this.supplierName = name;
  }

  async onSubmit(supplier: supplierInterface): Promise<void> {
    if (supplier.name === '' || supplier.name === undefined) {
      this.alertService.showAlert(this.alertTypes.error, 'Preencha o campo!');
    } else {
      try {
        await this.supplierService.post(supplier).toPromise();
        this.alertService.showAlert(
          this.alertTypes.success,
          `Fornecedor ${this.supplierName} Cadastrado!`
        );
        this.routerService.resetPage();
      } catch (error) {
        console.error('Erro ao cadastrar:', error);
        this.alertService.showAlert(
          this.alertTypes.error,
          'Erro ao cadastrar o fornecedor. Por favor, tente novamente.'
        );
      }
    }
  }

  async setSupplierState(state: boolean): Promise<void> {
    try {
      await this.supplierService.setState(this.supplierName, state);
      this.alertService.showAlert(
        this.alertTypes.success,
        `Fornecedor ${this.supplierName} foi inativado!`
      );
    } catch (error) {
      this.alertService.showAlert(
        this.alertTypes.error,
        `Erro ao inativar o fornecedor ${this.supplierName}`
      );
    }
  }
}
