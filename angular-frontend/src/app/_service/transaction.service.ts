import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Transaction } from '../_model/transaction';

@Injectable({
  providedIn: 'root'
})
export class TransactionService {

  private baseURL = "http://localhost:8080/transaction";

  constructor(private httpClient: HttpClient) { }

  getTransactionList(): Observable<Transaction[]> {
    return this.httpClient.get<Transaction[]>(`${this.baseURL}`);
  }

  transactionBook(transaction: Transaction): Observable<Object> {
    return this.httpClient.post(`${this.baseURL}`, transaction);
  }

  returnBook(transaction: Transaction): Observable<Object> {
    return this.httpClient.put(`${this.baseURL}`, transaction);
  }

  getBooksTransactionByUser(userId: number): Observable<Transaction[]> {
    return this.httpClient.get<Transaction[]>(`${this.baseURL}/user/${userId}`);
  }

  getBookTransactionHistory(bookId: number): Observable<Transaction[]> {
    return this.httpClient.get<Transaction[]>(`${this.baseURL}/book/${bookId}`);
  }
}
