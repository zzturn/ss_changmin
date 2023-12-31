package com.changmin.cm_backend.service;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.changmin.cm_backend.config.common.pojo.PageResult;
import com.changmin.cm_backend.config.mybatis.core.dataobject.BaseDO;
import com.changmin.cm_backend.config.security.config.SecurityProperties;
import com.changmin.cm_backend.config.security.util.SecurityFrameworkUtils;
import com.changmin.cm_backend.exceptions.ErrorCodeConstants;
import com.changmin.cm_backend.mapper.*;
import com.changmin.cm_backend.model.convertor.*;
import com.changmin.cm_backend.model.dto.wuxing.*;
import com.changmin.cm_backend.model.enums.WuxingTypeEnum;
import com.changmin.cm_backend.model.pojo.*;
import com.changmin.cm_backend.util.LoginUserUtil;
import com.changmin.cm_backend.util.WebFrameworkUtils;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class WuxingServiceImpl implements WuxingService {
  @Resource private WuxingMapper wuxingMapper;

  @Resource private SecurityProperties securityProperties;

  public static final String ADMIN_TOKEN = "wuxing";

  /**
   * 根据 id 获取屋形，不区分官方还是用户保存 todo 是否需要区分权限
   *
   * @param id
   * @return
   */
  @Override
  public WuxingItemDto get(String id) {
    return WuxingConvert.INSTANCE.convertItem(wuxingMapper.selectWuxingById(id));
  }

  /**
   * 管理员批量上传 todo 需要验证token
   *
   * @param dto
   * @return
   */
  @Override
  public List<String> createBatch(List<WuxingCreateReqDto> dto) {
    String token =
        SecurityFrameworkUtils.obtainAuthorization(
            WebFrameworkUtils.getRequest(), securityProperties.getTokenHeader());
    if (!ADMIN_TOKEN.equals(token)) {
      throw ErrorCodeConstants.FORBIDDEN;
    }
    List<WuxingDO> dataDo = WuxingConvert.INSTANCE.convertCreateList(dto);
    dataDo.forEach(x -> x.setType(WuxingTypeEnum.OFFICIAL.getValue()));
    wuxingMapper.insertBatch(dataDo);
    List<String> ids = dataDo.stream().map(WuxingDO::getId).collect(Collectors.toList());
    return ids;
  }

  /**
   * 用户上传自定义屋形，todo 需要判断是否与原屋形需要有关联关系
   *
   * @param dto
   * @return
   */
  @Override
  public String saveWuxing(WuxingCreateReqDto dto) {
    WuxingDO wuxingDO = WuxingConvert.INSTANCE.convert(dto);
    wuxingDO.setType(WuxingTypeEnum.CUSTOM.getValue());
    wuxingMapper.insert(wuxingDO);
    return wuxingDO.getId();
  }

  @Override
  public void deleteSavedWuxing(List<String> ids) {
    String userId = LoginUserUtil.getLoginUserIdOrElseThrow();
    wuxingMapper.delete(
        new LambdaQueryWrapper<WuxingDO>().in(WuxingDO::getId, ids).eq(BaseDO::getCreator, userId));
  }

  /**
   * 获取用户保存的屋形
   *
   * @return
   */
  @Override
  public List<WuxingItemDto> getSavedWuxingList(WuxingListReqDto dto) {
    String userId = LoginUserUtil.getLoginUserIdOrElseThrow();
    LambdaQueryWrapper<WuxingDO> queryWrapper =
        new LambdaQueryWrapper<WuxingDO>()
            .eq(WuxingDO::getCreator, userId)
            .eq(WuxingDO::getType, WuxingTypeEnum.CUSTOM)
            .orderByDesc(WuxingDO::getCreateTimeUtc);
    if (dto.getIgnore3DData()) {
      queryWrapper.select(WuxingDO.class, info -> !info.getColumn().equals("data"));
    }
    List<WuxingDO> wuxingDOS = wuxingMapper.selectList(queryWrapper);
    List<WuxingItemDto> res = WuxingConvert.INSTANCE.convertList(wuxingDOS);
    return res;
  }

  @Override
  public void update(WuxingUpdateReqDto dto) {
    String token =
        SecurityFrameworkUtils.obtainAuthorization(
            WebFrameworkUtils.getRequest(), securityProperties.getTokenHeader());
    if (!ADMIN_TOKEN.equals(token)) {
      throw ErrorCodeConstants.FORBIDDEN;
    }
    wuxingMapper.updateById(WuxingConvert.INSTANCE.convert(dto));
  }

  @Override
  public void deleteBatch(List<String> id) {
    String token =
        SecurityFrameworkUtils.obtainAuthorization(
            WebFrameworkUtils.getRequest(), securityProperties.getTokenHeader());
    if (!ADMIN_TOKEN.equals(token)) {
      throw ErrorCodeConstants.FORBIDDEN;
    }
    wuxingMapper.deleteBatchIds(id);
  }

  /**
   * 官方屋形分页
   *
   * @param dto
   * @return
   */
  @Override
  public PageResult<WuxingItemDto> getPage(WuxingPageReqDto dto) {
    LambdaQueryWrapper<WuxingDO> queryWrapper =
        new LambdaQueryWrapper<WuxingDO>()
            .eq(WuxingDO::getType, WuxingTypeEnum.OFFICIAL.getValue())
            .eq(Objects.nonNull(dto.getCanCustom()), WuxingDO::getCanCustom, dto.getCanCustom())
            .in(CollUtil.isNotEmpty(dto.getRoof()), WuxingDO::getRoof, dto.getRoof())
            .in(CollUtil.isNotEmpty(dto.getArea()), WuxingDO::getArea, dto.getArea())
            .in(CollUtil.isNotEmpty(dto.getYongTu()), WuxingDO::getYongTu, dto.getYongTu())
            .in(CollUtil.isNotEmpty(dto.getRoomCount()), WuxingDO::getRoomCount, dto.getRoomCount())
            .in(CollUtil.isNotEmpty(dto.getFengGe()), WuxingDO::getFengGe, dto.getFengGe())
            .isNotNull(!Boolean.TRUE.equals(dto.getDisableCompleteFilter()), WuxingDO::getCoverUrl)
            .isNotNull(
                !Boolean.TRUE.equals(dto.getDisableCompleteFilter()),
                WuxingDO::getXianChangZhaoPianUrl)
            .isNotNull(
                !Boolean.TRUE.equals(dto.getDisableCompleteFilter()),
                WuxingDO::getJianZhuTuMianUrl);
    if (Objects.nonNull(dto.getSortField())) {
      queryWrapper.orderBy(true, dto.getSortAsc(), dto.getSortField().getField());
    }
    if (dto.getIgnore3DData()) {
      queryWrapper.select(WuxingDO.class, info -> !info.getColumn().equals("data"));
    }
    PageResult<WuxingDO> pageResult = wuxingMapper.selectPage(dto, queryWrapper);
    return new PageResult<>(
        WuxingConvert.INSTANCE.convertList(pageResult.getList()), pageResult.getTotal());
  }
}
