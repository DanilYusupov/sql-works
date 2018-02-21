package com.sqlworks.model;

public class Engineer {
        private Long id;
        private String firstName;
        private String lastName;
        private String major;
        private Long tel;

        public Engineer(String firstName, String lastName, String major, Long tel) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.major = major;
            this.tel = tel;
        }

        public Engineer(long id, String firstName, String lastName, String major, Long tel) {
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

        public Long getTel() {
            return tel;
        }

    public void setMajor(String major) {
        this.major = major;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setTel(Long tel) {
        this.tel = tel;
    }
}
