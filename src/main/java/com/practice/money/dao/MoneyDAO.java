package com.practice.money.dao;

public interface MoneyDAO {

    void debit(float amt ,String reason);
    void debit(float amt);

    void credit(float amt ,String reason);
    void credit(float amt);

    String findById(int id);

    String lastTrans();

    void clearAccount();

    float showBalance();
}
