import { Component, OnInit } from '@angular/core';
import { Books } from '../_model/books';
import { Transaction } from '../_model/transaction';
import { BooksService } from '../_service/books.service';
import { TransactionService } from '../_service/transaction.service';
import { UserAuthService } from '../_service/user-auth.service';

@Component({
  selector: 'app-transaction-book',
  templateUrl: './transaction-book.component.html',
  styleUrls: ['./transaction-book.component.css']
})
export class TransactionBookComponent implements OnInit {

  books: Books[];

  constructor(
    private booksService: BooksService,
    private userAuthService: UserAuthService,
    private transactionService: TransactionService,
  ) { }

  userId = this.userAuthService.getUserId();

  ngOnInit(): void {
    this.getBooks();
  }

  private getBooks() {
    this.booksService.getBooksList().subscribe(data =>{
      this.books = data;
    });
  }

  transaction: Transaction = new Transaction();

  transactionBook(bookId: number) {
    this.transaction.bookId = bookId;
    this.transaction.userId = this.userId;
    console.log(this.transaction);
    this.transactionService.transactionBook(this.transaction).subscribe(data => {
      console.log(data);
    },
    error => console.log(error));
  }
}
