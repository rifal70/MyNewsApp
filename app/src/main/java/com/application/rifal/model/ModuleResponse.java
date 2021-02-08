package com.application.rifal.myapplication.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ModuleResponse{

    @SerializedName("sources")
    private List<ModuleItem> moduleDetail;

    public List<ModuleItem> getModuleDetail(){
        return moduleDetail;
    }


    @Override
    public String toString(){
        return
                "ModuleResponse{" +
                        "sources = '" + moduleDetail + '\'' +
                        "}";
    }
}