package com.shida.labchecksys.mapper;

import com.shida.labchecksys.pojo.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorityMapper extends JpaRepository<Authority, Integer> {

    //根据功能名称查询所有信息
    public List<Authority> findAllByFunctionName(String functionName);

    //根据功能名称删除指定信息
    public void deleteByFunctionName(String functionName);

    //根据功能Id查询所有信息
    public List<Authority> findAllByFunctionId(int functionId);

}
