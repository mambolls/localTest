package com.example.mybatisplus.output.service.impl;

import com.example.mybatisplus.output.entity.User;
import com.example.mybatisplus.output.mapper.UserMapper;
import com.example.mybatisplus.output.service.UserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author lilinsong
 * @since 2019-12-04
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
