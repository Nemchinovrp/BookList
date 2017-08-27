package com.getjavajob.nemchinovr.listener;

import com.getjavajob.nemchinovr.dao.BookDao;
import com.getjavajob.nemchinovr.model.Book;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Component
public class InitListener {
    private static final Logger logger = LoggerFactory.getLogger(InitListener.class);

    @Autowired
    private BookDao bookDao;

    @EventListener
    public void handleContextRefresh(ContextRefreshedEvent event) throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File first = new File(classLoader.getResource("image/first.jpg").getFile());
        File second = new File(classLoader.getResource("image/second.jpg").getFile());
        if (bookDao.findAllBooks().size() == 0) {
            Book firstBook = new Book("first", IOUtils.toByteArray(new FileInputStream(first)));
            Book secondBook = new Book("second", IOUtils.toByteArray(new FileInputStream(second)));
            bookDao.saveBook(firstBook);
            bookDao.saveBook(secondBook);
            logger.info("write init data");
        }
        logger.info("data have in table");
    }
}
