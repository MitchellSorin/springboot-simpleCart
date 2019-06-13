package com.sorin.simplecart.test;

import com.sorin.simplecart.bean.User;
import com.sorin.simplecart.dao.ItemDAO;
import com.sorin.simplecart.dao.OrderDAO;
import com.sorin.simplecart.dao.UserDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * userTest
 *
 * @author LSD
 * @date 2019/06/12
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {
    // @Autowired
    // private UserDAO userDAO;
    // @Autowired
    // private ItemDAO itemDAO;
    // @Autowired
    // private OrderDAO orderDAO;

    @Test
    public void addUsers() {
        // User user = new User();
        // user.setId("naruto");
        // user.setName("naruto");
        // user.setPassword("naruto");
        // userDAO.saveAndFlush(user);
        // user.setId("jiriya");
        // user.setName("jiriya");
        // user.setPassword("jiriya");
        // userDAO.saveAndFlush(user);
        // user.setId("kakashi");
        // user.setName("kakashi");
        // user.setPassword("kakashi");
        // userDAO.saveAndFlush(user);
        // user.setId("sakura");
        // user.setName("sakura");
        // user.setPassword("sakura");
        // userDAO.saveAndFlush(user);
        // user.setId("aa");
        // user.setName("aa");
        // user.setPassword("aa");
        // userDAO.saveAndFlush(user);
        // user.setId("ss");
        // user.setName("ss");
        // user.setPassword("ss");
        // userDAO.saveAndFlush(user);
        // user.setId("qq");
        // user.setName("qq");
        // user.setPassword("qq");
        // userDAO.saveAndFlush(user);


        // Item item = new Item();
        // item.setId("pear");
        // item.setName("pear");
        // item.setPrice(new BigDecimal(2.33));
        // itemDAO.saveAndFlush(item);
    }

    @Test
    public void deleteUser() {
        // User user = new User();
        // user.setId("naruto");
        // List<User> list = new ArrayList<>();
        // list.add(user);
        // //批量删除，不会先一条一条查询，和deleteAll相比
        // userDAO.deleteInBatch(list);
    }

    @Test
    public void selectOne() {
        // orderDAO.findAll();

        // userDAO.findById("naruto");
    }

    @Test
    public void selectList() {
        //过滤，排序，分页
        // Example<User> example = new Example<User>() {
        //     @Override
        //     public User getProbe() {
        //         User user = new User();
        //         user.setId("a");
        //         return user;
        //     }
        //
        //     @Override
        //     public ExampleMatcher getMatcher() {
        //         ExampleMatcher matcher = ExampleMatcher.matching()
        //                 .withMatcher("id", ExampleMatcher.GenericPropertyMatchers.contains())
        //                 .withIgnoreCase("id");
        //         return matcher;
        //     }
        // };
        // List<Sort.Order> orderList = new ArrayList<>();
        // Sort.Order order0 = new Sort.Order(Sort.Direction.ASC, "id");
        // orderList.add(order0);
        // Sort.Order order1 = new Sort.Order(Sort.Direction.DESC, "name");
        // orderList.add(order1);
        // Sort sort = Sort.by(orderList);
        // PageRequest pageRequest = PageRequest.of(2, 2, sort);
        // Page<User> all = userDAO.findAll(example, pageRequest);


        // userDAO.findByIdOrName(null, "jiriya");
        // List<String> names = new ArrayList<>();
        // names.add("jiraya");
        // names.add("naruto");
        // userDAO.findByNameIn(names);
        // Page<User> all = userDAO.findByNameLike("%a%", pageRequest);
        // userDAO.selectByIdOrName("", "naruto").get(0).getId();
    }
}
