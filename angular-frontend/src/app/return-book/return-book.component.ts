import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Books } from '../_model/books';
import { Transaction } from '../_model/transaction';
import { BooksService } from '../_service/books.service';
import { TransactionService } from '../_service/transaction.service';
import { UserAuthService } from '../_service/user-auth.service';

@Component({
  selector: 'app-return-book',
  templateUrl: './return-book.component.html',
  styleUrls: ['./return-book.component.css']
})
export class ReturnBookComponent implements OnInit {

  books: Books[];
  transaction: Transaction[];

  constructor(
    private transactionService: TransactionService,
    private booksService: BooksService,
    private userAuthService: UserAuthService
  ) { }

  userId = this.userAuthService.getUserId();

  ngOnInit(): void {
    this.getBooks();
    this.getBooksByUser();
  }

  private getBooks() {
    this.booksService.getBooksList().subscribe(data =>{
      this.books = data;
    });
  }

  
  private getBooksByUser() {
    this.transactionService.getBooksTransactionByUser(this.userId).subscribe(data => {
      this.transaction = data;
    })
  }

  brw: Transaction = new Transaction();
  public returnBook(transactionId: number) {
    this.brw.transactionId = transactionId;
    this.transactionService.returnBook(this.brw).subscribe(data => {
      console.log(data);
    },
    error => console.log(error));
  }

}
