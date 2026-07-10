import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-document-upload',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './document-upload.component.html',
  styleUrl: './document-upload.component.css'
})
export class DocumentUploadComponent {
  currentStep = 1;
  selectedFile: File | null = null;
  signers: Array<{ name: string; email: string }> = [];
  newSigner = { name: '', email: '' };

  constructor(private router: Router) {}

  onFileSelected(event: any): void {
    const file = event.target.files[0];
    if (file) {
      this.selectedFile = file;
    }
  }

  onDrop(event: DragEvent): void {
    event.preventDefault();
    const file = event.dataTransfer?.files[0];
    if (file) {
      this.selectedFile = file;
    }
  }

  onDragOver(event: DragEvent): void {
    event.preventDefault();
  }

  addSigner(): void {
    if (this.newSigner.name && this.newSigner.email) {
      this.signers.push({ ...this.newSigner });
      this.newSigner = { name: '', email: '' };
    }
  }

  removeSigner(index: number): void {
    this.signers.splice(index, 1);
  }

  nextStep(): void {
    if (this.currentStep < 3) {
      this.currentStep++;
    }
  }

  previousStep(): void {
    if (this.currentStep > 1) {
      this.currentStep--;
    }
  }

  submit(): void {
    // Show success toast and navigate
    alert('Document sent for signing successfully!');
    this.router.navigate(['/documents']);
  }
}
