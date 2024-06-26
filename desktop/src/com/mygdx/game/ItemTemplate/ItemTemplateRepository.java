package com.mygdx.game.ItemTemplate;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Represents a repository in which all the item templates are stored.
 * Item templates with the same name are considered the same item in this repository,
 * as the name of the item template serves as its key.
 */
public final class ItemTemplateRepository {

    private final Map<String, ItemTemplate> map;

    /**
     * Creates a new empty {@link ItemTemplateRepository}.
     */
    public ItemTemplateRepository(){
        this.map = new HashMap<>();
    }

    /**
     * Creates a new {@link ItemTemplateRepository}.
     * @param templates The list of {@link ItemTemplate}s. Cannot be null. Cannot contain null.
     *                  Cannot contain two item templates with the same name.
     *
     * @throws IllegalArgumentException If the list contains two item templates with the same name.
     */
    public ItemTemplateRepository(List<ItemTemplate> templates){
        Objects.requireNonNull(templates, "List is null.");
        if(templates.contains(null))
            throw new NullPointerException("List contains null.");
        checkForDuplicates(templates);
        this.map = templates.stream().collect(Collectors.toMap(ItemTemplate::name, template -> template));
    }

    /**
     * Checks for item templates with the same name.
     * @param templates The list of item templates. Cannot be null. Cannot contain null.
     *
     * @throws IllegalArgumentException If the list contains two item templates with the same name.
     */
    private void checkForDuplicates(List<ItemTemplate> templates){
        Set<String> unique = new HashSet<>();
        for(ItemTemplate template : templates)
            if(!unique.add(template.name()))
                throw new IllegalArgumentException("Duplicate Item Template found.");
    }

    /**
     * Fetches and returns the item template with the given name.
     * @param name The name. Cannot be null.
     *
     * @return The item template.
     * @throws NoSuchElementException If no such element exists.
     */
    public ItemTemplate getFromName(String name){
        Objects.requireNonNull(name, "Name is null.");
        if(!map.containsKey(name))
            throw new NoSuchElementException("No item template found.");
        return map.get(name);
    }

    /**
     * Adds the given item template to this repository.
     * @param template The item template. Cannot be null.
     *                 Cannot have the same name as an entry already present in the repository.
     */
    public void add(ItemTemplate template){
        Objects.requireNonNull(template, "Item template is null");
        if(map.containsKey(template.name()))
            throw new IllegalArgumentException("Duplicate item template found.");
        map.put(template.name(), template);
    }
}
