package com.fhdw.loeppe.helpers;

import com.fhdw.loeppe.dto.Article;
import com.fhdw.loeppe.dto.Customer;
import com.fhdw.loeppe.dto.Order;
import com.fhdw.loeppe.util.Country;
import com.fhdw.loeppe.util.OrderStatus;

import java.util.List;
import java.util.UUID;

public class SearchHelper {

    //Customer
    public static List<Customer> searchCustID(List<Customer> repo, String custID) {
        if(!custID.isEmpty() && !repo.isEmpty()) {
            for (int i = 0; i < repo.size(); i++) {
                if (!repo.get(i).getId().toString().contains(custID)) {
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

    public static List<Customer> searchCustEmail(List<Customer> repo, String email) {
        if(!email.isEmpty() && !repo.isEmpty()) {
            for(int i = 0; i < repo.size(); i++) {
                if(!repo.get(i).getEmailAdress().toLowerCase().contains(email.toLowerCase())) {
                    repo.remove(i);
                    i--;
                }
            }
        }
        return repo;
    }

    public static List<Customer> searchCustPhone(List<Customer> repo, String phone) {
        if(!phone.isEmpty() && !repo.isEmpty()) {
            for(int i = 0; i < repo.size(); i++) {
                if(!repo.get(i).getPhoneNumber().toLowerCase().contains(phone.toLowerCase())) {
                    repo.remove(i);
                    i--;
                }
            }
        }
        return repo;
    }

    public static List<Customer> searchCustStreet(List<Customer> repo, String street) {
        if(!street.isEmpty() && !repo.isEmpty()) {
            for(int i = 0; i < repo.size(); i++) {
                if(!repo.get(i).getStreet().toLowerCase().contains(street.toLowerCase())) {
                    repo.remove(i);
                    i--;
                }
            }
        }
        return repo;
    }

    public static List<Customer> searchCustCity(List<Customer> repo, String city) {
        if(!city.isEmpty() && !repo.isEmpty()) {
            for(int i = 0; i < repo.size(); i++) {
                if(!repo.get(i).getCity().toLowerCase().contains(city.toLowerCase())) {
                    repo.remove(i);
                    i--;
                }
            }
        }
        return repo;
    }

    public static List<Customer> searchCustPostal(List<Customer> repo, String postal) {
        if(!postal.isEmpty() && !repo.isEmpty()) {
            for(int i = 0; i < repo.size(); i++) {
                if(!repo.get(i).getPostalCode().toLowerCase().contains(postal.toLowerCase())) {
                    repo.remove(i);
                    i--;
                }
            }
        }
        return repo;
    }

    public static List<Customer> searchCustCountry(List<Customer> repo, Country country) {
        if(country != null && !repo.isEmpty()) {
            for(int i = 0; i < repo.size(); i++) {
                if(repo.get(i).getCountry() != country) {
                    repo.remove(i);
                    i--;
                }
            }
        }
        return repo;
    }

    //Article
    public static List<Article> searchArticleID(List<Article> repo, String artId) {
        if(!artId.isEmpty() && !repo.isEmpty()) {
            for (int i = 0; i < repo.size(); i++) {
                if (!repo.get(i).getId().toString().contains(artId)) {
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
    public static List<Order> searchOrderID(List<Order> repo, String orderID) {
        if(!orderID.isEmpty() && !repo.isEmpty()) {
            for (int i = 0; i < repo.size(); i++) {
                if (!repo.get(i).getId().toString().contains(orderID)) {
                    repo.remove(i);
                    i--;
                }
            }
        }
        return repo;
    }

    public static List<Order> searchOrderCustID(List<Order> repo, String custID) {
        if(!custID.isEmpty() && !repo.isEmpty()) {
            for (int i = 0; i < repo.size(); i++) {
                if (!repo.get(i).getCustomer().getId().toString().contains(custID)) {
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
