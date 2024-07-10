import { Routes } from '@angular/router';
import { LoginPageComponent } from './pages/login-page/login-page.component';
import { RegisterPageComponent } from './pages/register-page/register-page.component';

export const routes: Routes = [
  // Redireciona quando a url for vazia para o login e depois associa o login ao componente
  {
    path: '',
    redirectTo: '/login',
    pathMatch: 'full',
  },
  {
    path: 'login',
    component: LoginPageComponent,
  },
  // Associa o register ao componente
  {
    path: 'register',
    component: RegisterPageComponent,
  },
];
