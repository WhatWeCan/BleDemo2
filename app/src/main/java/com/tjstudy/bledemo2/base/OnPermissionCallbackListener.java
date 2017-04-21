package com.tjstudy.bledemo2.base;

import java.util.List;

/**
 * 权限处理接口
 */
public interface OnPermissionCallbackListener {
    void onGranted();

    void onDenied(List<String> deniedPermissions);
}
