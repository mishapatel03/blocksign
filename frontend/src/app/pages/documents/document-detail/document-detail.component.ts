import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-document-detail',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './document-detail.component.html',
  styleUrl: './document-detail.component.css'
})
export class DocumentDetailComponent {
  document = {
    name: 'vendor_agreement.pdf',
    uploadDate: '2024-01-15',
    fileSize: '2.4 MB',
    status: 'EXECUTED',
    hash: 'a1b2c3d4e5f6g7h8i9j0k1l2m3n4o5p6q7r8s9t0',
    transactionHash: '0x4a3b5c7d9e1f2a3b5c7d9e1f2a3b5c7d9e1f2a3b5c7d9e1f2a3b5c7d9e1f2a3b',
    blockNumber: 19823456
  };

  riskAnalysis = {
    level: 'LOW',
    flaggedClauses: [
      { text: 'Indemnification clause', severity: 'LOW' },
      { text: 'Termination terms', severity: 'LOW' }
    ]
  };

  signers = [
    { name: 'John Smith', email: 'john@example.com', status: 'SIGNED', walletAddress: '0x1234...5678', signedDate: '2024-01-15' },
    { name: 'Sarah Johnson', email: 'sarah@example.com', status: 'SIGNED', walletAddress: '0xabcd...efgh', signedDate: '2024-01-15' },
    { name: 'Mike Brown', email: 'mike@example.com', status: 'PENDING', walletAddress: null, signedDate: null }
  ];

  timeline = [
    { event: 'Document uploaded', date: '2024-01-15 10:00 AM', user: 'Misha Patel' },
    { event: 'John Smith signed', date: '2024-01-15 02:30 PM', user: 'John Smith' },
    { event: 'Sarah Johnson signed', date: '2024-01-15 03:45 PM', user: 'Sarah Johnson' }
  ];

  getStatusBadgeClass(status: string): string {
    switch (status) {
      case 'PENDING': return 'bg-warning/20 text-warning';
      case 'SIGNED': return 'bg-success/20 text-success';
      case 'DECLINED': return 'bg-danger/20 text-danger';
      default: return 'bg-text-muted/20 text-text-muted';
    }
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

  copyToClipboard(text: string): void {
    navigator.clipboard.writeText(text);
    alert('Copied to clipboard!');
  }
}
