package ru.job4j.bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс описывает работу модели для банковской системы
 * @author Stanislav Kurilenko
 * @version 1.0
 */
public class BankService {
    /**
     * Хранение связки данных пользователя и банковского счета осуществляется
     * в коллекции типа Map
     */
    private final Map<User, List<Account>> users = new HashMap<>();

    /**
     * Метод принимает на вход данные пользователя и, если его нет в системе,
     * добавляет этого пользователя, создавая новый банковский счет
     * @param user пользователь
     */
    public void addUser(User user) {
            users.putIfAbsent(user, new ArrayList<Account>());
    }

    /**
     * Метод принимает на вход данные паспорта пользователя и банковский счет,
     * который нужно добавить этому пользователю. Добавляет банковский счет,
     * проверяя перед этим, что такого счета еще нет у пользователя.
     * @param passport данные паспорта пользователя
     * @param account данные банковского счета
     */
    public void addAccount(String passport, Account account) {
        User user = findByPassport(passport);
        if (user != null) {
            List<Account> accounts = users.get(user);
            if (!accounts.contains(account)) {
                accounts.add(account);
            }
        }
    }

    /**
     * Метод принимает на вход данные паспорта пользователя и ищет пользователя
     * в списке по этим данным.
     * @param passport данные паспорта пользователя
     * @return возвращает найденного по паспорту пользователя
     */
    public User findByPassport(String passport) {
        return users.keySet()
                .stream()
                .filter(u -> u.getPassport().equals(passport))
                .findFirst()
                .orElse(null);
    }

    /**
     * Метод принимает на вход данные паспорта и реквизиты банковского счета
     * и ищет в списке счетов нужный.
     * @param passport данные паспорта пользователя
     * @param requisite реквизиты банковского счета
     * @return возвращает банковский счет или null если пользователя с таким пасспортом нет
     */
    public Account findByRequisite(String passport, String requisite) {
        User user = findByPassport(passport);
        if (user != null) {
            return users.get(user)
                    .stream()
                    .filter(a -> a.getRequisite().equals(requisite))
                    .findFirst()
                    .orElse(null);
        }
        return null;
    }

    /**
     * Метод принимает на вход данные паспорта пользователя и реквизиты счета с которого нужно первевести,
     * данные пасопорта пользователя и реквизиты счета на который нужно перевести, а также сумму перевода.
     * @param srcPassport данные паспорта пользователя, который переводит
     * @param srcRequisite реквизиты счета пользователя, который переводит
     * @param destPassport данные паспорта пользователя, которому переводят
     * @param destRequisite реквизиты счета пользователя, которому переводят
     * @param amount сумма перевода
     * @return метод возвращает true если перевод осуществлен или false если пользователь не найдет
     * или не хватает денег на счете, с которого переводят.
     */
    public boolean transferMoney(String srcPassport, String srcRequisite,
                                 String destPassport, String destRequisite, double amount) {
        boolean rsl = false;
        Account srcAccount = findByRequisite(srcPassport, srcRequisite);
        Account destAccount = findByRequisite(destPassport, destRequisite);
        if (srcAccount != null && destAccount != null && srcAccount.getBalance() >= amount) {
            srcAccount.setBalance(srcAccount.getBalance() - amount);
            destAccount.setBalance(destAccount.getBalance() + amount);
            rsl = true;
        }
        return rsl;
    }
}
