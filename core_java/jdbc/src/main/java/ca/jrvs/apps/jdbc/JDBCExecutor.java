package ca.jrvs.apps.jdbc;

import org.apache.log4j.BasicConfigurator;

import java.sql.*;

public class JDBCExecutor {

    public static void main(String[] args) {
        BasicConfigurator.configure();

        DatabaseConnectionManager dcm = new DatabaseConnectionManager("localhost",
                "hplussport", "postgres", "password");
        try {
            Connection connection = dcm.getConnection();
            CustomerDAO customerDAO = new CustomerDAO(connection);
            OrderDAO orderDAO = new OrderDAO(connection);
            Customer customer = new Customer();
            customer.setFirstName("John");
            customer.setLastName("Adams");
            customer.setEmail("jadams@wh.gov");
            customer.setAddress("1234 Main St");
            customer.setPhone("(555) 555-9845 Main St");
            customer.setCity("Arlington");
            customer.setState("VA");
            customer.setZipCode("01234");

            Customer dbCustomer = customerDAO.create(customer);
            System.out.println(dbCustomer);
            dbCustomer = customerDAO.findById(dbCustomer.getId());
            System.out.println(dbCustomer);
            dbCustomer.setEmail("john.adams@wh.gov");
            dbCustomer = customerDAO.update(dbCustomer);
            System.out.println(dbCustomer);
            customerDAO.delete(dbCustomer.getId());

            Order order = orderDAO.findById(1111);
            System.out.println(order);
        } catch (SQLException ex) {
            dcm.logger.error(ex.getMessage(), ex);
        }
    }

}
