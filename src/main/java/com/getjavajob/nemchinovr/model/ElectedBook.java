package com.getjavajob.nemchinovr.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "favorites")
public class ElectedBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "id_account", nullable = false)
    private Account owner;
    @OneToOne
    @JoinColumn(name = "id_book", nullable = false)
    private Book book;


    public ElectedBook(Account owner, Book book) {
        this.owner = owner;
        this.book = book;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ElectedBook that = (ElectedBook) o;
        if (owner != null ? !owner.equals(that.owner) : that.owner != null) return false;
        return book != null ? book.equals(that.book) : that.book == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (owner != null ? owner.hashCode() : 0);
        result = 31 * result + (book != null ? book.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ElectedBook{" +
                "id=" + id +
                ", owner=" + owner +
                ", book=" + book +
                '}';
    }
}