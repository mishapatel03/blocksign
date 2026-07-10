import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  constructor() {}

  login(email: string, password: string): Observable<any> {
    // Stub implementation - will be implemented later
    return of({ token: 'mock-token', user: { email, name: 'Misha Patel' } });
  }

  register(email: string, password: string, name: string): Observable<any> {
    // Stub implementation - will be implemented later
    return of({ token: 'mock-token', user: { email, name } });
  }

  logout(): void {
    localStorage.removeItem('auth_token');
  }

  isAuthenticated(): boolean {
    return !!localStorage.getItem('auth_token');
  }
}
