import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../../../environments/environment';

@Injectable({ providedIn: 'root' })
export class ApiService {
  private base = environment.apiUrl;

  // Seu professor pediu para forçar json:
  private headers = new HttpHeaders({ 'Content-Type': 'application/json' });
  private options = {
    headers: this.headers,
    responseType: 'json' as const,
  };

  constructor(private http: HttpClient) {}

  // exemplos futuros:
  // getPizzas() { return this.http.get(`${this.base}/pizzas`, this.options); }
}
