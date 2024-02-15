package com.learn.springboot.service;

import com.learn.springboot.Entity.Children;
import com.learn.springboot.Entity.Parent;
import com.learn.springboot.model.ParentRequest;
import com.learn.springboot.repository.ParentRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class ParentService {
    ParentRepository mParentRepository;

    public ParentService(ParentRepository pParentRepository) {
        this.mParentRepository = pParentRepository;
    }

    public Parent createParent(ParentRequest pParentRequest) {
        Parent lParent = new Parent();
        lParent.setFirstname(pParentRequest.getFirstName());
        lParent.setLastname(pParentRequest.getLastName());

        return mParentRepository.save(lParent);
    }

    public Parent getParent() {

        Parent lParent = new Parent();
        lParent.setParent_id(101);
        lParent.setFirstname("abhi");
        lParent.setLastname("singh");

        List<Children> lList = new ArrayList<>();
        Children lChildren = new Children();
        lChildren.setChild_id(201);
        lChildren.setFirstname("ravi");
        lChildren.setLastname("singh");
        lChildren.setParent(lParent);
        lList.add(lChildren);
        lParent.setChildren(lList);

        Optional<Parent> lParentOp = mParentRepository.findByChildren(lChildren);
        return lParentOp.get();
    }

}
