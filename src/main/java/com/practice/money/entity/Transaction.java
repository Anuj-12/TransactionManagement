package com.practice.money.entity;

import jakarta.persistence.*;

// Mapping Transaction (class) to account (table)
@Entity
@Table(name="account")
public class Transaction {

    public Transaction(){

    }

    public Transaction(float amount){
        this.amount = amount;
        reason = "Not specified";
    }

    public Transaction(float amount, String reason) {
        this.amount = amount;
        this.reason = reason;
    }

    // Marking this as the primary key
    @Id
    // Auto incrementation of values
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trans_id")
    private int transId;

    @Column(name = "amount")
    private float amount;

    @Column(name = "reason")
    private String reason;

    @Column(name = "balance")
    private float bal;

    public int getTransId() {
        return transId;
    }

    public void setTransId(int transId) {
        this.transId = transId;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public float getBal() {
        return bal;
    }

    public void setBal(float bal) {
        this.bal = bal;
    }

    @Override
    public String toString() {
            return "Transaction ID = " + transId +
                ", Amount = " + amount +
                ", Reason = " + reason +
                ", Balance = " + bal;
    }
}
