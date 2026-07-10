import { Routes } from '@angular/router';
import { LoginComponent } from './pages/auth/login/login.component';
import { RegisterComponent } from './pages/auth/register/register.component';
import { MainLayoutComponent } from './layout/main-layout.component';
import { authGuard } from './core/guards/auth.guard';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { DocumentListComponent } from './pages/documents/document-list/document-list.component';
import { DocumentUploadComponent } from './pages/documents/document-upload/document-upload.component';
import { DocumentDetailComponent } from './pages/documents/document-detail/document-detail.component';
import { SignDocumentComponent } from './pages/sign/sign-document/sign-document.component';
import { AuditTrailComponent } from './pages/audit/audit-trail/audit-trail.component';

export const routes: Routes = [
  // Public routes (no auth needed)
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },

  // Protected routes (auth guard applied)
  {
    path: '',
    component: MainLayoutComponent,
    canActivate: [authGuard],
    children: [
      { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
      { path: 'dashboard', component: DashboardComponent },
      { path: 'documents', component: DocumentListComponent },
      { path: 'documents/upload', component: DocumentUploadComponent },
      { path: 'documents/:id', component: DocumentDetailComponent },
      { path: 'documents/:id/sign', component: SignDocumentComponent },
      { path: 'audit/:contractId', component: AuditTrailComponent },
    ]
  },

  // Fallback
  { path: '**', redirectTo: 'login' }
];
