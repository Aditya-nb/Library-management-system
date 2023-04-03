package com.transact.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.books.entity.Books;
import com.transact.entity.Transact;
import com.transact.repository.TransactRepository;
import com.users.entity.Users;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Repository
@RestController
@RequestMapping("/borrow")
public class TransactController {
    
    @Autowired
    private RestTemplate restTemplate;
    
    
    @PostMapping
    public String borrowBook(@RequestBody Transact borrow) {
        ResponseEntity<Users> userResponse = restTemplate.getForEntity("http://localhost:8090/users/{id}", Users.class, borrow.getUserId());
        Users user = userResponse.getBody();

        ResponseEntity<Books> bookResponse = restTemplate.getForEntity("http://localhost:8081/books/{id}", Books.class, borrow.getBookId());
        Books book = bookResponse.getBody();

        if (book.getNoOfCopies() < 1) {
            return "The book \"" + book.getBookName() + "\" is out of stock!";
        }

        book.borrowBook();
        restTemplate.put("http://localhost:8081/books", book);

        Date currentDate = new Date();
        Date overdueDate = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(overdueDate);
        c.add(Calendar.DATE, 7);
        overdueDate = c.getTime();
        borrow.setIssueDate(currentDate);
        borrow.setDueDate(overdueDate);
        restTemplate.postForEntity("http://localhost:8092/transact", borrow, Transact.class);
        return user.getName() + " has borrowed one copy of \"" + book.getBookName() + "\"!";
    }

    @GetMapping
    public List<Transact> getAllTransactions() {
        ResponseEntity<List<Transact>> response = restTemplate.exchange("http://localhost:8092/transact", HttpMethod.GET, null, new ParameterizedTypeReference<List<Transact>>() {});
        return response.getBody();
    }

    @PutMapping
    public Transact returnBook(@RequestBody Transact borrow) {
        ResponseEntity<Transact> borrowResponse = restTemplate.getForEntity("http://localhost:8092/transact/{id}", Transact.class, borrow.getBorrowId());
        Transact borrowBook = borrowResponse.getBody();

        ResponseEntity<Books> bookResponse = restTemplate.getForEntity("http://localhost:8081/books/{id}", Books.class, borrowBook.getBookId());
        Books book = bookResponse.getBody();

        book.returnBook();
        restTemplate.put("http://localhost:8081/books", book);

        Date currentDate = new Date();
        borrowBook.setReturnDate(currentDate);
        return restTemplate.postForObject("http://localhost:8092/transact", borrowBook, Transact.class);
    }

    @GetMapping("user/{id}")
    public List<Transact> booksBorrowedByUser(@PathVariable Integer id) {
        ResponseEntity<List<Transact>> response = restTemplate.exchange("http://localhost:8092/transact/user/{id}", HttpMethod.GET, null, new ParameterizedTypeReference<List<Transact>>() {}, id);
        return response.getBody();
    }

    @GetMapping("book/{id}")
    public List<Transact> bookBorrowHistory(@PathVariable Integer id) {
        ResponseEntity<List<Transact>> response = restTemplate.exchange("http://localhost:8092/transact/book/{id}", HttpMethod.GET, null, new ParameterizedTypeReference<List<Transact>>() {}, id);
        return response.getBody();
    }


//    @Autowired
//    private UsersRepository usersRepository;
//
//    @Autowired
//    private BooksRepository booksRepository;

//    @PostMapping
//    public String borrowBook(@RequestBody Transact borrow) {
//        Users user = usersRepository.findById(borrow.getUserId()).get();
//        Books book = booksRepository.findById(borrow.getBookId()).get();
//
//        if (book.getNoOfCopies() < 1) {
//            return "The book \"" + book.getBookName() + "\" is out of stock!";
//        }
//
//        book.borrowBook();
//        booksRepository.save(book);
//
//        Date currentDate = new Date();
//        Date overdueDate = new Date();
//        Calendar c = Calendar.getInstance();
//        c.setTime(overdueDate);
//        c.add(Calendar.DATE, 7);
//        overdueDate = c.getTime();
//        borrow.setIssueDate(currentDate);
//        borrow.setDueDate(overdueDate);
//        borrowRepository.save(borrow);
//        return user.getName() + " has borrowed one copy of \"" + book.getBookName() + "\"!";
//    }
//
//    @GetMapping
//    public List<Transact> getAllBorrow() {
//        return borrowRepository.findAll();
//    }
//
//    @PutMapping
//    public Transact returnBook(@RequestBody Transact borrow) {
//        Transact borrowBook = borrowRepository.findById(borrow.getBorrowId()).get();
//        Transact book = booksRepository.findById(borrowBook.getBookId()).get();
//
//        book.returnBook();
//        booksRepository.save(book);
//
//        Date currentDate = new Date();
//        borrowBook.setReturnDate(currentDate);
//        return borrowRepository.save(borrowBook);
//    }
//
//    @GetMapping("user/{id}")
//    public List<Transact> booksBorrowedByUser(@PathVariable Integer id) {
//        return borrowRepository.findByUserId(id);
//    }
//
//    @GetMapping("book/{id}")
//    public List<Transact> bookBorrowHistory(@PathVariable Integer id) {
//        return borrowRepository.findByBookId(id);
//    }
}
