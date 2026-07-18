import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { Router, RouterLink, RouterLinkActive } from '@angular/router';
import { AuthService } from '../../../core/services/auth.service';
import { LucideAngularModule, LayoutDashboard, FileText, Upload, Shield, Wallet } from 'lucide-angular';

@Component({
  selector: 'app-sidebar',
  standalone: true,
  imports: [CommonModule, RouterLink, RouterLinkActive, LucideAngularModule],
  templateUrl: './sidebar.component.html',
  styleUrl: './sidebar.component.css'
})
export class SidebarComponent {
  userName = '';
  userRole = '';
  walletConnected = false;
  walletAddress = '';

  // Lucide icons
  LayoutDashboard = LayoutDashboard;
  FileText = FileText;
  Upload = Upload;
  Shield = Shield;
  Wallet = Wallet;

  constructor(
    public router: Router,
    private authService: AuthService
  ) {
    this.loadUserData();
  }

  loadUserData(): void {
    const user = this.authService.getCurrentUser();
    if (user) {
      this.userName = user.name || 'User';
      this.userRole = user.role || 'INITIATOR';
    }
  }

  getUserInitials(): string {
    return this.userName.charAt(0).toUpperCase();
  }

  getRoleBadgeClass(): string {
    switch (this.userRole) {
      case 'ADMIN':
        return 'bg-red-500/10 text-red-400';
      case 'INITIATOR':
        return 'bg-indigo-500/10 text-indigo-400';
      case 'SIGNER':
        return 'bg-green-500/10 text-green-400';
      default:
        return 'bg-gray-500/10 text-gray-400';
    }
  }

  connectWallet(): void {
    // TODO: Implement wallet connection
    this.walletConnected = true;
    this.walletAddress = '0x1234567890abcdef1234567890abcdef12345678';
  }

  getTruncatedAddress(): string {
    if (!this.walletAddress) return '';
    return `${this.walletAddress.slice(0, 6)}...${this.walletAddress.slice(-4)}`;
  }

  logout(): void {
    this.authService.logout();
  }
}
