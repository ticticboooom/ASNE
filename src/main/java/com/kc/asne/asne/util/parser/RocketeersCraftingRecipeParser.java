package com.kc.asne.asne.util.parser;

import com.google.gson.Gson;
import net.minecraft.util.NonNullList;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;

public class RocketeersCraftingRecipeParser {
    public static NonNullList<RocketeersCraftingRecipe> recipes;
    public static RocketeersCraftingRecipe getRecipe(String jsonString) {
        Gson gson = new Gson();
        return gson.fromJson(jsonString, RocketeersCraftingRecipe.class);
    }
    public static void loadRecipes() {
        recipes = NonNullList.create();
        try {
            URL folder = RocketeersCraftingRecipeParser.class.getClassLoader().getResource("mod/rocketeerscraftingrecipes");
            File[] files = new File(folder.getPath()).listFiles();
            for (int i = 0; i < files.length; i ++){
                recipes.add(getRecipe(new String(Files.readAllBytes(files[i].toPath()))));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
