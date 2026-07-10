import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DocumentService {
  constructor() {}

  getDocuments(): Observable<any[]> {
    // Stub implementation - will be implemented later
    return of([]);
  }

  getDocumentById(id: string): Observable<any> {
    // Stub implementation - will be implemented later
    return of({});
  }

  uploadDocument(file: File, signers: any[]): Observable<any> {
    // Stub implementation - will be implemented later
    return of({});
  }

  signDocument(documentId: string, signature: any): Observable<any> {
    // Stub implementation - will be implemented later
    return of({});
  }

  getAuditTrail(contractId: string): Observable<any[]> {
    // Stub implementation - will be implemented later
    return of([]);
  }
}
