package org.bank.dao.configuration;

import java.sql.*;
import java.util.UUID;

public class DBInit {

    public static final UUID CUSTOMER_1_UUID = UUID.fromString("39856993-fecc-4704-9787-725dd6ab9195");

    public static final UUID ACCOUNT_1_UUID = UUID.fromString("49856993-fecc-4704-9787-725dd6ab9195");

    public static void init() {
       try (final Connection connection = DBConfiguration.getConnection()) {
           Statement statement = connection.createStatement();
           statement.setQueryTimeout(30);
           statement.executeUpdate("drop table if exists account");
           statement.executeUpdate("drop table if exists customer");
           statement.executeUpdate("drop table if exists operation");
           statement.executeUpdate("create table customer (uuid varchar(36), name varchar(36))");
           statement.executeUpdate("create table account (uuid varchar(36), total_amount integer, customer_uuid varchar(36))");
           statement.executeUpdate("create table operation (uuid varchar(36), account_uuid varchar(36), amount integer, date date)");
           statement.executeUpdate(String.format("insert into customer values ('%s', 'Customer1')", CUSTOMER_1_UUID));
           statement.executeUpdate(String.format("insert into account values ('%s', 0, '%s')", ACCOUNT_1_UUID, CUSTOMER_1_UUID));
       } catch(SQLException e) {
           System.err.println(e.getMessage());
       }
   }

}
