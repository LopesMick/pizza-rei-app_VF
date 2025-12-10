import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, Validators } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';

@Component({
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterLink],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss',
})
export class LoginComponent {
  private fb = inject(FormBuilder);
  private router = inject(Router);

  form = this.fb.group({
    usuario: ['', Validators.required],
    senha: ['', Validators.required],
  });

  erro = '';

  submit() {
    this.erro = '';
    if (this.form.invalid) return;

    const saved = localStorage.getItem('pdr_user_v1');
    if (!saved) {
      this.erro = 'Usuário não encontrado. Faça o cadastro.';
      return;
    }

    const user = JSON.parse(saved);

    if (this.form.value.senha !== user.senha) {
      this.erro = 'Senha inválida.';
      return;
    }

    localStorage.setItem('pdr_logged_v1', 'true');
    this.router.navigate(['/cardapio']);
  }
}
