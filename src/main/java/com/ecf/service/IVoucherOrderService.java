package com.ecf.service;

import com.ecf.dto.Result;
import com.ecf.entity.VoucherOrder;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 虎哥
 * @since 2021-12-22
 */
public interface IVoucherOrderService extends IService<VoucherOrder> {
    Result createVoucherOrder(Long voucherId);
    Result seckillVoucher(Long voucherId);
}
