package fr.dauphine.miageif.microserv.library;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Reader {
    @Id
    private int id;

    @Column
    private String gender;
    @Column
    private String name;
    @Column
    private String firstName;
    @Column
    private Date birthDate;
    @Column
    private String address;

    public Reader(){}

    public Reader(int id, String gender, String name, String firstName, Date birthDate, String address) {
        this.id = id;
        this.gender = gender;
        this.name = name;
        this.firstName = firstName;
        this.birthDate = birthDate;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
