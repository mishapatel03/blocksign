import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-spinner',
  standalone: true,
  imports: [CommonModule],
  template: `
    <div class="flex items-center justify-center p-8">
      <div class="w-8 h-8 border-2 border-border border-t-primary rounded-full animate-spin"></div>
    </div>
  `,
  styleUrl: './spinner.component.css'
})
export class SpinnerComponent {}
