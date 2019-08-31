package ru.job4jhibernate.admanager.dao;

import ru.job4jhibernate.admanager.models.Drive;

import java.util.List;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public interface DaoDrive {

    boolean addDrive(Drive drive);

    Drive findDriveById(String id);

    List<Drive> findAllDrive();
}
