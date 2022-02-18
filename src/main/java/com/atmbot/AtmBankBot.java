package com.atmbot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import static com.atmbot.VarConstant.*;

public class AtmBankBot extends TelegramLongPollingBot {
    int balance = 5000;


    @Override
    public String getBotUsername() {
        return "atmtelegram_bot";
    }

    @Override
    public String getBotToken() {
        return "5258741771:AAHY27uGDZOWMB2twuAAQqQnLFq8YC7aXTg";
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        long chatId = update.getMessage().getChatId();
        String text = message.getText();
        if (message != null && message.hasText()) {
            switch (message.getText()) {
                case START:
                    SendMessage sendMessageWelcome = new SendMessage();
                    sendMessageWelcome.setChatId(String.valueOf(chatId));
                    sendMessageWelcome.setText("Welcome to ATM\n" + "Your account number is " + chatId + "\n" + "/ATM");
                    try {
                        execute(sendMessageWelcome);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    break;

                case ATM:
                    SendMessage sendMessageATM = new SendMessage();
                    sendMessageATM.setChatId(String.valueOf(chatId));
                    sendMessageATM.setText("+=+=+=ATM operation=+=+=+\n" + "Choose (/withdraw) for Withdraw\n" + "Choose (/deposit) for Deposit\n" + "Choose (/balance) for Check Balance\n" + "Choose (/exit) for EXIT\n" + "Choose the operation you want to perform:");
                    try {
                        execute(sendMessageATM);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    break;

                case WITHDRAW:
                    SendMessage sendMessageWithdraw = new SendMessage();
                    sendMessageWithdraw.setChatId(String.valueOf(chatId));
                    double aDouble = Double.parseDouble(text);
                    double newBalance = balance - aDouble;
                    sendMessageWithdraw.setText("Your balance is" + newBalance);
                    try {
                        execute(sendMessageWithdraw);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    break;

                case DEPOSIT:
                    SendMessage sendMessageDeposit = new SendMessage();
                    sendMessageDeposit.setChatId(String.valueOf(chatId));
                    sendMessageDeposit.setText("Enter the amount to be deposit:");
                    try {
                        execute(sendMessageDeposit);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    break;

                case BALANCE:
                    SendMessage sendMessageBalance = new SendMessage();
                    sendMessageBalance.setChatId(String.valueOf(chatId));
                    sendMessageBalance.setText("Your balance is " + balance);
                    try {
                        execute(sendMessageBalance);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    break;

                case EXIT:
                    SendMessage sendMessageExit = new SendMessage();
                    sendMessageExit.setChatId(String.valueOf(chatId));
                    sendMessageExit.setText("Goodbye");
                    try {
                        execute(sendMessageExit);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    }
}

