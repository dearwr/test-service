package com.hchc.testservice.service;

import com.alibaba.fastjson.JSON;
import com.hchc.testservice.dao.BranchMallDao;
import com.hchc.testservice.model.BranchInfo;
import com.hchc.testservice.pack.Output;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by wangrong 2020/5/13
 */
@Service
@Slf4j
public class RemoteService {

    public static final String LAB2_ALARM_SERVICE_URL = "http://lab2.51hchc.com:7676";

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private BranchMallDao branchMallDao;


    public Output pushBranchInfos() {
        String url = LAB2_ALARM_SERVICE_URL + "/mallConsole/pushBranchInfos";
        List<BranchInfo> branchInfos = branchMallDao.queryBranchInfos();

        Output response = restTemplate.postForObject(url, branchInfos, Output.class);
        log.info("[pushBranchInfos] response :{}", JSON.toJSONString(response));
        return response;
    }
}
