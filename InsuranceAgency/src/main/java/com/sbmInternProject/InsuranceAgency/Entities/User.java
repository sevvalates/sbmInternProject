package com.sbmInternProject.InsuranceAgency.Entities;

import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Integer id;
        private String userName;
        private String email;
        private String password;

        public User() {}

        public Integer getId() {
                return id;
        }

        public void setId(Integer id) {
                this.id = id;
        }

        public String getUserName() {
                return userName;
        }

        public void setUserName(String userName) {
                this.userName = userName;
        }

        public String getEmail() {
                return email;
        }

        public void setEmail(String email) {
                this.email = email;
        }

        public String getPassword() {
                return password;
        }

        public void setPassword(String password) {
                this.password = password;
        }

        @Override
        public String toString() {
                return "User{" +
                        "id=" + id +
                        ", user name='" + userName + '\'' +
                        ", email='" + email + '\'' +
                        ", password='" + password + '\'' +
                        '}';
        }
}
