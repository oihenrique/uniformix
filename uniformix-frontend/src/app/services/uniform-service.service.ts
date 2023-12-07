import { Injectable } from '@angular/core';
import { uniformInterface } from '../interfaces/uniformInterface';

@Injectable({
  providedIn: 'root'
})
export class UniformServiceService {

  constructor() { }

  generateUniformObject(uniform: uniformInterface): uniformInterface {
    return uniform;
  }
}
