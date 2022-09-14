package fr.cornysneeze.aircraftmod.worldgen.ores;

import net.minecraftforge.common.ForgeConfigSpec;

public class OresConfig {


    public static ForgeConfigSpec.IntValue OVERWORLD_VEINSIZE;
    public static ForgeConfigSpec.IntValue OVERWORLD_AMOUNT;

    public static void registerCommonConfig(ForgeConfigSpec.Builder COMMON_BUILDER) {
        COMMON_BUILDER.comment("Settings for ore generation").push("ores");


        OVERWORLD_VEINSIZE = COMMON_BUILDER
                .comment("Veinsize of our ore in the overworld dimension")
                .defineInRange("overworldVeinsize", 9, 1, Integer.MAX_VALUE);
        OVERWORLD_AMOUNT = COMMON_BUILDER
                .comment("Amount of veines of our ore in the overworld dimension")
                .defineInRange("overworldAmount", 3, 1, Integer.MAX_VALUE);

        COMMON_BUILDER.pop();
    }

}