package com.getjavajob.nemchinovr.controller;

import com.getjavajob.nemchinovr.dao.AccountDao;
import com.getjavajob.nemchinovr.dao.BookDao;
import com.getjavajob.nemchinovr.model.Account;
import com.getjavajob.nemchinovr.model.Book;
import com.getjavajob.nemchinovr.model.ElectedBook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class BookController {
    private static final Logger logger = LoggerFactory.getLogger(BookController.class);
    @Autowired
    private AccountDao accountDao;
    @Autowired
    private BookDao bookDao;

    @ResponseBody
    @RequestMapping(value = "/book/add/")
    public String addBookInFavoritesWithAjax(@RequestParam("id") int bookId, HttpSession session) {
        Account accountFromSession = (Account) session.getAttribute("account");
        accountFromSession.getElectedBookList().add(new ElectedBook(accountFromSession, bookDao.getById(bookId)));
        accountDao.saveAccount(accountFromSession);
        logger.info("id book - " + bookId);
        return "ok";
    }

    @ResponseBody
    @RequestMapping(value = "/book/delete/")
    public String deleteBookWithAjax(@RequestParam("bookId") int id, HttpSession session) {
        Account accountFromSession = (Account) session.getAttribute("account");
        logger.info("get account from session with firstName: " + accountFromSession.getFirstName());
        ElectedBook electedBook = new ElectedBook(accountFromSession, bookDao.getById(id));
        logger.info("delete elected book - " + electedBook.toString());
        logger.info("size1 " + accountFromSession.getElectedBookList().size());
        accountFromSession.getElectedBookList().remove(electedBook);
        logger.info("size2 " + accountFromSession.getElectedBookList().size());
        accountDao.saveAccount(accountFromSession);
        logger.info("delete book id " + id);
        session.setAttribute("account", accountFromSession);
        return "ok";
    }

    @ResponseBody
    @RequestMapping(value = "/book/fav/")
    public List<Book> returnListBooks(HttpSession session) {
        Account accountFromSession = (Account) session.getAttribute("account");
        List<ElectedBook> electedBooks = accountFromSession.getElectedBookList();
        List<Book> bookList = new ArrayList<>();
        for (ElectedBook temp : electedBooks) {
            bookList.add(temp.getBook());
        }
        return bookList;
    }

    @ResponseBody
    @RequestMapping(value = "/all/book/")
    public List<Book> returnAllBooks() {
        logger.info("запрос на получение книг");
        return bookDao.findAllBooks();
    }
}