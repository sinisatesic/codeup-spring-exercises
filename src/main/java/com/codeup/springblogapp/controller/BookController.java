package com.codeup.springblogapp.controller;

import com.codeup.springblogapp.model.Book;
import com.codeup.springblogapp.repositories.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BookController {

    // Dependency Injection
    private BookRepository bookRepo;

    public BookController(BookRepository bookRepo) {
        this.bookRepo = bookRepo;
    }

    @GetMapping("/books")
    @ResponseBody
    public String getBooks() {
        String books = "<ul>";
        for (Book book : this.bookRepo.findAll()) {
            books += "<li>"+book.getTitle() + " by " + book.getAuthor() + "</li>";
        }
        books += "</ul>";
        return books;
    }

//    @PostMapping("/books/create")
//    @ResponseBody
//    public String newPerson(
//            @RequestParam(name = "title") String titleParam,
//            @RequestParam(name = "author") String authorParam
//    ) {
//        Book book = new Book();
//        book.setTitle(titleParam);
//        book.setAuthor(authorParam);
//        this.bookRepo.save(book);
//        return "New Book Created";
//    }


    //redirect:
    //return "redirect:/posts";

}