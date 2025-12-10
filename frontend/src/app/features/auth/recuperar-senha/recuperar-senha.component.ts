import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './recuperar-senha.component.html',
  styleUrl: './recuperar-senha.component.scss',
})
export class RecuperarSenhaComponent {
  private fb = inject(FormBuilder);
  private router = inject(Router);

  form = this.fb.group({
    usuario: ['', Validators.required],
    novaSenha: ['', [Validators.required, Validators.minLength(6)]],
    repita: ['', Validators.required],
  });

  msg = '';

  submit() {
    this.msg = '';
    if (this.form.invalid) return;

    const { novaSenha, repita } = this.form.value;

    if (novaSenha !== repita) {
      this.form.get('repita')?.setErrors({ mismatch: true });
      return;
    }

    const raw = localStorage.getItem('pdr_user_v1');
    if (!raw) {
      this.msg = 'Usuário não encontrado. Faça o cadastro.';
      return;
    }

    const user = JSON.parse(raw);
    user.senha = novaSenha;

    localStorage.setItem('pdr_user_v1', JSON.stringify(user));

    this.msg = 'Senha atualizada com sucesso.';
    this.router.navigate(['/login']);
  }
}
