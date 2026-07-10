import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-document-list',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './document-list.component.html',
  styleUrl: './document-list.component.css'
})
export class DocumentListComponent {
  searchTerm = '';
  filterStatus = 'ALL';
  sortBy = 'NEWEST';

  documents = [
    { id: 1, name: 'vendor_agreement.pdf', status: 'EXECUTED', progress: 4, totalSigners: 4, signers: ['J', 'S', 'M', 'K'], date: '2024-01-15' },
    { id: 2, name: 'nda_2024.pdf', status: 'PENDING', progress: 2, totalSigners: 4, signers: ['J', 'S'], date: '2024-01-14' },
    { id: 3, name: 'service_contract.pdf', status: 'DECLINED', progress: 1, totalSigners: 3, signers: ['J'], date: '2024-01-13' },
    { id: 4, name: 'employment_offer.pdf', status: 'EXECUTED', progress: 3, totalSigners: 3, signers: ['S', 'M', 'K'], date: '2024-01-12' },
    { id: 5, name: 'partnership_agreement.pdf', status: 'PARTIALLY_SIGNED', progress: 3, totalSigners: 4, signers: ['J', 'S', 'M'], date: '2024-01-11' },
    { id: 6, name: 'lease_agreement.pdf', status: 'PENDING', progress: 1, totalSigners: 2, signers: ['J'], date: '2024-01-10' }
  ];

  constructor(private router: Router) {}

  getStatusBadgeClass(status: string): string {
    switch (status) {
      case 'PENDING': return 'bg-warning/20 text-warning';
      case 'EXECUTED': return 'bg-success/20 text-success';
      case 'DECLINED': return 'bg-danger/20 text-danger';
      case 'PARTIALLY_SIGNED': return 'bg-primary/20 text-primary';
      default: return 'bg-text-muted/20 text-text-muted';
    }
  }

  getSignerColor(index: number): string {
    const colors = ['bg-primary', 'bg-success', 'bg-warning', 'bg-danger'];
    return colors[index % colors.length];
  }

  getProgressPercent(doc: any): number {
    return Math.round((doc.progress / doc.totalSigners) * 100);
  }

  navigateToUpload(): void {
    this.router.navigate(['/documents/upload']);
  }

  navigateToDetail(id: number): void {
    this.router.navigate(['/documents', id]);
  }
}
