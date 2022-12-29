package com.fhdw.loeppe.helpers;

import com.fhdw.loeppe.dto.Article;
import com.fhdw.loeppe.dto.Customer;
import com.fhdw.loeppe.dto.Order;
import com.fhdw.loeppe.util.OrderStatus;

import java.util.List;

public class SearchHelper {

    //Customer
    public static List<Customer> searchCustID(List<Customer> repo, long id) {
        if(!repo.isEmpty()) {
            for (int i = 0; i < repo.size(); i++) {
                if (repo.get(i).getId() != id) {
                    repo.remove(i);
                    i--;
                }
            }
        }
        return repo;
    }

    public static List<Customer> searchCustFirstN(List<Customer> repo, String firstname) {
        if(!firstname.isEmpty() && !repo.isEmpty()) {
            for(int i = 0; i < repo.size(); i++) {
                if(!repo.get(i).getFirstname().toLowerCase().contains(firstname.toLowerCase())) {
                    repo.remove(i);
                    i--;
                }
            }
        }
        return repo;
    }

    public static List<Customer> searchCustLarstN(List<Customer> repo, String lastname) {
        if(!lastname.isEmpty() && !repo.isEmpty()) {
            for(int i = 0; i < repo.size(); i++) {
                if(!repo.get(i).getLastname().toLowerCase().contains(lastname.toLowerCase())) {
                    repo.remove(i);
                    i--;
                }
            }
        }
        return repo;
    }

    public static List<Customer> searchCustAddress(List<Customer> repo, String address) {
        if(!address.isEmpty() && !repo.isEmpty()) {
            for(int i = 0; i < repo.size(); i++) {
                if(!repo.get(i).getAddress().toLowerCase().contains(address.toLowerCase())) {
                    repo.remove(i);
                    i--;
                }
            }
        }
        return repo;
    }

    //Article
    public static List<Article> searchArticleID(List<Article> repo, long id) {
        if(!repo.isEmpty()) {
            for (int i = 0; i < repo.size(); i++) {
                if (repo.get(i).getId() != id) {
                    repo.remove(i);
                    i--;
                }
            }
        }
        return repo;
    }

    public static List<Article> searchArticleName(List<Article> repo, String name) {
        if(!name.isEmpty() && !repo.isEmpty()) {
            for(int i = 0; i < repo.size(); i++) {
                if(!repo.get(i).getName().toLowerCase().contains(name.toLowerCase())) {
                    repo.remove(i);
                    i--;
                }
            }
        }
        return repo;
    }

    //Order
    public static List<Order> searchOrderID(List<Order> repo, long id) {
        if(!repo.isEmpty()) {
            for (int i = 0; i < repo.size(); i++) {
                if (repo.get(i).getId() != id) {
                    repo.remove(i);
                    i--;
                }
            }
        }
        return repo;
    }

    public static List<Order> searchOrderCustID(List<Order> repo, long id) {
        if(!repo.isEmpty()) {
            for (int i = 0; i < repo.size(); i++) {
                if (repo.get(i).getCustomer().getId() != id) {
                    repo.remove(i);
                    i--;
                }
            }
        }
        return repo;
    }

    public static List<Order> searchOrderFirstname(List<Order> repo, String firstname) {
        if(!firstname.isEmpty() && !repo.isEmpty()) {
            for(int i = 0; i < repo.size(); i++) {
                if(!repo.get(i).getCustomer().getFirstname().toLowerCase().contains(firstname.toLowerCase())) {
                    repo.remove(i);
                    i--;
                }
            }
        }
        return repo;
    }

    public static List<Order> searchOrderLastname(List<Order> repo, String lastname) {
        if(!lastname.isEmpty() && !repo.isEmpty()) {
            for(int i = 0; i < repo.size(); i++) {
                if(!repo.get(i).getCustomer().getLastname().toLowerCase().contains(lastname.toLowerCase())) {
                    repo.remove(i);
                    i--;
                }
            }
        }
        return repo;
    }

    public static List<Order> searchOrderAddress(List<Order> repo, String address) {
        if(!address.isEmpty() && !repo.isEmpty()) {
            for(int i = 0; i < repo.size(); i++) {
                if(!repo.get(i).getCustomer().getAddress().toLowerCase().contains(address.toLowerCase())) {
                    repo.remove(i);
                    i--;
                }
            }
        }
        return repo;
    }

    public static List<Order> searchOrderStatus(List<Order> repo, OrderStatus status) {
        if(status != null && !repo.isEmpty()) {
            for(int i = 0; i < repo.size(); i++) {
                if(repo.get(i).getOrderStatus() != status) {
                    repo.remove(i);
                    i--;
                }
            }
        }
        return repo;
    }

}
