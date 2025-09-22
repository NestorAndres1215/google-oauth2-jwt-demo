import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AuthRoutingModule } from './auth-routing-module';
import { Login } from '../login/login';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { OAuthModule } from 'angular-oauth2-oidc';


@NgModule({
 
  imports: [
    CommonModule,Login,
    AuthRoutingModule,   BrowserModule,
    HttpClientModule,
    OAuthModule.forRoot()  // 🔹 Esto es obligatorio para PKCE
  ]
})
export class AuthModule { }
