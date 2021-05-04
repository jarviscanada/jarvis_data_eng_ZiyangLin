package ca.jrvs.apps.trading.model.domain;

import java.util.Date;
import java.util.Objects;

public class Trader implements Entity<Integer> {

    private int id;
    private String firstName;
    private String lastName;
    private Date dob;
    private String country;
    private String email;

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trader trader = (Trader) o;
        return id == trader.id &&
                Objects.equals(firstName, trader.firstName) &&
                Objects.equals(lastName, trader.lastName) &&
                Objects.equals(dob, trader.dob) &&
                Objects.equals(country, trader.country) &&
                Objects.equals(email, trader.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, dob, country, email);
    }

    @Override
    public String toString() {
        return "Trader{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dob=" + dob +
                ", country='" + country + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
