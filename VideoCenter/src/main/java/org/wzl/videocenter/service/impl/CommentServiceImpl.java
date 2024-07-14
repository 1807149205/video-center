package org.wzl.videocenter.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.wzl.videocenter._do.Comment;
import org.wzl.videocenter.service.CommentService;
import org.wzl.videocenter.mapper.CommentMapper;
import org.springframework.stereotype.Service;

/**
* @author 卫志龙
* @description 针对表【t_comment】的数据库操作Service实现
* @createDate 2024-07-14 18:23:09
*/
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
    implements CommentService{

}




