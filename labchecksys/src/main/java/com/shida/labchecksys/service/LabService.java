package com.shida.labchecksys.service;


import com.shida.labchecksys.common.JsonResponse;
import com.shida.labchecksys.pojo.Lab;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LabService {



    JsonResponse addLab(Lab lab);

    JsonResponse deleteLab(long LabId);

    JsonResponse updateLab(Lab lab);

    JsonResponse selectLab(long LabId);

    String findLabNameByLabSaferId(long labSaferId);

    long findLabSaferIdByLabName(String labName);

    long findLeadrIdByLabName(String labName);

    long findSysIdByLabName(String labName);

    /**
     *
     * //2021/1/6修改
     * //2021/1/6修改
     * //2021/1/6修改
     * //2021/1/6修改
     */

    JsonResponse labApply(long labId, String labUserId,long labSaferId);

    List<Long> findLabUserIdByLabName(String labName);

}
