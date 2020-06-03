package com.kc.asne.asne.util.parser;

import com.google.gson.Gson;
import com.kc.asne.asne.util.parser.models.MultiBlock;
import com.kc.asne.asne.util.parser.models.ParsableIndex;
import com.kc.asne.asne.util.parser.models.PressRecipe;
import com.kc.asne.asne.util.parser.models.RocketeersCraftingRecipe;
import com.kc.asne.base.general.resource.ResourceHelper;
import net.minecraft.util.NonNullList;

import java.util.ArrayList;
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
        ArrayList<String> paths = readIndex("rocketeerscraftingrecipes");
        for (final String path : paths) {
            String file = new String(ResourceHelper.getResourceAsBytes("mod/rocketeerscraftingrecipes/" + path));
            recipes.add(getRecipe(file));
        }
    }

    public static MultiBlock getMultiBlock(String jsonString) {
        Gson gson = new Gson();
        return gson.fromJson(jsonString, MultiBlock.class);
    }


    public static void loadMultiBlocks() {
        multiBlocks = new HashMap<>();
        ArrayList<String> paths = readIndex("multiblocks");
        for (final String path : paths) {
            String file = new String(ResourceHelper.getResourceAsBytes("mod/multiblocks/" + path));
            MultiBlock mb = getMultiBlock(file);
            multiBlocks.put(mb.controller.type, mb);
        }
    }

    public static void loadPressRecipes() {
        pressRecipes = new HashMap<>();
        ArrayList<String> paths = readIndex("pressrecipes");
        for (final String path : paths) {
            String file = new String(ResourceHelper.getResourceAsBytes("mod/pressrecipes/" + path));
            PressRecipe pressRecipe = getPressRecipe(file);
            pressRecipes.put(pressRecipe.in, pressRecipe);
        }
    }

    private static PressRecipe getPressRecipe(String jsonString) {
        Gson gson = new Gson();
        return gson.fromJson(jsonString, PressRecipe.class);
    }

    private static ArrayList<String> readIndex(String indexFor) {
        String contents = new String(ResourceHelper.getResourceAsBytes("mod/index/" + indexFor + ".json"));
        ParsableIndex index = new Gson().fromJson(contents, ParsableIndex.class);
        return index.paths;
    }
}
