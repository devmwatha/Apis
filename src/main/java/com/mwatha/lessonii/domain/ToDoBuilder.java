package com.mwatha.lessonii.domain;

public class ToDoBuilder {
    private static ToDoBuilder instance = new ToDoBuilder();
    private String id = null;
    private String description = "";

    private ToDoBuilder() {
    }

    public static ToDoBuilder create() {
        return instance;
    }

    public ToDoBuilder withDescription(String description)
    {
        this.description = description;
        return instance;
    }

    public ToDoBuilder withId(String id){
        this.id = id;
        return instance;
    }

    public ToDO build(){
        ToDO result = new ToDO(this.description);
        if (id != null)
            result.setId(id);
        return result;
    }
}
