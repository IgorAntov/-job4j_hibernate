package ru.job4jhibernate.admanager.dao;

import ru.job4jhibernate.admanager.models.Brand;

import java.util.List;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public interface DaoBrand {

    boolean addBrand(Brand brand);

    Brand findBrandById(String id);

    List<Brand> findAllBrand();
}
