package com.kc.asne.asne.util.parser;

import com.google.gson.Gson;
import net.minecraft.util.NonNullList;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.HashMap;

public class CustomParser {
    public static NonNullList<RocketeersCraftingRecipe> recipes;
    public static HashMap<String, MultiBlock> multiBlocks;
    public static HashMap<String, PressRecipe> pressRecipes;

    public static void loadAll() {
        loadMultiBlocks();
        loadPressRecipes();
        loadRecipes();
    }

    public static RocketeersCraftingRecipe getRecipe(String jsonString) {
        Gson gson = new Gson();
        return gson.fromJson(jsonString, RocketeersCraftingRecipe.class);
    }
    public static void loadRecipes() {
        recipes = NonNullList.create();
        try {
            URL folder = CustomParser.class.getClassLoader().getResource("mod/rocketeerscraftingrecipes");
            File[] files = new File(folder.getPath()).listFiles();
            for (int i = 0; i < files.length; i++) {
                recipes.add(getRecipe(new String(Files.readAllBytes(files[i].toPath()))));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static MultiBlock getMultiBlock(String jsonString) {
        Gson gson = new Gson();
        return gson.fromJson(jsonString, MultiBlock.class);
    }


    public static void loadMultiBlocks() {
        multiBlocks = new HashMap<>();
        try {
            URL folder = CustomParser.class.getClassLoader().getResource("mod/multiblocks");
            File[] files = new File(folder.getPath()).listFiles();
            for (int i = 0; i < files.length; i ++){
                MultiBlock mb  = getMultiBlock(new String(Files.readAllBytes(files[i].toPath())));
                multiBlocks.put(mb.controller.type, mb);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void loadPressRecipes() {
        pressRecipes = new HashMap<>();
        try {
            URL folder = CustomParser.class.getClassLoader().getResource("mod/pressrecipes");
            File[] files = new File(folder.getPath()).listFiles();
            for (int i = 0; i < files.length; i++) {
                PressRecipe pressRecipe = getPressRecipe(new String(Files.readAllBytes(files[i].toPath())));
                pressRecipes.put(pressRecipe.in, pressRecipe);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static PressRecipe getPressRecipe(String jsonString) {
        Gson gson = new Gson();
        return gson.fromJson(jsonString, PressRecipe.class);
    }
}
