package com.lpl.security.service.impl;

import com.lpl.security.entity.Blob;
import com.lpl.security.service.IBlogService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
@Service
public class IblogserviceImpl implements IBlogService {
    private List<Blob>list=new ArrayList<>();
    public IblogserviceImpl(){
        list.add(new Blob(1L,"spring in action","good!"));
        list.add(new Blob(2L,"spring boot in action","nice!"));
    }
    @Override
    public List<Blob> getBlobs() {
        return list;
    }

    @Override
    public void deleteBlob(Long id) {
        Iterator iterator=list.iterator();
        while (iterator.hasNext()){
            Blob blob= (Blob) iterator.next();
            if(blob.getId()==id){
                iterator.remove();
            }
        }
    }
}
