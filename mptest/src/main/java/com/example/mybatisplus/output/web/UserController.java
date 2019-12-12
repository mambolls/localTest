package com.example.mybatisplus.output.web;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.example.mybatisplus.output.dto.UserDto;
import com.example.mybatisplus.output.entity.User;
import com.example.mybatisplus.output.service.UserService;
import com.example.mybatisplus.utils.QRCodeUtil;
import com.example.mybatisplus.utils.ZXingUtil;
import com.kingxunlian.common.XLBaseResponse;
import com.kingxunlian.third.app.base.log.Logger;
import org.hibernate.validator.HibernateValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;


/**
 * @ClassName UserController
 * @Description TODO
 * @Author lilinsong
 * @Date 2019/12/5 16:40
 * @Version 1.0
 */
@RestController
@RequestMapping("/output/user")
public class UserController {

    @Autowired
    private UserService service;


    /**
     * @MethodName getUsers
     * @Author lilinsong
     * @Description  TODO
     * @Param []
     * @return java.lang.String
     * @Date 2019/12/12 16:21
     **/
    @RequestMapping("/getUsers")
    public String getUsers() {
        Wrapper<User> wrapper = new EntityWrapper<>();
        List<User> list = this.service.selectList(wrapper);
        Logger.info("获取成功！");
        String jsonString = JSONObject.toJSONString(list);
        return jsonString;
    }

    /**
     * @return java.lang.String
     * @Author lilinsong
     * @Description
     * @Param [user]
     * @Date 2019/12/5 16:39
     **/
    @RequestMapping("/insertUser")
    public String insertUser(@RequestBody User user) {
        if (user == null || "".equals(user)) {
            return "user is empty!";
        }
        boolean insert = this.service.insert(user);
        if (!insert) {
            return "user insert ERR!";
        }
        return "user insert SUC!";
    }

    /**
     * @return java.lang.String
     * @Author lilinsong
     * @Description TODO
     * @Param [list]
     * @Date 2019/12/9 11:13
     **/
    @RequestMapping("/insertUserBatch")
    public String insertUserBatch(@RequestBody List<User> list) {
        if (CollectionUtils.isEmpty(list)) {
            return "user is empty!";
        }
        boolean insertBatch = this.service.insertBatch(list);
        if (!insertBatch) {
            return "user insert ERR!";
        }
        return "user insert SUC!";
    }

    /**
     * @return java.lang.String
     * @Author lilinsong
     * @Description
     * @Date 16:00 2019/12/5
     * @Param [list]
     **/
    @RequestMapping("/deldeletUserBatchet")
    public String deletUserBatch(@RequestBody List<Long> list) {

        boolean deleteBatchIds = this.service.deleteBatchIds(list);
        if (!deleteBatchIds) {
            return "user delete ERR!";
        }
        return "user delete SUC!";
    }

    /**
     * @return com.kingxunlian.common.XLBaseResponse<com.example.mybatisplus.output.entity.User>
     * @Author lilinsong
     * @Description
     * @Date 16:01 2019/12/5
     * @Param [userDto]
     **/
    @RequestMapping("/getUserById")
    public XLBaseResponse<User> getUserById(@RequestBody @Valid UserDto userDto) {
//        validate(userDto);
        Wrapper<User> wrapper = new EntityWrapper<>();
        wrapper.eq(User.ID, userDto.getId());
        User user = this.service.selectOne(wrapper);
        System.out.println(XLBaseResponse.newInstance(user).isOk());
        System.out.println(XLBaseResponse.newInstance(user).getBody());
        return XLBaseResponse.newInstance(user);
    }

    /**
     * @return void
     * @Author lilinsong
     * @Description 参数校验
     * @Param [request]
     * @Date 2019/12/6 9:48
     **/
    private void validate(Object request) {
        Validator validator = Validation.byProvider(HibernateValidator.class).configure().failFast(Boolean.FALSE)
                .buildValidatorFactory().getValidator();
        Set<ConstraintViolation<Object>> detailResult = validator.validate(request);
        StringJoiner sj = new StringJoiner(",");
        if (!detailResult.isEmpty()) {
            detailResult.stream().forEach(e -> sj.add(e.getMessageTemplate()));
            throw new RuntimeException(sj.toString());
        }
    }

    private static String path = "D:/page/pag1.jpg";

    /**
     * @return void
     * @Author lilinsong
     * @Description
     * @Param []
     * @Date 2019/12/5 16:34
     **/
    @RequestMapping("/readPag")
    public static String readPag() {
        byte[] pagData = ZXingUtil.image2byte(path);
        String code = ZXingUtil.readCode(pagData);
        return code;
    }

    @RequestMapping("/creatPag")
    public static String creatPag() throws Exception {
        // 存放在二维码中的内容
        String text = "砖搬完了吗？钱挣够了吗？一天就知道黄图";
        // 嵌入二维码的图片路径
        String imgPath = "D:/page/pag1.jpg";
        // 生成的二维码的路径及名称
        String destPath = "D:/page/jam.jpg";
        //生成二维码
        QRCodeUtil.encode(text, imgPath, destPath, true);
        // 解析二维码
        String str = QRCodeUtil.decode(destPath);
        // 打印出解析出的内容
        System.out.println(str);
        return "SUC";
    }


}

