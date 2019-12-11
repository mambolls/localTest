package com.example.mybatisplus.client.entity;


import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author lilinsong
 * @since 2019-12-11
 */
public class User extends Model<User> implements Serializable{

private static final long serialVersionUID=1L;

                        @TableId(value = "id", type = IdType.AUTO)
                            private Long id;
                private String name;
                private Integer age;
    @TableField("phone_no")
                private String phoneNo;
                private String company;


    public Long getId(){
            return id;
            }

        public User setId(Long id){
            this.id = id;
                return this;
            }

    public String getName(){
            return name;
            }

        public User setName(String name){
            this.name = name;
                return this;
            }

    public Integer getAge(){
            return age;
            }

        public User setAge(Integer age){
            this.age = age;
                return this;
            }

    public String getPhoneNo(){
            return phoneNo;
            }

        public User setPhoneNo(String phoneNo){
            this.phoneNo = phoneNo;
                return this;
            }

    public String getCompany(){
            return company;
            }

        public User setCompany(String company){
            this.company = company;
                return this;
            }

    public static final String ID ="id";

    public static final String NAME ="name";

    public static final String AGE ="age";

    public static final String PHONE_NO ="phone_no";

    public static final String COMPANY ="company";

@Override
protected Serializable pkVal(){
            return this.id;
        }

@Override
public String toString() {
        return "User{" +
                ", id=" + id +
                ", name=" + name +
                ", age=" + age +
                ", phoneNo=" + phoneNo +
                ", company=" + company +
        "}";
        }
        }
