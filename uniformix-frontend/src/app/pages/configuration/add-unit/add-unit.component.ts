import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { AlertTypeEnum } from 'src/app/components/alert/alertType.enum';
import { unitInterface } from 'src/app/interfaces/unitInterface';
import { AlertServiceService } from 'src/app/services/alert-service.service';
import { RouterServiceService } from 'src/app/services/router-service.service';
import { UnitServiceService } from 'src/app/services/unit-service.service';
import { BrazilStates, BrazilStatesRecord } from './brazilStates.enum';


@Component({
  selector: 'app-add-unit',
  templateUrl: './add-unit.component.html',
  styleUrls: ['./add-unit.component.css'],
})
export class AddUnitComponent implements OnInit {
  eyeIcon: string = '../../assets/icons/eye.svg';
  eyeClosedIcon: string = '../../assets/icons/eye-closed.svg';
  
  public sm = { width: '14rem' };
  public md = { width: '20rem' };
  public lg = { width: '32rem' };

  alertTypes = AlertTypeEnum;
  unit$: unitInterface[] = [];
  unitName: string = '';
  selectedUnit: unitInterface | null = null;
  enumKeys: object[] = Object.entries(BrazilStatesRecord).map(([key, name]) => ({ key, name }));

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

  selectUnit(unit: unitInterface) {
    this.selectedUnit = unit;
  }

  async onSubmit(unit: unitInterface): Promise<void> {
    if (unit.name === '' || unit.name === undefined) {
      this.alertService.showAlert(this.alertTypes.error, 'Preencha o campo!');
    } else {
      try {
        unit.state = this.getStateAbbreviation(unit.state);
        await this.unitService.post(unit).toPromise();
        this.alertService.showAlert(
          this.alertTypes.success,
          `Unidade ${this.unitName} Cadastrado!`
        );
        this.routerService.resetPage();
      } catch (error) {
        console.error('Erro ao cadastrar:', error);
        this.alertService.showAlert(
          this.alertTypes.error,
          'Erro ao cadastrar a unidade. Por favor, tente novamente.'
        );
      }
    }
  }

  async setUnitState(state: boolean): Promise<void> {
    try {
      if(this.unitName !== '') {
        await this.unitService.setState(this.unitName, state);
        this.alertService.showAlert(
          this.alertTypes.success,
          `Unidade ${this.unitName} foi atualizada`
        );
      } else {
        this.alertService.showAlert(
          this.alertTypes.info,
          `Selecione uma unidade`
        );
      }
    } catch (error) {
      this.alertService.showAlert(
        this.alertTypes.error,
        `Erro ao atualizar a unidade ${this.unitName}`
      );
    }
  }

  getStateAbbreviation(stateName: string): string {
    for (const [key, value] of Object.entries(BrazilStatesRecord)) {
      if (value === stateName) {
        return key;
      }
    }
    return '';
  }
}
