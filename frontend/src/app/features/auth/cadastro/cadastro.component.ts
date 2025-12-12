import { Component, inject } from '@angular/core';
import {
  FormBuilder,
  Validators,
  AbstractControl,
  ValidationErrors,
  ReactiveFormsModule,
} from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../../environments/environment';

function senhasIguais(group: AbstractControl): ValidationErrors | null {
  const senha = group.get('senha')?.value;
  const senha2Control = group.get('senha2');
  const senha2 = senha2Control?.value;

  if (!senha2Control) return null;

  if (!senha || !senha2) {
    if (senha2Control.hasError('mismatch')) {
      const { mismatch, ...rest } = senha2Control.errors ?? {};
      senha2Control.setErrors(Object.keys(rest).length ? rest : null);
    }
    return null;
  }

  if (senha !== senha2) {
    senha2Control.setErrors({ ...(senha2Control.errors ?? {}), mismatch: true });
    return { mismatch: true };
  }

  if (senha2Control.hasError('mismatch')) {
    const { mismatch, ...rest } = senha2Control.errors ?? {};
    senha2Control.setErrors(Object.keys(rest).length ? rest : null);
  }
  return null;
}

@Component({
  selector: 'app-cadastro',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './cadastro.component.html',
})
export class CadastroComponent {
  private fb = inject(FormBuilder);
  private http = inject(HttpClient);

  form = this.fb.group(
    {
      nome: ['', Validators.required],
      endereco: ['', Validators.required],
      telefone: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      senha: ['', [Validators.required, Validators.minLength(6)]],
      senha2: ['', Validators.required],
    },
    { validators: senhasIguais }
  );

  submit() {
    this.form.markAllAsTouched();
    if (this.form.invalid) return;

    const payload = {
      nome: this.form.value.nome,
      email: this.form.value.email,
      senha: this.form.value.senha,
      endereco: this.form.value.endereco,
      telefone: this.form.value.telefone,
    };

    this.http.post(`${environment.apiUrl}/api/usuario/salvar`, payload).subscribe({
      next: (r) => console.log('Cadastrado!', r),
      error: (e) => console.error('Erro ao cadastrar', e),
    });
  }
}
