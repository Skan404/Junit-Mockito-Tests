package org.example;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MageRepository {
    private Map<String, Mage> mages = new HashMap<>();

    public Optional<Mage> find(String name) {
        return Optional.ofNullable(mages.get(name));
    }

    public void delete(String name) {
        if (!mages.containsKey(name)) {
            throw new IllegalArgumentException("Mage not found");
        }
        mages.remove(name);
    }

    public void save(Mage mage) {
        if (mages.containsKey(mage.getName())) {
            throw new IllegalArgumentException("Mage already exists");
        }
        mages.put(mage.getName(), mage);
    }
}
