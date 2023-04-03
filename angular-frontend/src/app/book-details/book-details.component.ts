import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Books } from '../_model/books';
import { Transaction } from '../_model/transaction';
import { Users } from '../_model/users';
import { BooksService } from '../_service/books.service';
import { TransactionService } from '../_service/transaction.service';
import { UsersService } from '../_service/users.service';

@Component({
  selector: 'app-book-details',
  templateUrl: './book-details.component.html',
  styleUrls: ['./book-details.component.css']
})
export class BookDetailsComponent implements OnInit {

  id: number;
  book: Books;
  transaction: Transaction[];
  user: Users;

  constructor(private route: ActivatedRoute,
    private bookService: BooksService,
    private transactionService: TransactionService,
    public userService: UsersService
  ) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['bookId'];
    // console.log(this.id);
    this.book = new Books();
    this.bookService.getBookById(this.id).subscribe( data => {
      this.book = data;
      console.log(data);
    })

    this.getTransactionHistory(this.id);
    
  }

  private getTransactionHistory(bookId: number) {
    this.transactionService.getBookTransactionHistory(bookId).subscribe(data => {
      this.transaction = data;
      console.log(data);
    });
  }

  public getUserData(userId: number):string {
    this.user = new Users();
    this.userService.getUserById(userId).subscribe( data => {
      this.user = data;
    })
    return this.user.name;
  }
}
