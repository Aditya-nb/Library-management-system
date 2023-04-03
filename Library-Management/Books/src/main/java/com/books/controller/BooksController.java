package com.books.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.books.entity.Books;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin("http://localhost:4200/")
@RestController
@RequestMapping("/admin")
public class BooksController {

    @Autowired
    private RestTemplate restTemplate;
    
    public BooksController(RestTemplate restTemplate) {
    	this.restTemplate=restTemplate;
    	
    }
    
    
    @GetMapping("/books")
    public List<Books> getAllBooks() {
        String url = "http://localhost:8081/books";
        ResponseEntity<List<Books>> response = restTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Books>>() {});
        return response.getBody();
    }

    @PreAuthorize("hasRole('Admin')")
    @GetMapping("/books/{id}")
    public ResponseEntity<Books> getBookById(@PathVariable Integer id) throws NotFoundException {
        String url = "http://localhost:8081/books/{id}";
        ResponseEntity<Books> response = restTemplate.getForEntity(url, Books.class, id);
        Books book = response.getBody();
        return ResponseEntity.ok(book);
    }

    @PreAuthorize("hasRole('Admin')")
    @PostMapping("/books")
    public Books createBook(@RequestBody Books book) {
        String url = "http://localhost:8081/books";
        return restTemplate.postForObject(url, book, Books.class);
    }

    @PreAuthorize("hasRole('Admin')")
    @PutMapping("/books/{id}")
    public ResponseEntity<Books> updateBook(@PathVariable Integer id, @RequestBody Books bookDetails) throws NotFoundException {
        String url = "http://localhost:8081/books/{id}";
        restTemplate.put(url, bookDetails, id);
        return ResponseEntity.ok(bookDetails);
    }

    @PreAuthorize("hasRole('Admin')")
    @DeleteMapping("/books/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteBook(@PathVariable Integer id) throws NotFoundException {
        String url = "http://localhost:8081/books/{id}";
        restTemplate.delete(url, id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
    

//    @GetMapping("/books")
//    public List<Books> getAllBooks(){
//        return booksRepository.findAll();
//    }
//    
//    @PreAuthorize("hasRole('Admin')")
//    @GetMapping("/books/{id}")
//    public ResponseEntity<Books> getBookById(@PathVariable Integer id) throws NotFoundException {
//        Books book = booksRepository.findById(id).orElseThrow(() -> new NotFoundException());
//        return ResponseEntity.ok(book);
//    }
//
//    @PreAuthorize("hasRole('Admin')")
//    @PostMapping("/books")
//    public Books createBook(@RequestBody Books book) {
//        return booksRepository.save(book);
//    }
//
//    @PreAuthorize("hasRole('Admin')")
//    @PutMapping("/books/{id}")
//    public ResponseEntity<Books> updateBook(@PathVariable Integer id, @RequestBody Books bookDetails) throws NotFoundException {
//        Books book = booksRepository.findById(id).orElseThrow(() -> new NotFoundException());
//
//        book.setBookName(bookDetails.getBookName());
//        book.setBookAuthor(bookDetails.getBookAuthor());
//        book.setBookGenre(bookDetails.getBookGenre());
//        book.setNoOfCopies(bookDetails.getNoOfCopies());
//
//        Books updatedBook = booksRepository.save(book);
//        return ResponseEntity.ok(updatedBook);
//    }
//
//    @PreAuthorize("hasRole('Admin')")
//    @DeleteMapping("/books/{id}")
//    public ResponseEntity<Map<String, Boolean>> deleteBook(@PathVariable Integer id) throws NotFoundException {
//        Books book = booksRepository.findById(id).orElseThrow(() -> new NotFoundException());
//
//        booksRepository.delete(book);
//        Map<String, Boolean> response = new HashMap<>();
//        response.put("deleted", Boolean.TRUE);
//        return ResponseEntity.ok(response);
//    }
}
