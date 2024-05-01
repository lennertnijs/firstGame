package com.mygdx.game.V2.Inventory;

import java.util.*;
import java.util.stream.Collectors;

public final class ItemTemplateRepository {

    private final Map<String, ItemTemplate> map;

    public ItemTemplateRepository(){
        this.map = new HashMap<>();
    }

    public ItemTemplateRepository(List<ItemTemplate> templates){
        Objects.requireNonNull(templates, "List is null.");
        if(templates.contains(null))
            throw new NullPointerException("List contains null.");
        checkForDuplicates(templates);
        this.map = templates.stream().collect(Collectors.toMap(ItemTemplate::name, template -> template));
    }

    private void checkForDuplicates(List<ItemTemplate> templates){
        Set<String> unique = new HashSet<>();
        for(ItemTemplate template : templates)
            if(!unique.add(template.name()))
                throw new IllegalArgumentException("Duplicate Item Template found.");
    }

    public Map<String, ItemTemplate> getMap(){
        return new HashMap<>(map);
    }

    public void add(ItemTemplate template){
        Objects.requireNonNull(template, "Item template is null");
        if(map.containsKey(template.name()))
            throw new IllegalArgumentException("Duplicate item template found.");
        map.put(template.name(), template);
    }
}
