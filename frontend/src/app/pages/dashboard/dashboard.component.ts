import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent {
  stats = [
    { label: 'Total Documents', value: 24, icon: '📄', color: 'text-primary', change: '+12% this month' },
    { label: 'Pending Signatures', value: 5, icon: '⏳', color: 'text-warning', change: '+2 this week' },
    { label: 'Executed', value: 18, icon: '✅', color: 'text-success', change: '+8 this month' },
    { label: 'Declined', value: 1, icon: '❌', color: 'text-danger', change: '0 this month' }
  ];

  recentDocuments = [
    { name: 'vendor_agreement.pdf', status: 'EXECUTED', date: '2024-01-15' },
    { name: 'nda_2024.pdf', status: 'PENDING', date: '2024-01-14' },
    { name: 'service_contract.pdf', status: 'DECLINED', date: '2024-01-13' },
    { name: 'employment_offer.pdf', status: 'EXECUTED', date: '2024-01-12' },
    { name: 'partnership_agreement.pdf', status: 'PARTIALLY_SIGNED', date: '2024-01-11' }
  ];

  signingActivity = [
    { action: 'vendor_agreement.pdf signed by John', time: '2h ago', color: 'bg-success' },
    { action: 'nda_2024.pdf sent for signing', time: '5h ago', color: 'bg-warning' },
    { action: 'service_contract.pdf declined', time: '1d ago', color: 'bg-danger' },
    { action: 'employment_offer.pdf signed by Sarah', time: '2d ago', color: 'bg-success' },
    { action: 'partnership_agreement.pdf uploaded', time: '3d ago', color: 'bg-primary' }
  ];

  getStatusBadgeClass(status: string): string {
    switch (status) {
      case 'PENDING': return 'bg-warning/20 text-warning';
      case 'EXECUTED': return 'bg-success/20 text-success';
      case 'DECLINED': return 'bg-danger/20 text-danger';
      case 'PARTIALLY_SIGNED': return 'bg-primary/20 text-primary';
      default: return 'bg-text-muted/20 text-text-muted';
    }
  }
}
