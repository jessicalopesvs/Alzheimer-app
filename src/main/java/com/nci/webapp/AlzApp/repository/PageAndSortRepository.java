package com.nci.webapp.AlzApp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface PageAndSortRepository<T, ID> extends CrudRepository<T,ID> {


    Iterable<T> findAll(Sort sort);
    Page<T> findAll(Pageable pageable);
}
