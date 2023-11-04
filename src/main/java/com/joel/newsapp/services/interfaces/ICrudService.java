package com.joel.newsapp.services.interfaces;

import com.joel.newsapp.exceptions.NotFoundException;

public interface ICrudService <T, TPostDTO, TEditDTO, ID> {
    public T save(TPostDTO dto) throws Exception;
    public T getById(ID id) throws NotFoundException;
    public T edit(TEditDTO dto) throws Exception;
    public String deleteById(ID id);
}
