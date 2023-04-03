import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Books } from '../_model/books';
import { Transaction } from '../_model/transaction';
import { Users } from '../_model/users';
import { BooksService } from '../_service/books.service';
import { TransactionService } from '../_service/transaction.service';
import { UsersService } from '../_service/users.service';

@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.css']
})
export class UserDetailsComponent implements OnInit {

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
    this.id = this.route.snapshot.params['userId'];
    // console.log(this.id);
    this.user = new Users();
    this.userService.getUserById(this.id).subscribe( data => {
      this.user = data;
      console.log(data);
    })

    this.getTransactionByUser(this.id);
    
  }

  private getTransactionByUser(userId: number) {
    this.transactionService.getBooksTransactionByUser(userId).subscribe(data => {
      this.transaction = data;
      console.log(data);
    });
  }

}
