import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
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
  activeFilter = 'all';
  sortBy = 'NEWEST';

  allDocuments = [
    { id: 1, name: 'vendor_agreement.pdf', status: 'EXECUTED', progress: 4, totalSigners: 4, signers: ['J', 'S', 'M', 'K'], date: '2024-01-15' },
    { id: 2, name: 'nda_2024.pdf', status: 'PENDING', progress: 2, totalSigners: 4, signers: ['J', 'S'], date: '2024-01-14' },
    { id: 3, name: 'service_contract.pdf', status: 'DECLINED', progress: 1, totalSigners: 3, signers: ['J'], date: '2024-01-13' },
    { id: 4, name: 'employment_offer.pdf', status: 'EXECUTED', progress: 3, totalSigners: 3, signers: ['S', 'M', 'K'], date: '2024-01-12' },
    { id: 5, name: 'partnership_agreement.pdf', status: 'PARTIALLY_SIGNED', progress: 3, totalSigners: 4, signers: ['J', 'S', 'M'], date: '2024-01-11' },
    { id: 6, name: 'lease_agreement.pdf', status: 'PENDING', progress: 1, totalSigners: 2, signers: ['J'], date: '2024-01-10' }
  ];

  filteredDocuments = [...this.allDocuments];

  filters = ['all', 'pending', 'executed', 'declined', 'partially_signed'];

  constructor(private router: Router, private route: ActivatedRoute) {
    this.route.queryParams.subscribe(params => {
      this.activeFilter = params['filter'] || 'all';
      this.filterDocuments();
    });
  }

  filterDocuments(): void {
    if (this.activeFilter === 'all') {
      this.filteredDocuments = [...this.allDocuments];
    } else {
      this.filteredDocuments = this.allDocuments.filter(doc => 
        doc.status.toLowerCase() === this.activeFilter.replace('_', ' ')
      );
    }
  }

  setFilter(filter: string): void {
    this.activeFilter = filter;
    this.filterDocuments();
    this.router.navigate([], { queryParams: { filter: filter } });
  }

  clearFilter(): void {
    this.activeFilter = 'all';
    this.filterDocuments();
    this.router.navigate([], { queryParams: {} });
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
