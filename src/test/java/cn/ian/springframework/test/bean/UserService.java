package cn.ian.springframework.test.bean;

public class UserService {

    private String uId;
    private UserDAO userDAO;

    public void queryUserInfo() {
        System.out.println("查询用户信息" + userDAO.queryUserName(uId));
    }

    public UserService() {
    }
}
