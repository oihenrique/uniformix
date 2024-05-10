import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { AlertTypeEnum } from 'src/app/components/alert/alertType.enum';
import { unitInterface } from 'src/app/interfaces/unitInterface';
import { AlertServiceService } from 'src/app/services/alert-service.service';
import { RouterServiceService } from 'src/app/services/router-service.service';
import { UnitServiceService } from 'src/app/services/unit-service.service';

@Component({
  selector: 'app-add-unit',
  templateUrl: './add-unit.component.html',
  styleUrls: ['./add-unit.component.css'],
})
export class AddUnitComponent implements OnInit {
  public sm = { width: '14rem' };
  public md = { width: '20rem' };
  public lg = { width: '32rem' };

  alertTypes = AlertTypeEnum;
  unit$: unitInterface[] = [];
  unitName: string = "";

  constructor(
    private unitService: UnitServiceService,
    private alertService: AlertServiceService,
    private routerService: RouterServiceService
  ) {}

  ngOnInit(): void {
    this.fetchUnitData();
  }

  fetchUnitData(): void {
    this.unitService.getUnitList().subscribe((units) => {
      this.unit$ = units;
    });
  }

  getUnitName(name: string): void {
    this.unitName = name;
  }

  async onSubmit(unit: unitInterface): Promise<void> {
    if (unit.name === '' || unit.name === undefined) {
      this.alertService.showAlert(this.alertTypes.error, 'Preencha o campo!');
    } else {
      try {
        await this.unitService.post(unit).toPromise();
        this.alertService.showAlert(this.alertTypes.success, 'Lote Cadastrado!');
        this.routerService.resetPage();
      } catch (error) {
        console.error('Erro ao cadastrar:', error);
        this.alertService.showAlert(this.alertTypes.error, 'Erro ao cadastrar o lote. Por favor, tente novamente.');
      }
    }
  }

  async inativateUnit(): Promise<void> {
    try {
      await this.unitService.inativate(this.unitName);
      this.alertService.showAlert(this.alertTypes.success, `Unidade ${this.unitName} foi inativada!`);
    } catch (error) {
      this.alertService.showAlert(this.alertTypes.error, `Erro ao inativar a unidade ${this.unitName}`);
    }
  }
}