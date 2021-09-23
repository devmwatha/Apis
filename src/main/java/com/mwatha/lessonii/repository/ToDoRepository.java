package com.mwatha.lessonii.repository;

import com.mwatha.lessonii.domain.ToDO;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class ToDoRepository implements CommonRepository<ToDO> {
    private final Map<String, ToDO> toDos = new HashMap<>();

    @Override
    public ToDO save(ToDO domain) {
        ToDO result = toDos.get(domain.getId());
        if (result != null) {
            result.setModified(LocalDateTime.now());
            result.setDescription(domain.getDescription());
            result.setCompleted(domain.isCompleted());
            domain = result;
        }
        toDos.put(domain.getId(), domain);
        return toDos.get(domain.getId());
    }

    @Override
    public Iterable<ToDO> save(Collection<ToDO> domain) {
        domain.forEach(this::save);
        return findAll();
    }

    @Override
    public void delete(ToDO domain) {
        toDos.remove(domain.getId());
    }

    @Override
    public ToDO findById(String id) {
        return toDos.get(id);
    }

    @Override
    public Iterable<ToDO> findAll() {
        return toDos.entrySet().stream().sorted(entryComparator).map(Map.Entry::getValue).collect(Collectors.toList());
    }

    private final Comparator<Map.Entry<String, ToDO>> entryComparator = Comparator.comparing((Map.Entry<String, ToDO> o) -> o.getValue().getCreated());
}
