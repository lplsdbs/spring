package com.lpl.security.service;

import com.lpl.security.entity.Blob;

import java.util.List;

public interface IBlogService {
    List<Blob>getBlobs();
    void deleteBlob(Long id);
}
