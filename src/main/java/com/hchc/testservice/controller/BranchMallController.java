package com.hchc.testservice.controller;

import com.hchc.testservice.dao.BranchMallDao;
import com.hchc.testservice.pack.Output;
import com.hchc.testservice.service.RemoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wangrong 2020/5/18
 */
@RestController
@RequestMapping("/mallConsole")
@Slf4j
public class BranchMallController {

    @Autowired
    private BranchMallDao branchMallDao;

    @Autowired
    private RemoteService remoteService;

    @GetMapping("/queryBranchInfos")
    public Output queryBranchInfos() {
        log.info("[queryBranchInfos]");
        return Output.ok(branchMallDao.queryBranchInfos());
    }

    @GetMapping("/pushBranchInfos")
    public Output pushBranchInfos() {
        log.info("[pushBranchInfos]");
        return remoteService.pushBranchInfos();
    }

}
