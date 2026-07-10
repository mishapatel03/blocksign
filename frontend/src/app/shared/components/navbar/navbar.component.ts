import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {
  walletConnected = false;
  walletAddress = '0xAbCd...1234';
  pageTitle = 'Dashboard';
  breadcrumb = 'BlockSign / Dashboard';

  constructor(private router: Router) {}

  toggleWallet(): void {
    this.walletConnected = !this.walletConnected;
  }

  logout(): void {
    localStorage.removeItem('auth_token');
    this.router.navigate(['/login']);
  }
}
