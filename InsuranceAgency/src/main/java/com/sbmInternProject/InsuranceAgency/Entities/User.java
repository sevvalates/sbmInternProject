package com.sbmInternProject.InsuranceAgency.Entities;

import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "users")
public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", nullable = false, updatable = false)
        private long id;
        @Column(name = "name", nullable = false)
        private String name;
        @Column(name = "surname", nullable = false)
        private String surname;
        @Column(name = "email", nullable = false)
        private String email;

        @Column(name = "identity_number", nullable = false, unique = true)
        private long identityNumber;
        @Column(name = "phone_number", nullable = false)
        private long phoneNumber;


        public User() {
        }

        public User(String name, String surname, long identityNumber, long phoneNumber, String email) {
                this.name = name;
                this.surname = surname;
                this.identityNumber = identityNumber;
                this.phoneNumber = phoneNumber;
                this.email = email;
        }

        public long getId() {
                return id;
        }

        public void setId(long id) {
                this.id = id;
        }

        public long getIdentityNumber() {
                return identityNumber;
        }

        public void setIdentityNumber(long identityNumber) {
                this.identityNumber = identityNumber;
        }

        public long getPhoneNumber() {
                return phoneNumber;
        }

        public void setPhoneNumber(long phoneNumber) {
                this.phoneNumber = phoneNumber;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getSurname() {
                return surname;
        }

        public void setSurname(String surname) {
                this.surname = surname;
        }

        public String getEmail() {
                return email;
        }

        public void setEmail(String email) {
                this.email = email;
        }

        @Override
        public String toString() {
                return "User{" +
                        "id=" + id +
                        ", name='" + name + '\'' +
                        ", surname='" + surname + '\'' +
                        ", email='" + email + '\'' +
                        ", identityNumber=" + identityNumber +
                        ", phoneNumber=" + phoneNumber +
                        '}';
        }
}