import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { OAuthService } from 'angular-oauth2-oidc';
import { AuthService } from '../auth';

@Component({
  selector: 'app-auth-callback',
    standalone: true,   // 👈 para que funcione con Angular standalone
  imports: [],
  templateUrl: './auth-callback.html',
  styleUrl: './auth-callback.css',
})
export class AuthCallback implements OnInit {

constructor(
    private route: ActivatedRoute,
    private authService: AuthService,
    private router: Router
  ) {}


  ngOnInit() {
    // 1️⃣ Obtener el 'code' de la URL
    const code = this.route.snapshot.queryParamMap.get('code');

    if (!code) {
      console.error('❌ No se recibió el código de Google');
      return;
    }

    // 2️⃣ Llamar al backend para intercambiar code por token
    this.authService.loginWithCode(code).subscribe({
      next: (res) => {
        console.log('✅ Login exitoso');
        console.log('Token:', res.token);
        console.log('Email:', res.email);
        console.log('Name:', res.name);

        // Guardar token en localStorage
        localStorage.setItem('jwt', res.token);

        // Redirigir a la página principal
      this.router.navigate(['/dashboard']);
      },
      error: (err) => {
        
        console.error('❌ Error autenticando con backend', err);
      },
    });
  }
}
