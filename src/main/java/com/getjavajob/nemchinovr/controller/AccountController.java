package com.getjavajob.nemchinovr.controller;

import com.getjavajob.nemchinovr.dao.AccountDao;
import com.getjavajob.nemchinovr.dto.AccountDto;
import com.getjavajob.nemchinovr.model.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class AccountController {
    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private AccountDao accountDao;

    @RequestMapping(value = "*")
    public String startPage() {
        return "login";
    }

    @ResponseBody
    @RequestMapping(value = "/registration/ajax", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public String registrationAjax(@RequestBody AccountDto accountDto) {
        Account account = new Account(accountDto);
        logger.info("json from ajax accountDto - " + account.toString());
        accountDao.saveAccount(account);
        logger.info("save account " + account.getFirstName());
        return "success";
    }

    @ResponseBody
    @RequestMapping(value = "/login/ajax/")
    public String loginWithAjax(@RequestParam("login") String login, @RequestParam("password") String password, HttpSession session) {
        Account account = accountDao.getByLogin(login);
        if (account != null && password.equals(account.getPassword().trim())) {
            session.setAttribute("account", account);
            logger.info("added account in session" + account.getFirstName());
            return "success";
        } else {
            return "failed";
        }
    }

    @RequestMapping(value = "/logout/", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        Account accountFromSession = (Account) session.getAttribute("account");
        session.invalidate();
        logger.info("session is invalidate " + accountFromSession.getFirstName());
        return "login";
    }
}