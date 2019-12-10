package com.example.mybatisplus.output.dto;

import javax.validation.constraints.NotNull;

public class UserDto {

    @NotNull(message = "用户ID不能为空")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
