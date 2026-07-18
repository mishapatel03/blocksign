import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { LucideAngularModule, FileText, Clock, CircleCheckBig, CircleX } from 'lucide-angular';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule, LucideAngularModule],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent {
  // Lucide icon data
  FileText = FileText;
  Clock = Clock;
  CircleCheckBig = CircleCheckBig;
  CircleX = CircleX;

  stats = [
    { 
      label: 'Total Documents', 
      value: 24, 
      iconName: 'file-text', 
      colorClass: 'bg-indigo-500/10 text-indigo-400',
      change: '+12% this month',
      route: ['/documents']
    },
    { 
      label: 'Pending Signatures', 
      value: 5, 
      iconName: 'clock', 
      colorClass: 'bg-amber-500/10 text-amber-400',
      change: '+2 this week',
      route: ['/documents', { filter: 'pending' }]
    },
    { 
      label: 'Executed', 
      value: 18, 
      iconName: 'check-circle', 
      colorClass: 'bg-green-500/10 text-green-400',
      change: '+8 this month',
      route: ['/documents', { filter: 'executed' }]
    },
    { 
      label: 'Declined', 
      value: 1, 
      iconName: 'x-circle', 
      colorClass: 'bg-red-500/10 text-red-400',
      change: '0 this month',
      route: ['/documents', { filter: 'declined' }]
    }
  ];

  recentDocuments = [
    { name: 'vendor_agreement.pdf', status: 'EXECUTED', date: '2024-01-15' },
    { name: 'nda_2024.pdf', status: 'PENDING', date: '2024-01-14' },
    { name: 'service_contract.pdf', status: 'DECLINED', date: '2024-01-13' },
    { name: 'employment_offer.pdf', status: 'EXECUTED', date: '2024-01-12' },
    { name: 'partnership_agreement.pdf', status: 'PARTIALLY_SIGNED', date: '2024-01-11' }
  ];

  signingActivity = [
    { action: 'vendor_agreement.pdf signed by John', time: '2h ago', status: 'signed' },
    { action: 'nda_2024.pdf sent for signing', time: '5h ago', status: 'pending' },
    { action: 'service_contract.pdf declined', time: '1d ago', status: 'declined' },
    { action: 'employment_offer.pdf signed by Sarah', time: '2d ago', status: 'signed' },
    { action: 'partnership_agreement.pdf uploaded', time: '3d ago', status: 'uploaded' }
  ];

  constructor(private router: Router) {}

  getIcon(iconName: string) {
    switch (iconName) {
      case 'file-text': return this.FileText;
      case 'clock': return this.Clock;
      case 'check-circle': return this.CircleCheckBig;
      case 'x-circle': return this.CircleX;
      default: return this.FileText;
    }
  }

  navigateTo(route: any[]): void {
    this.router.navigate(route);
  }

  getStatusBadgeClass(status: string): string {
    switch (status) {
      case 'PENDING': 
        return 'bg-amber-500/10 text-amber-400 border border-amber-500/20';
      case 'EXECUTED': 
        return 'bg-green-500/10 text-green-400 border border-green-500/20';
      case 'DECLINED': 
        return 'bg-red-500/10 text-red-400 border border-red-500/20';
      case 'PARTIALLY_SIGNED': 
        return 'bg-indigo-500/10 text-indigo-400 border border-indigo-500/20';
      default: 
        return 'bg-text-muted/10 text-text-muted border border-text-muted/20';
    }
  }

  getActivityDotColor(status: string): string {
    switch (status) {
      case 'signed': return 'bg-green-400';
      case 'pending': return 'bg-amber-400';
      case 'declined': return 'bg-red-400';
      case 'uploaded': return 'bg-indigo-400';
      default: return 'bg-gray-400';
    }
  }
}
