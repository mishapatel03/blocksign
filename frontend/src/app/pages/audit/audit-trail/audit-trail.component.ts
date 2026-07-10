import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-audit-trail',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './audit-trail.component.html',
  styleUrl: './audit-trail.component.css'
})
export class AuditTrailComponent {
  contractIdSearch = '';
  dateRange = '';
  eventTypeFilter = 'ALL';

  auditEvents = [
    {
      id: 1,
      type: 'DOCUMENT_REGISTERED',
      documentName: 'vendor_agreement.pdf',
      action: 'Document registered on blockchain',
      performedBy: 'Misha Patel',
      walletAddress: '0x1234...5678',
      timestamp: '2024-01-15 10:00:00',
      kafkaEventId: 'evt_001234567890'
    },
    {
      id: 2,
      type: 'DOCUMENT_SIGNED',
      documentName: 'vendor_agreement.pdf',
      action: 'Document signed by John Smith',
      performedBy: 'John Smith',
      walletAddress: '0xabcd...efgh',
      timestamp: '2024-01-15 14:30:00',
      kafkaEventId: 'evt_001234567891'
    },
    {
      id: 3,
      type: 'DOCUMENT_SIGNED',
      documentName: 'vendor_agreement.pdf',
      action: 'Document signed by Sarah Johnson',
      performedBy: 'Sarah Johnson',
      walletAddress: '0x9876...5432',
      timestamp: '2024-01-15 15:45:00',
      kafkaEventId: 'evt_001234567892'
    },
    {
      id: 4,
      type: 'CONTRACT_EXECUTED',
      documentName: 'vendor_agreement.pdf',
      action: 'Contract executed - all signatures collected',
      performedBy: 'System',
      walletAddress: '0x0000...0000',
      timestamp: '2024-01-15 16:00:00',
      kafkaEventId: 'evt_001234567893'
    },
    {
      id: 5,
      type: 'DOCUMENT_DECLINED',
      documentName: 'service_contract.pdf',
      action: 'Document declined by Mike Brown',
      performedBy: 'Mike Brown',
      walletAddress: '0x5555...6666',
      timestamp: '2024-01-14 11:20:00',
      kafkaEventId: 'evt_001234567894'
    },
    {
      id: 6,
      type: 'DOCUMENT_REGISTERED',
      documentName: 'nda_2024.pdf',
      action: 'Document registered on blockchain',
      performedBy: 'Misha Patel',
      walletAddress: '0x1234...5678',
      timestamp: '2024-01-14 09:00:00',
      kafkaEventId: 'evt_001234567895'
    },
    {
      id: 7,
      type: 'DOCUMENT_SIGNED',
      documentName: 'nda_2024.pdf',
      action: 'Document signed by Alice Williams',
      performedBy: 'Alice Williams',
      walletAddress: '0xaaaa...bbbb',
      timestamp: '2024-01-14 13:15:00',
      kafkaEventId: 'evt_001234567896'
    },
    {
      id: 8,
      type: 'DOCUMENT_SIGNED',
      documentName: 'employment_offer.pdf',
      action: 'Document signed by David Lee',
      performedBy: 'David Lee',
      walletAddress: '0xcccc...dddd',
      timestamp: '2024-01-13 10:30:00',
      kafkaEventId: 'evt_001234567897'
    }
  ];

  getEventBadgeClass(type: string): string {
    switch (type) {
      case 'DOCUMENT_REGISTERED': return 'bg-primary/20 text-primary';
      case 'DOCUMENT_SIGNED': return 'bg-success/20 text-success';
      case 'DOCUMENT_DECLINED': return 'bg-danger/20 text-danger';
      case 'CONTRACT_EXECUTED': return 'bg-warning/20 text-warning';
      default: return 'bg-text-muted/20 text-text-muted';
    }
  }

  getEventBorderClass(type: string): string {
    switch (type) {
      case 'DOCUMENT_REGISTERED': return 'border-l-4 border-indigo-500';
      case 'DOCUMENT_SIGNED': return 'border-l-4 border-green-500';
      case 'DOCUMENT_DECLINED': return 'border-l-4 border-red-500';
      case 'CONTRACT_EXECUTED': return 'border-l-4 border-yellow-500';
      default: return 'border-l-4 border-gray-500';
    }
  }

  formatEventType(type: string): string {
    return type.replace(/_/g, ' ');
  }
}
