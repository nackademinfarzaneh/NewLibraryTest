/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nackademin.librarytest.librarytestsystem.model;

/**
 *
 * @author testautom-nack
 */
public class SingleLoan {
    
    
    private Loan loan;
    
    public SingleLoan(Loan loan){
    
//   Book book;
//    User user;
    this.loan = loan;        
    }
    /**
     * @return the loan
     */
    public Loan getLoan() {
        return loan;
    }

    /**
     * @param loan the loan to set
     */
    public void setLoan(Loan loan) {
        this.loan = loan;
    }
    
    
}
