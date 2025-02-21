package com.practice.money;

import com.practice.money.dao.MoneyDAO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MoneyApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoneyApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(MoneyDAO moneyDAO){
		return runner -> {

			//debitAmount(moneyDAO, 200, "General transaction");
			//debitAmount(moneyDAO, 500);
			//clearTransactions(moneyDAO);
			//showCurrentBalance(moneyDAO);
			//creditAmount(moneyDAO, 200, "Pizza");
			//creditAmount(moneyDAO, 500);
		};
	}

	private void creditAmount(MoneyDAO moneyDAO, float amt) {
		System.out.println("------------------------------------------------------------------------------");
		moneyDAO.credit(amt);
		System.out.println("------------------------------------------------------------------------------");
	}

	private void creditAmount(MoneyDAO moneyDAO, float amt, String reason) {

		System.out.println("------------------------------------------------------------------------------");
		moneyDAO.credit(amt, reason);
		System.out.println("------------------------------------------------------------------------------");
	}

	private void showCurrentBalance(MoneyDAO moneyDAO) {

		System.out.println("------------------------------------------------------------------------------");
		System.out.println("The current balance in the account is : " + moneyDAO.showBalance());
		System.out.println("------------------------------------------------------------------------------");
	}

	private void clearTransactions(MoneyDAO moneyDAO) {
		System.out.println("------------------------------------------------------------------------------");
		System.out.println("Clearing the transaction table...");
		moneyDAO.clearAccount();
		System.out.println("------------------------------------------------------------------------------");
	}

	private void debitAmount(MoneyDAO moneyDAO, int amt, String reason) {

		System.out.println("------------------------------------------------------------------------------");
		// Add "amt" amount of money in account
		System.out.println("Rs " + amt + " debited to your account");
		moneyDAO.debit(amt, reason);
		System.out.println("Transaction details : ");
		System.out.println(moneyDAO.lastTrans());
		System.out.println("------------------------------------------------------------------------------");
	}

	private void debitAmount(MoneyDAO moneyDAO, int amt){
		System.out.println("------------------------------------------------------------------------------");
		System.out.println("Rs " + amt + " debited to your account");
		moneyDAO.debit(amt);
		System.out.println("Transaction details : ");
		System.out.println(moneyDAO.lastTrans());
		System.out.println("------------------------------------------------------------------------------");
	}

	private void findById(MoneyDAO moneyDAO, int id){
		System.out.println("------------------------------------------------------------------------------");
		System.out.println("Finding the transaction with the ID : " + id + "....");
		moneyDAO.findById(id);
		System.out.println("------------------------------------------------------------------------------");
	}
}
