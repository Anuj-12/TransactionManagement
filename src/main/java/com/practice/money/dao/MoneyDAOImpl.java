package com.practice.money.dao;

import com.practice.money.entity.Transaction;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NoSuchElementException;

// Marks for component scanning and provides CRUD objects
@Repository
public class MoneyDAOImpl implements MoneyDAO{

    private EntityManager entityManager;

    @Autowired
    public MoneyDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void debit(float amt, String reason) {
        Transaction trans = new Transaction(amt, reason);
        float bal = 0;

        // try-catch block to prevent index = -1
        try{
            // Create a query to get a list of all transactions
            TypedQuery<Transaction> lastBal = entityManager.createQuery("FROM Transaction", Transaction.class);
            List<Transaction> balList = lastBal.getResultList();
            // Get the last student and set the balance = last student's balance
            bal = balList.get(balList.size() - 1).getBal();
        }catch (IndexOutOfBoundsException e){}

        // Set the new balance = last balance + amount deposited
        trans.setBal(bal + amt);

        // Add a new entry to the database table
        entityManager.persist(trans);
    }

    @Override
    @Transactional
    public void debit(float amt) {
        Transaction trans = new Transaction(amt);
        float bal = 0;

        try{
            TypedQuery<Transaction> lastBal = entityManager.createQuery("FROM Transaction", Transaction.class);
            List<Transaction> balList = lastBal.getResultList();
            bal = balList.get(balList.size() - 1).getBal();
        }catch (IndexOutOfBoundsException e){}

        trans.setBal(bal + amt);
        entityManager.persist(trans);
    }

    @Override
    @Transactional
    public void credit(float amt, String reason) {
        Transaction trans = new Transaction(amt, reason);

        float bal = 0;

        try{
            TypedQuery<Transaction> lastBal = entityManager.createQuery("FROM Transaction", Transaction.class);
            List<Transaction> balList = lastBal.getResultList();
            bal = balList.get(balList.size() - 1).getBal();
        }catch (IndexOutOfBoundsException e){
            System.out.println("Sorry you don't have enough balance in your account");
        }

        if(bal - amt > 0){
            trans.setBal(bal - amt);
            trans.setAmount(-amt);
            System.out.println("Rs " + amt + " has be credited to your account");
            entityManager.persist(trans);
        }else{
            System.out.println("Sorry you don't have enough balance in your account");
        }
    }

    @Override
    @Transactional
    public void credit(float amt) {
        Transaction trans = new Transaction(amt);

        float bal = 0;

        try{
            TypedQuery<Transaction> lastBal = entityManager.createQuery("FROM Transaction", Transaction.class);
            List<Transaction> balList = lastBal.getResultList();
            bal = balList.get(balList.size() - 1).getBal();
        }catch (IndexOutOfBoundsException e){
            System.out.println("Sorry you don't have enough balance in your account");
        }

        if(bal - amt > 0){
            trans.setBal(bal - amt);
            trans.setAmount(-amt);
            System.out.println("Rs " + amt + " has be credited to your account");
            entityManager.persist(trans);
        }else{
            System.out.println("Sorry you don't have enough balance in your account");
        }
    }

    @Override
    public String findById(int id) {

        try{
            TypedQuery<Transaction> trans = entityManager.createQuery("FROM Transaction WHERE trans_id=:id", Transaction.class);
            trans.setParameter("id", id);
            return trans.getResultList().toString();
        }catch (Exception e){
            System.out.println("There are no transactions with this ID");
            return null;
        }
    }

    @Override
    public String lastTrans() {
        TypedQuery<Transaction> lastTrans = entityManager.createQuery("FROM Transaction", Transaction.class);
        return lastTrans.getResultList().getLast().toString();
    }

    @Override
    @Transactional
    public void clearAccount() {
        // Using actual Query instead of JPQL
        // executeUpdate() method is used when deleting something in the database
       entityManager.createNativeQuery("TRUNCATE TABLE account", Transaction.class).executeUpdate();
    }

    @Override
    public float showBalance() {
        // Fetch a list of balance of all the students
        TypedQuery<Float>balList = entityManager.createQuery("SELECT bal FROM Transaction", Float.class);

        float currentBal = 0;

        // try-catch block needed when the table is empty
        try{
            // Return the last balance recorded
            List<Float> bal = balList.getResultList();
            currentBal = bal.getLast();
        } catch (NoSuchElementException e){}
        return currentBal;
    }
}