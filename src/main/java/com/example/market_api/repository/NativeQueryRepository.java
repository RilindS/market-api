package com.example.market_api.repository;

import com.example.market_api.data.User.UserView;
import com.example.market_api.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class NativeQueryRepository {

    private final EntityManager em;

    public NativeQueryRepository(EntityManager em) {
        this.em = em;
    }

    public List<UserView> GetAllUsers(Integer page, Integer size) {
        page = page -1;
        Integer offset=page *size;

        String queryString = "SELECT u.id,u.first_name,u.last_name,u.email,u.phone_number FROM Users u ORDER BY u.id ASC";

        Query query = em.createNativeQuery(queryString);

        List<Object[]> resultsSet =  query.getResultList();

        return  getUserList(resultsSet);


    }
    public long getUsersCount(String search) {
        String countQueryString = "SELECT COUNT(DISTINCT t.id) FROM users t " +
                "WHERE t.deleted_at IS NULL ";

        if (search != null && !search.isEmpty()) {
            countQueryString += "AND (t.first_name LIKE '%" + search + "%' OR t.last_name LIKE '%" + search + "%' OR t.email LIKE '%" + search + "%')";
        }

        Query countQuery = em.createNativeQuery(countQueryString);
        return ((Number) countQuery.getSingleResult()).longValue();
    }
    private List<UserView> getUserList(List<Object[]> results) {
        List<UserView> users = new ArrayList<>();


        for (Object[] objects : results) {
            UserView userView =new UserView();

            if(objects[0]!=null){
                userView.setId(Long.valueOf(String.valueOf(objects[0])));
            }

            if(objects[1]!=null){
                userView.setFirstName(String.valueOf(objects[1]));
            }
            if(objects[2]!=null){
                userView.setLastName(String.valueOf(objects[2]));
            }
            if(objects[3]!=null){
                userView.setEmail(String.valueOf(objects[3]));
            }
            if(objects[4]!=null){
                userView.setPhoneNumber(String.valueOf(objects[4]));
            }
//            if(objects[5]!=null){
//                userView.setRole(String.valueOf(objects[5]));
//            }
//            if(objects[6]!=null){
//                userView.setRole(String.valueOf(objects[6]));
//            }
            users.add(userView);


        }
        return users;
    }

}
