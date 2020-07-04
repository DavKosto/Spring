package com.flamexander.hibernate.products_items;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Test {
    private Customer customer;
    private Item item;
    private Session session;
    private SessionFactory factory;

    public Test(){
        session = null;
        PrepareDataApp.forcePrepareData();
        factory = new Configuration()
                .configure("configs/hibernate.cfg.xml")
                .buildSessionFactory();
    }

    private void getSession(){
        session = factory.getCurrentSession();
        session.beginTransaction();
    }

    public void showCustomerProducts(Long id){
        try {
            getSession();
            customer = session.get(Customer.class, id);
            System.out.println(customer.getName() + ", покупал: ");
            for (Item item : customer.getItems()) {
                System.out.println(item.getTitle());
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            factory.close();
            if (session != null) {
                session.close();
            }
        }
    }

    public void showCustomerSpecificProduct(Long id){
        try {
            getSession();
            item = session.get(Item.class, id);
            System.out.println("Клиенты, купившие " + item.getTitle() + ": ");
            for (Customer customer : item.getCustomers()) {
                System.out.println(customer.getName());
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            factory.close();
            if (session != null) {
                session.close();
            }
        }
    }

    public void deleteCustomer(Long id){
        try {
            getSession();
            session.delete(session.get(Customer.class, id));
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            factory.close();
            if (session != null) {
                session.close();
            }
        }
    }

    public void deleteItem(long id){
        try {
            getSession();
            session.delete(session.get(Item.class, id));
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            factory.close();
            if (session != null) {
                session.close();
            }
        }
    }
}
