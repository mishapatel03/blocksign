import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';
import { LucideAngularModule, Bell } from 'lucide-angular';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [CommonModule, LucideAngularModule],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {
  pageTitle = 'BlockSign';
  notificationCount = 3;
  Bell = Bell;

  constructor(private router: Router, private route: ActivatedRoute) {
    this.updatePageTitle();
  }

  private updatePageTitle(): void {
    this.router.events.subscribe(() => {
      const url = this.router.url;
      
      if (url === '/dashboard') {
        this.pageTitle = 'Dashboard';
      } else if (url === '/documents') {
        this.pageTitle = 'Documents';
      } else if (url === '/documents/upload') {
        this.pageTitle = 'Upload Document';
      } else if (url === '/audit') {
        this.pageTitle = 'Audit Trail';
      } else {
        this.pageTitle = 'BlockSign';
      }
    });
  }
}
