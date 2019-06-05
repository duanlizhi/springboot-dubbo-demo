package com.opay.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.opay.service.AccountService;
import org.springframework.stereotype.Component;

/**
 * <dl>
 * <dt><span class="strong">类说明:</span></dt>
 * <dd>用一句话描述该类作用</dd>
 * </dl>
 * <dl>
 * <dt><span class="strong">创建时间:</span></dt>
 * <dd>2019-06-05 10:59</dd>
 * </dl>
 *
 * @author duan_lizhi
 * @since 1.0
 */
@Service(interfaceClass = AccountService.class)
@Component
public class AccountServiceImpl implements AccountService {

    @Override
    public boolean saveAccount(String name) {
        System.out.println("服务调用>>> :" + name);
        return false;
    }
}
