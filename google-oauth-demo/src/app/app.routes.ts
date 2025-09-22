import { Routes } from '@angular/router';
import { Login } from './auth/login/login';
import { AuthCallback } from './auth/auth-callback/auth-callback';
import { Dashboard } from './auth/dashboard/dashboard';



export const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'login', component: Login }, // standalone component
  { path: 'auth-callback', component: AuthCallback }, // 🔹 Agregar esto
  { path: 'dashboard', component: Dashboard }, // 🔹 Agregar esto

];
