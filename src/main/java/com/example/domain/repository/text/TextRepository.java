package com.example.domain.repository.text;

import com.example.domain.model.Text;

/**
 * Created by shimizukazuki on 2014/09/26.
 */
public interface TextRepository {

    boolean create(Text text);

    Text findOne(String id);
}
