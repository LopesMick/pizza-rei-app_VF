import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './cadastro.component.html',
  styleUrl: './cadastro.component.scss',
})
export class CadastroComponent {
  private fb = inject(FormBuilder);
  private router = inject(Router);

  form = this.fb.group({
    nome: ['', [Validators.required, Validators.minLength(3)]],
    endereco: ['', Validators.required],
    telefone: ['', Validators.required],
    email: ['', [Validators.required, Validators.email]],
    senha: ['', [Validators.required, Validators.minLength(6)]],
    senha2: ['', Validators.required],
  });

  submit() {
    if (this.form.invalid) return;

    const { senha, senha2 } = this.form.value;
    if (senha !== senha2) {
      this.form.get('senha2')?.setErrors({ mismatch: true });
      return;
    }

    localStorage.setItem('pdr_user_v1', JSON.stringify(this.form.value));
    this.router.navigate(['/login']);
  }
}
