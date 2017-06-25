package smb.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import smb.service.dao.ResultDao;
import smb.service.entity.ResultDO;

@Service
public class ResultService {
    @Autowired
    RestTemplate           restTemplate;
    
    @Autowired
    private ResultDao          dao;

    @Transactional
    public void insertResult(List<ResultDO> list) {
        for(ResultDO entity:list){
            insert(entity);
        }
    }


    public List<ResultDO> findList(Date wantDate) {
        return dao.findList(wantDate);
    }

    public int insert(ResultDO entity) {
        Assert.notNull(entity, "entity is null");
        return dao.insert(entity);
    }

    public ResultDO findById(int id) {
        return dao.findById(id);
    }
}
