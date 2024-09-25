package com.ecf.utils;

public interface ILock {
    public void unlock();
    // 尝试获取锁（超出timeoutSec会自动释放锁）
    boolean tryLock(long timeoutSec);
}
