import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-sign-document',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './sign-document.component.html',
  styleUrl: './sign-document.component.css'
})
export class SignDocumentComponent {
  walletConnected = false;
  walletAddress = '0xAbCd...1234';
  showConfirmModal = false;
  showDeclineModal = false;
  declineReason = '';

  document = {
    name: 'vendor_agreement.pdf',
    sender: 'Misha Patel',
    hash: 'a1b2c3d4e5f6g7h8i9j0k1l2m3n4o5p6q7r8s9t0'
  };

  riskAnalysis = {
    level: 'LOW',
    flaggedClauses: [
      { text: 'Indemnification clause may limit liability', severity: 'LOW' },
      { text: 'Auto-renewal clause requires 30-day notice', severity: 'LOW' }
    ]
  };

  constructor(private router: Router) {}

  toggleWallet(): void {
    this.walletConnected = !this.walletConnected;
  }

  openConfirmModal(): void {
    this.showConfirmModal = true;
  }

  closeConfirmModal(): void {
    this.showConfirmModal = false;
  }

  confirmSign(): void {
    this.showConfirmModal = false;
    alert('Signature submitted successfully!');
    this.router.navigate(['/documents']);
  }

  openDeclineModal(): void {
    this.showDeclineModal = true;
  }

  closeDeclineModal(): void {
    this.showDeclineModal = false;
    this.declineReason = '';
  }

  confirmDecline(): void {
    this.showDeclineModal = false;
    alert('Document declined successfully!');
    this.router.navigate(['/documents']);
  }

  getRiskBadgeClass(level: string): string {
    switch (level) {
      case 'LOW': return 'bg-success/20 text-success';
      case 'MEDIUM': return 'bg-warning/20 text-warning';
      case 'HIGH': return 'bg-danger/20 text-danger';
      default: return 'bg-text-muted/20 text-text-muted';
    }
  }

  getSeverityClass(severity: string): string {
    switch (severity) {
      case 'LOW': return 'bg-success/20 text-success';
      case 'MEDIUM': return 'bg-warning/20 text-warning';
      case 'HIGH': return 'bg-danger/20 text-danger';
      default: return 'bg-text-muted/20 text-text-muted';
    }
  }
}
