package com.opay.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.opay.service.AccountService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <dl>
 * <dt><span class="strong">类说明:</span></dt>
 * <dd>用一句话描述该类作用</dd>
 * </dl>
 * <dl>
 * <dt><span class="strong">创建时间:</span></dt>
 * <dd>2019-06-05 11:32</dd>
 * </dl>
 *
 * @author duan_lizhi
 * @since 1.0
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @Reference(url = "dubbo://127.0.0.1:20880")
    private AccountService accountService;

    @RequestMapping("/test")
    public String test() {
        accountService.saveAccount("段立志");
        return "哈哈哈哈啦啦啦啦";
    }
}
