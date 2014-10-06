package com.example.domain.repository.image;

import com.example.domain.model.Image;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by shimizukazuki on 2014/09/26.
 */
public interface ImageRepository {

    boolean create(Image image);

    Image findOne(@Param("id") String id);
}
