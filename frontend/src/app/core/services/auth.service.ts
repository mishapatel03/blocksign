import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap, BehaviorSubject } from 'rxjs';
import { Router } from '@angular/router';
import { environment } from '../../../environments/environment';

interface AuthResponse {
  token: string;
  type: string;
  userId: number;
  email: string;
  name: string;
  tenantId: string;
}

interface LoginRequest {  
  email: string;
  password: string;
}

interface RegisterRequest {
  email: string;
  password: string;
  name: string;
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private accessToken: string | null = null;
  private currentUserSubject = new BehaviorSubject<any>(null);
  currentUser$ = this.currentUserSubject.asObservable();

  constructor(
    private http: HttpClient,
    private router: Router
  ) {
    console.log('API URL:', environment.apiUrl); 
    this.loadUserFromStorage();
  }

  login(email: string, password: string): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(
      `${environment.apiUrl}/api/auth/login`,
      { email, password } as LoginRequest
    ).pipe(
      tap(response => {
        this.accessToken = response.token;
        localStorage.setItem('access_token', response.token);
        const userInfo = {
          userId: response.userId,
          email: response.email,
          name: response.name,
          tenantId: response.tenantId
        };
        localStorage.setItem('user_info', JSON.stringify(userInfo));
        this.currentUserSubject.next(userInfo);
      })
    );
  }

  register(email: string, password: string, name: string): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(
      `${environment.apiUrl}/api/auth/register`,
      { email, password, name } as RegisterRequest
    ).pipe(
      tap(response => {
        this.accessToken = response.token;
        localStorage.setItem('access_token', response.token);
        const userInfo = {
          userId: response.userId,
          email: response.email,
          name: response.name,
          tenantId: response.tenantId
        };
        localStorage.setItem('user_info', JSON.stringify(userInfo));
        this.currentUserSubject.next(userInfo);
      })
    );
  }

  logout(): void {
    this.accessToken = null;
    localStorage.removeItem('access_token');
    localStorage.removeItem('user_info');
    this.currentUserSubject.next(null);
    this.router.navigate(['/login']);
  }

  isLoggedIn(): boolean {
  return !!this.accessToken || !!localStorage.getItem('access_token');
}

  getToken(): string | null {
  return this.accessToken || localStorage.getItem('access_token');
}

  getCurrentUser(): any {
    return this.currentUserSubject.value;
  }

  private loadUserFromStorage(): void {
    const token = localStorage.getItem('access_token');
    const userInfo = localStorage.getItem('user_info');
    if (token && userInfo) {
      this.accessToken = token;
      this.currentUserSubject.next(JSON.parse(userInfo));
    }
  }
}
