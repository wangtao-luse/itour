package com.itour.service;

import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.constant.Constant;
import com.itour.exception.BaseException;
import com.itour.model.vo.PageInfo;
import com.itour.model.work.CommentLike;
import com.itour.model.work.WorkInfo;
import com.itour.persist.CommentLikeMapper;
import com.itour.service.CommentLikeService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2021-07-09
 */
@Service
public class CommentLikeService extends ServiceImpl<CommentLikeMapper, CommentLike> {
}
