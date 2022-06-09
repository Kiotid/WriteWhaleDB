package it.rvs.writewhale.controller;

import java.net.HttpURLConnection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import it.rvs.writewhale.model.Users;
import it.rvs.writewhale.service.UsersService;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.*;
import java.net.*;

//mark class as Controller
@RestController
public class UsersController extends TelegramLongPollingBot {
    @Autowired
    UsersService usersService;

    public void onUpdateReceived(Update update) {

        String msg = update.getMessage().getText();
        String chatId=update.getMessage().getChatId().toString();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);

        if(msg.startsWith("/start")){
            sendMessage.setText("Hi, this bot is a bot :D");
        }else if(msg.startsWith("/login")){
            String username = msg.split(" ")[1];
            String password = msg.split(" ")[2];

        }else if(msg.startsWith("/register")){
            String username = msg.split(" ")[1];
            String password = msg.split(" ")[2];
            String repassword = msg.split(" ")[3];

            if (password.equals(repassword)){
                sendMessage.setText(username + " you are welcome!");
                //////////////I WANT CALL THE FUNCTION SAVEUSER :(
            }
        }

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {}
    }

    @Override
    public String getBotUsername() {
        return "writewhalebot";
    }

    @Override
    public String getBotToken() {
        return "5317756365:AAHnbxCSqhSijNuR25Rispy0MyKUZGvuUhA";
    }

    //creating a get mapping that retrieves all the users detail from the database
    @GetMapping("/user")
    private List<Users> getAllusers()
    {
        return usersService.getAllUsers();
    }
    //creating a get mapping that retrieves the detail of a specific user
    @GetMapping("/user/{userid}")
    private Users getusers(@PathVariable("userid") int userid)
    {
        return usersService.getUsersById(userid);
    }
    //creating a delete mapping that deletes a specified user
    @DeleteMapping("/user/{userid}")
    private void deleteuser(@PathVariable("userid") int userid)
    {
        usersService.delete(userid);
    }

    //creating post mapping that post the user detail in the database
    @PostMapping("/users")
    private int saveuser(@RequestBody Users users)
    {
        usersService.saveOrUpdate(users);
        return users.getUserid();
    }
    //creating put mapping that updates the user detail
    @PutMapping("/users")
    private Users update(@RequestBody Users users)
    {
        usersService.saveOrUpdate(users);
        return users;
    }
}
