import { Component, OnInit } from '@angular/core';
import { AlertTypeEnum } from 'src/app/components/alert/alertType.enum';
import { categoryInterface } from 'src/app/interfaces/categoryInterface';
import { AlertServiceService } from 'src/app/services/alert-service.service';
import { CategoryServiceService } from 'src/app/services/category-service.service';
import { RouterServiceService } from 'src/app/services/router-service.service';

@Component({
  selector: 'app-add-category',
  templateUrl: './add-category.component.html',
  styleUrls: ['./add-category.component.css'],
})
export class AddCategoryComponent implements OnInit {
  public sm = { width: '14rem' };
  public md = { width: '20rem' };
  public lg = { width: '32rem' };

  alertTypes = AlertTypeEnum;
  categorie$: categoryInterface[] = [];
  categoryName: string = '';
  selectedCategory: categoryInterface | null = null;

  constructor(
    private categoryService: CategoryServiceService,
    private alertService: AlertServiceService,
    private routerService: RouterServiceService
  ) {}

  ngOnInit(): void {
    this.fetchCategoryData();
  }

  fetchCategoryData(): void {
    this.categoryService.getCategories().subscribe((categories) => {
      this.categorie$ = categories;
    });
  }

  getCategoryName(name: string): void {
    this.categoryName = name;
  }

  selectCategory(category: categoryInterface) {
    this.selectedCategory = category;
  }

  async onSubmit(category: categoryInterface): Promise<void> {
    if (category.name === '' || category.name === undefined) {
      this.alertService.showAlert(this.alertTypes.error, 'Preencha o campo!');
    } else {
      try {
        await this.categoryService.post(category).toPromise();
        this.alertService.showAlert(
          this.alertTypes.success,
          `Categoria ${this.categoryName} cadastrada!`
        );
        this.routerService.resetPage();
      } catch (error) {
        console.error('Erro ao cadastrar:', error);
        this.alertService.showAlert(
          this.alertTypes.error,
          'Erro ao cadastrar a categoria. Por favor, tente novamente.'
        );
      }
    }
  }

  async setCategoryState(state: boolean): Promise<void> {
    try {
      await this.categoryService.setState(this.categoryName, state);
      this.alertService.showAlert(
        this.alertTypes.success,
        `Categoria ${this.categoryName} foi inativada!`
      );
    } catch (error) {
      this.alertService.showAlert(
        this.alertTypes.error,
        `Erro ao inativar a categoria ${this.categoryName}`
      );
    }
  }
}
