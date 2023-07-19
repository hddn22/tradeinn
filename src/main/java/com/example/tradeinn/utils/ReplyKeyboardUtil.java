package com.example.tradeinn.utils;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ReplyKeyboardUtil {
    public final static ReplyKeyboardMarkup START_MENU_BUTTONS = new ReplyKeyboardMarkup();
    public final static ReplyKeyboardMarkup MAIN_MENU_BUTTONS = new ReplyKeyboardMarkup();
    public final static ReplyKeyboardMarkup SERVICE_BUTTONS = new ReplyKeyboardMarkup();
    public final static ReplyKeyboardMarkup ACCOUNT_BUTTONS = new ReplyKeyboardMarkup();
    public final static ReplyKeyboardMarkup SUM_BUTTONS = new ReplyKeyboardMarkup();

    public final static ReplyKeyboardMarkup CONFIRM_BUTTONS = new ReplyKeyboardMarkup();

    public final static ReplyKeyboardMarkup CHANGE_BUTTONS = new ReplyKeyboardMarkup();

    public final static ReplyKeyboardMarkup FARE_BUTTONS = new ReplyKeyboardMarkup();

    public final static ReplyKeyboardMarkup WORK_BUTTONS = new ReplyKeyboardMarkup();
    public final static ReplyKeyboardMarkup MGCC_BUTTONS = new ReplyKeyboardMarkup();

    static {
        List<KeyboardRow> keyboard = new ArrayList<>();

        keyboard.add(initKeyboardRow("Я в деле!"));
        setButtonOptions(START_MENU_BUTTONS, keyboard);


        keyboard = new ArrayList<>();
        keyboard.add(initKeyboardRow("\uD83D\uDEB4\uD83C\uDFFC\u200D♂️Сделать заказ"));

        keyboard.add(initKeyboardRow("\uD83D\uDCB8 Тарифы", "\uD83D\uDCC4 Условия работы"));

        keyboard.add(initKeyboardRow("\uD83C\uDFE1 Moscow Gravel Cycling Club"));
        setButtonOptions(MAIN_MENU_BUTTONS, keyboard);


        // Кнопки выбора сервиса
        keyboard = new ArrayList<>();
        keyboard.add(initKeyboardRow("TradeInn (BikeInn, RunnerInn, etc.)"));

        keyboard.add(initKeyboardRow("StarBike"));

        keyboard.add(initKeyboardRow("Любой другой зарубежный сервис(условия индивидуальны)"));

        keyboard.add(initKeyboardRow("☠️Отмена"));
        setButtonOptions(SERVICE_BUTTONS, keyboard);

        // Кнопки выбора аккаунта
        keyboard = new ArrayList<>();
        keyboard.add(initKeyboardRow("Мой"));

        keyboard.add(initKeyboardRow("Ваш"));

        keyboard.add(initKeyboardRow("☠️Отмена"));
        setButtonOptions(ACCOUNT_BUTTONS, keyboard);

        // Кнопки выбора суммы
        keyboard = new ArrayList<>();
        keyboard.add(initKeyboardRow("До 20тыс.руб."));

        keyboard.add(initKeyboardRow("От 20тыс.руб. до 40тыс.руб."));

        keyboard.add(initKeyboardRow("Свыше 40тыс.руб."));

        keyboard.add(initKeyboardRow("☠️Отмена"));
        setButtonOptions(SUM_BUTTONS, keyboard);

        keyboard = new ArrayList<>();
        keyboard.add(initKeyboardRow("Отправить ✅"));

        keyboard.add(initKeyboardRow("Удалить ❌"));

        keyboard.add(initKeyboardRow("Изменить \uD83D\uDCDD"));

        setButtonOptions(CONFIRM_BUTTONS, keyboard);

        keyboard = new ArrayList<>();
        keyboard.add(initKeyboardRow("Сервис"));

        keyboard.add(initKeyboardRow("Аккаунт"));

        keyboard.add(initKeyboardRow("Сумма"));

        keyboard.add(initKeyboardRow("☠️Отмена"));

        setButtonOptions(CHANGE_BUTTONS, keyboard);

        keyboard = new ArrayList<>();
        keyboard.add(initKeyboardRow("\uD83D\uDEB4\uD83C\uDFFC\u200D♂️Сделать заказ"));

        keyboard.add(initKeyboardRow("⏪ Назад в меню"));

        setButtonOptions(FARE_BUTTONS, keyboard);

        keyboard = new ArrayList<>();
        keyboard.add(initKeyboardRow("\uD83D\uDEB4\uD83C\uDFFC\u200D♂️Сделать заказ"));
        keyboard.add(initKeyboardRow("\uD83D\uDCB5 Оплата", "↩️ Возвраты"));
        keyboard.add(initKeyboardRow("⏪ Назад в меню"));

        setButtonOptions(WORK_BUTTONS, keyboard);

        keyboard = new ArrayList<>();
        keyboard.add(initKeyboardRow("⏪ Назад в меню"));

        setButtonOptions(MGCC_BUTTONS, keyboard);
    }

    private static KeyboardRow initKeyboardRow(String... text) {
        KeyboardRow keyboardRow = new KeyboardRow();
        Stream.of(text).forEach(keyboardRow::add);

        return keyboardRow;
    }
    private static void setButtonOptions(ReplyKeyboardMarkup replyKeyboardMarkup, List<KeyboardRow> keyboard) {
        replyKeyboardMarkup.setKeyboard(keyboard);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);
    }

    public static boolean validateMessage(String text, ReplyKeyboardMarkup keyboardMarkup) {
        return keyboardMarkup.getKeyboard()
                .stream()
                .anyMatch(keyboardButtons -> keyboardButtons.contains(text));
    }
}
