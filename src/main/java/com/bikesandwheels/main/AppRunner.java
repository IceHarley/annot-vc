package com.bikesandwheels.main;

import com.bikesandwheels.persistence.model.Author;
import com.bikesandwheels.persistence.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;

@Component
public class AppRunner implements Runnable {
//    @Autowired
//    AuthorService authorService;

    public void run() {
//        Author author = new Author();
//        author.setName("John" + Long.toString(Calendar.getInstance().getTimeInMillis()));
//        authorService.save(author);
    }
}
