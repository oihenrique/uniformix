import { Injectable } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';


@Injectable({
  providedIn: 'root'
})
export class RouterServiceService {

  constructor(
    private router: Router,
    private route: ActivatedRoute
    ) {}

  redirectToInventoryRoute() {
    this.router.navigate(['/inventory']);
  }
  
  resetPage() {
    this.router.routeReuseStrategy.shouldReuseRoute = () => false;
    this.router.onSameUrlNavigation = 'reload';
    this.router.navigate([this.router.url], {
      relativeTo: this.route
    });
  }
}
