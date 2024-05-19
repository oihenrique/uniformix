import { Component, OnInit } from '@angular/core';
import { transactionInterface } from 'src/app/interfaces/transactionInterface';
import { uniformInterface } from 'src/app/interfaces/uniformInterface';
import { unitInterface } from 'src/app/interfaces/unitInterface';
import { AlertServiceService } from 'src/app/services/alert-service.service';
import { TransactionServiceService } from 'src/app/services/transaction-service.service';
import { UniformServiceService } from 'src/app/services/uniform-service.service';
import { UnitServiceService } from 'src/app/services/unit-service.service';
import { AlertTypeEnum } from 'src/app/components/alert/alertType.enum';
import { RouterServiceService } from 'src/app/services/router-service.service';

@Component({
  selector: 'app-transaction',
  templateUrl: './transaction.component.html',
  styleUrls: ['./transaction.component.css'],
})
export class TransactionComponent implements OnInit {
  addIcon = '../../assets/icons/Add icon.svg';
  removeIcon = '../../assets/icons/Remove button.svg';
  alertTypes = AlertTypeEnum;

  public sm = { width: '14rem' };
  public md = { width: '20rem' };
  public lg = { width: '32rem' };

  columns: Array<keyof uniformInterface> = ['name', 'quantity', 'sex', 'size'];
  uniform$: Array<uniformInterface> = [];
  unitList: Array<unitInterface> = [];
  uniformName: string = '';
  operationType: string = '';
  tablePageNumber$: number = 0;

  constructor(
    private uniformService: UniformServiceService,
    private unitService: UnitServiceService,
    private transactionService: TransactionServiceService,
    private alertService: AlertServiceService,
    private routerService: RouterServiceService
  ) {}

  ngOnInit(): void {
    this.fetchUniformData();
    this.fetchUnitData();
  }

  fetchUniformData(next: boolean = false, prev: boolean = false): void {
    if (next) {
      this.tablePageNumber$ += 1;
    } else if (prev && this.tablePageNumber$ > 0) {
      this.tablePageNumber$ -= 1;
    }

    this.uniformService.getUniforms(this.tablePageNumber$).subscribe((uniform) => {
      this.uniform$ = uniform;
    });
  }

  fetchUnitData(): void {
    this.unitService.getUnitList().subscribe((unit) => {
      this.unitList = unit;
    });
  }

  getUniformName(name: string) {
    this.uniformName = name;
  }

  async onSubmitTransaction(transaction: transactionInterface): Promise<void> {
    try {
      transaction.uniform = this.uniformName;
      transaction.user = "joao@teste.com";
      transaction.operationType = this.operationType;

      await this.transactionService.post(transaction).subscribe();
      this.alertService.showAlert(
        this.alertTypes.success,
        'Transação realizada!'
      );

      setTimeout(() => {
        this.routerService.resetPage();
      }, 1500);

    } catch (error) {
      console.error(error);
      this.alertService.showAlert(
        this.alertTypes.error,
        'Preencha todos os campos!'
      );
    }
  }
}
