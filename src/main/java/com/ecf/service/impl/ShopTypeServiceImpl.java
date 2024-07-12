package com.ecf.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.ecf.dto.Result;
import com.ecf.entity.ShopType;
import com.ecf.mapper.ShopTypeMapper;
import com.ecf.service.IShopTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import static com.ecf.utils.RedisConstants.CACHE_SHOP_TYPE;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 虎哥
 * @since 2021-12-22
 */
@Service
public class ShopTypeServiceImpl extends ServiceImpl<ShopTypeMapper, ShopType> implements IShopTypeService {
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Override
    public Result queryType() {
        String key = CACHE_SHOP_TYPE;
        String types = stringRedisTemplate.opsForValue().get(key);
        if(StrUtil.isNotBlank(types)){
            List<ShopType> shopTypeList = JSONUtil.toList(types, ShopType.class);
            return Result.ok(shopTypeList);
        }
        List<ShopType> shopTypeList = query().orderByAsc("sort").list();
        stringRedisTemplate.opsForValue().set(key,JSONUtil.toJsonStr(shopTypeList));
        if(shopTypeList == null) return Result.fail("no exists");
        return Result.ok(shopTypeList);
    }
}
