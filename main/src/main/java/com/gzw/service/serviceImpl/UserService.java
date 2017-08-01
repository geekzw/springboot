package com.gzw.service.serviceImpl;

import com.gzw.domain.ResultInfo;
import com.gzw.domain.Token;
import com.gzw.domain.User;
import com.gzw.domain.UserExample;
import com.gzw.domain.vo.LoginVO;
import com.gzw.enums.ResultCode;
import com.gzw.mappers.UserMapper;
import com.gzw.service.RedisTokenService;
import com.gzw.utils.Md5Util;
import com.gzw.utils.RsaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by gujian on 2017/6/23.
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTokenService tokenService;

    public User getUserByUsername(String username){
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(username);
        List<User> list = userMapper.selectByExample(userExample);
        return list == null?null:list.get(0);
    }

    /**
     * 登录
     * @param user
     * @return
     */
    public ResultInfo loginIn(User user, HttpSession session){
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(user.getUsername());
        List<User> list = userMapper.selectByExample(userExample);
        User user1 = list == null?null:list.get(0);
        ResultInfo<LoginVO> resultInfo;
        LoginVO loginVO = new LoginVO();

        if(user1 == null){
            resultInfo = ResultInfo.getErrorInfo(ResultCode.NO_USER);
        }else{
            if(user1.getPassword().equals(Md5Util.pwdDigest(user.getPassword()))){
                session.setAttribute("user",user);
                Token token = tokenService.create(user.getUsername());
                String auth = token.getUsername()+"_"+token.getToken();
                loginVO.setToken(auth);
                loginVO.setPublicKey(RsaUtil.getPublicKeyStrOrCreate());
                resultInfo = ResultInfo.getSuccessWithInfo(ResultCode.LOGIN_SUCCESS,loginVO);
            }else{
                resultInfo = ResultInfo.getErrorInfo(ResultCode.ERROR_USERNAME_OR_PASSSWORD);
            }
        }
        return resultInfo;
    }

    /**
     * 注册
     * @param user
     * @return
     */
    public ResultInfo register(User user){
        ResultInfo resultInfo;
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(user.getUsername());
        List<User> list = userMapper.selectByExample(userExample);
        User user1 = list == null?null:list.get(0);
        if(user1!=null){
            resultInfo = ResultInfo.getErrorInfo(ResultCode.USER_EXIST);
        }else{
            user.setPassword(Md5Util.pwdDigest(user.getPassword()));
            if(userMapper.insert(user)>0) resultInfo = ResultInfo.getSuccessInfo(ResultCode.REGISTER_SUCCESS);
            resultInfo = ResultInfo.getErrorInfo(ResultCode.REGISTER_FAILE);
        }
        return resultInfo;
    }

    public ResultInfo<User> update(User user){
        ResultInfo<User> resultInfo;
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(user.getUsername());

        if(userMapper.updateByExample(user,userExample)>0){
            resultInfo = ResultInfo.getSuccessInfo(ResultCode.UPDATE_SUCCESS);
            resultInfo.setData(userMapper.selectByExample(userExample).get(0));
        }else{
            resultInfo = ResultInfo.getErrorInfo(ResultCode.UPDATE_ERROR);
        }
        return resultInfo;
    }
}
