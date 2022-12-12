package hu.mik.prog5.assignment.bookrental.controller;

import hu.mik.prog5.assignment.bookrental.entity.Book;
import hu.mik.prog5.assignment.bookrental.entity.Genre;
import hu.mik.prog5.assignment.bookrental.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/rental")
    public String showBooks(Model model) {
        model.addAttribute("books", this.bookService.findAll());
        return "rental";
    }

    @GetMapping("/my-rental")
    public String showMyRentalBooks(@RequestParam String username, Model model) {
        model.addAttribute("rentals", this.bookService.findByUsername(username));
        return "my-rental";
    }

    @GetMapping("/rent")
    public String getBook(@RequestParam Long id, Model model) {
        model.addAttribute("book", this.bookService.findById(id));
        return "rent";
    }

    @PostMapping("/rent")
    public String rentBook(final Book book) {

        this.bookService.rentBook(book);
        return "redirect:rental";
    }

    @GetMapping("/save-book")
    public String addBookForm(@RequestParam(required = false) Long id, final Book book, Model model) {
        if (id != null) {
            model.addAttribute("book", this.bookService.findById(id));
        }
        return "save-book";
    }

    @PostMapping("/save-book")
    public String addBook(final Book book) {
        if (book.getId() != null) {
            this.bookService.update(book);
        } else {
            this.bookService.create(book);
        }

        return "save-book";
    }

    @GetMapping("/retrieve-book")
    public String getRentedBooks(Model model) {
        model.addAttribute("rented_books", this.bookService.findAllRentedBooks());
        return "retrieve-book";
    }

    @GetMapping("/retrieve-book/{id}")
    public String retrieveBook(@PathVariable Long id) {
        this.bookService.retrieveBook(id);
        return "redirect:/retrieve-book";
    }

    @ModelAttribute("allGenre")
    public Genre[] getAllGenre() {
        return Genre.values();
    }
}
