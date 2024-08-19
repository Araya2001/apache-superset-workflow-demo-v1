package org.aaj.example.postgresqletlscheduler.service;

import java.util.List;
import java.util.Optional;

public interface DbCrudOperatorService<PersistingObject, Id> {

    Optional<PersistingObject> get(Id id);

    PersistingObject create(PersistingObject persistingObject);

    PersistingObject update(PersistingObject persistingObject);

    void delete(Id id);

    List<PersistingObject> get(List<Id> ids);

    List<PersistingObject> create(List<PersistingObject> persistingObjects);

    List<PersistingObject> update(List<PersistingObject> persistingObjects);

    void delete(List<Id> ids);

    List<PersistingObject> getAll();
}