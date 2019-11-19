package com.pukhalskyi.entity;

public enum DbType {
    H2, MYSQL;

    public String getListRepo() {
        if (this.equals(MYSQL)) {
            return "ticketJdbcModelRepository";
        }

        return "ticketJpaModelRepository";
    }

    public String getPageRepo() {
        if (this.equals(MYSQL)) {
            return "ticketPageJdbcModelRepository";
        }

        return "ticketPageJpaModelRepository";
    }
}
