package life.majiang.community.service;

import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.User;
import life.majiang.community.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public void createOrUpdate(User user) {
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andAccountIdEqualTo(user.getAccountId());
        List<User> users = userMapper.selectByExample(userExample);
       if(users.size()==0){
           //insert
           user.setGmtCreate(System.currentTimeMillis());
           user.setGmtModified(user.getGmtCreate());
           userMapper.insert(user);
       }else{
           //update
           User dbUser = users.get(0);
           User updateUser = new User();
           //update is the things needed to be updated,dbUser get the user from the front
           updateUser.setGmtModified(System.currentTimeMillis());
           updateUser.setToken(user.getToken());
           updateUser.setName(user.getName());
           updateUser.setAvatarUrl(user.getAvatarUrl());
           UserExample example = new UserExample();
           example.createCriteria()
                   .andIdEqualTo(dbUser.getId());
           userMapper.updateByExampleSelective(updateUser,example);
       }
    }

}
