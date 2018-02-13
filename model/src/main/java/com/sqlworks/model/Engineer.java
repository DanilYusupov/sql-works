package com.sqlworks.model;

public class Engineer {
        private Long id;
        private String firstName;
        private String lastName;
        private String major;
        private String tel;

        public Engineer(String firstName, String lastName, String major, String tel) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.major = major;
            this.tel = tel;
        }

        public Engineer(long id, String firstName, String lastName, String major, String tel) {
            this.id = id;
            this.firstName = firstName;
            this.lastName = lastName;
            this.major = major;
            this.tel = tel;
        }

        public Long getId() {
            return id;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public String getMajor() {
            return major;
        }

        public String getTel() {
            return tel;
        }

    public void setMajor(String major) {
        this.major = major;
    }
}
