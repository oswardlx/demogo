package com.example.service;

import com.example.entity.Man;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ManService {
    public List<Man> getManList(){
        List<Man> list = new ArrayList<>();
        Man man = new Man();
        for(int x = 0;x<3;x++){
            man.setAge(x+10+"");
            man.setId(x+"");
            man.setName(x+"fffff");
            list.add(man);
        }
        return list;
    }
}
