package fr.dauphine.miageif.microserv.library;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.Objects;

@Entity
public class Loan {

    @Id
    private int id;

    @Column
    private String ISBN;
    @Column
    private int idReader;
    @Column
    private Date loanDate;
    @Column
    private Date returnDate;

    public Loan(){}

    public Loan(int id, String ISBN, int idReader, Date loanDate, Date returnDate) {
        this.id = id;
        this.ISBN = ISBN;
        this.idReader = idReader;
        this.loanDate = loanDate;
        this.returnDate = returnDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public int getIdReader() {
        return idReader;
    }

    public void setIdReader(int idReader) {
        this.idReader = idReader;
    }

    public Date getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Loan)) return false;
        Loan loan = (Loan) o;
        return id == loan.id &&
                idReader == loan.idReader &&
                ISBN.equals(loan.ISBN) &&
                Objects.equals(loanDate, loan.loanDate) &&
                Objects.equals(returnDate, loan.returnDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(loanDate, returnDate);
    }
}
