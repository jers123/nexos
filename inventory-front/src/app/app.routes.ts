import { Routes } from '@angular/router';
import { User } from './view/user/user/user';
import { Position } from './view/position/position/position';
import { Product } from './view/product/product/product';

export const routes: Routes = [
  { path: 'position', component: Position },
  { path: 'product', component: Product },
  { path: 'user', component: User },
  { path: '', redirectTo: 'product', pathMatch: 'full' }
];
