package com.example.domain.repository.image;

import com.example.domain.model.Image;

/**
 * Created by shimizukazuki on 2014/09/26.
 */
public interface ImageRepository {

    boolean create(Image image);

    Image findOne(String id);
}
