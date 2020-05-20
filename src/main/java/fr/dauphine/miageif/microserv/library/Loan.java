package fr.dauphine.miageif.microserv.library;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Reader reader;

    @ManyToOne
    private Book book;

    @Column
    private Date loanDate;

    @Column
    private Date returnDate;

    @Column
    private boolean availableLoan;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Reader getReader() {
        return reader;
    }

    public Book getBook() {
        return book;
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

    public Loan(){}

    public Loan(Reader reader, Book book, Date loanDate, Date returnDate) {
        this.reader = reader;
        this.book = book;
        this.loanDate = loanDate;
        this.returnDate = returnDate;
    }

    public boolean isBetweenDatesLoanAndReturn(Date date){
        //tranform to sameformate
        long longLoanDate = this.loanDate.getTime();
        long longReturnDate = this.returnDate.getTime();
        long longDate = date.getTime();

        if(longLoanDate <= longDate &&  longReturnDate>=longDate) return true;

        return false;

    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Loan)) return false;
        Loan loan = (Loan) o;
        return reader.equals(loan.reader) &&
                book.equals(loan.book) &&
                Objects.equals(loanDate, loan.loanDate) &&
                Objects.equals(returnDate, loan.returnDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(loanDate, returnDate);
    }

    @Override
    public String toString() {
        return "Loan{" +
                "id=" + id +
                '}';
    }

    public void setAvailableLoan(boolean availableBook) {
        this.availableLoan = availableBook;
    }

    public boolean isAvailableLoan() {
        return availableLoan;
    }
}
