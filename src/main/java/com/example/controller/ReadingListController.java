package com.example.controller;

import com.example.entity.Book;
import com.example.repo.ReadingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @autor A_Nakonechnyi
 * @date 18.05.2016.
 */

@Controller
@RequestMapping("/")
public class ReadingListController {

    private ReadingListRepository readingListRepository;

    @Autowired
    public ReadingListController(
            ReadingListRepository readingListRepository) {
        this.readingListRepository = readingListRepository;
    }

    @RequestMapping(value="/{reader}", method=RequestMethod.GET)
    public String readersBooks(
            @PathVariable("reader") String reader,
            Model model) {
        List<Book> readingList =
                readingListRepository.findByReader(reader);
        if (readingList != null) {
            model.addAttribute("books", readingList);
        }
        return "readingList";
    }

    @RequestMapping(value="/{reader}", method= RequestMethod.POST)
    public String addToReadingList(
            @PathVariable("reader") String reader, Book book) {
        book.setReader(reader);
        readingListRepository.save(book);
        return "redirect:/{reader}";
    }
}
