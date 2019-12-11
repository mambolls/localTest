package com.example.mybatisplus.client.service.impl;

import com.example.mybatisplus.client.entity.User;
import com.example.mybatisplus.client.mapper.UserMapper;
import com.example.mybatisplus.client.service.UserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lilinsong
 * @since 2019-12-11
 */
        @Service
        public class UserServiceImpl extends ServiceImpl<UserMapper, User>implements UserService {

        }
