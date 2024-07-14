package org.wzl.videocenter.utils;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;

/**
 * @author: 卫志龙
 * @date: 2024年07月14日 17:09
 */
public class IdGen {

    public static String getId() {
        return IdWorker.get32UUID();
    }

}
