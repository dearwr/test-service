package com.hchc.testservice.dao;

import com.alibaba.fastjson.JSON;
import com.hchc.testservice.model.BranchInfo;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by wangrong 2020/5/19
 */
@Repository
public class BranchMallDao extends FlipBaseDao {

    public List<BranchInfo> queryBranchInfos() {
        String sql = "SELECT b.id as branchId, IFNULL(h.`name`,h.`code`) hName, b.`name` as bName, b.address, m.f_mall, m.f_type, m.f_config from t_branch_mall m" +
                " LEFT JOIN t_headquarter h on m.f_hqid = h.id" +
                " LEFT JOIN t_branch b on m.f_branchid = b.id" +
                " WHERE m.f_enable = 1 and f_config <> '' and f_hqid <> 199";
        return jdbcTemplate.query(sql, this::queryMapping);
    }

    private BranchInfo queryMapping(ResultSet set, int i) throws SQLException {
        BranchInfo bInfo = JSON.parseObject(set.getString("f_config"), BranchInfo.class);
        bInfo.setBranchId(set.getLong("branchId"));
        bInfo.setBrandName(set.getString("hName"));
        bInfo.setBranchName(set.getString("bName"));
        bInfo.setAddress(set.getString("address"));
        bInfo.setMark(set.getString("f_mall"));
        bInfo.setPushMethod(set.getString("f_type"));
        return bInfo;
    }
}
