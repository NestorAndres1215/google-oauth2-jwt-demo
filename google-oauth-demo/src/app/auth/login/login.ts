import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';



@Component({
  selector: 'app-login',

  imports: [CommonModule], // ✅ Ahora Angular ve el provider
  templateUrl: './login.html',
  styleUrls: ['./login.css'] // corregido
})
export class Login implements OnInit {

  constructor(private authService: AuthService) {}
  ngOnInit(): void {
  
  }

  login() {
    this.authService.login();
  }
}