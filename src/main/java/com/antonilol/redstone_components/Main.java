/*
 * Copyright (c) 2021 Antoni Spaanderman
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.antonilol.redstone_components;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Main implements ModInitializer {
	
	public static final Block CONFIGURABLE_REDSTONE_BLOCK = new ConfigurableRedstoneBlock(
		FabricBlockSettings.of(Material.METAL, MapColor.BRIGHT_RED)
		.requiresTool()
		.strength(5.0F, 6.0F)
		.sounds(BlockSoundGroup.METAL)
	);
	
	public static final String CONFIGURABLE_REDSTONE_BLOCK_NAME = "configurable_redstone_block";

	public static final Block MEMORY_CELL_BLOCK = new MemoryCellBlock(
		FabricBlockSettings.of(Material.DECORATION)
		.breakInstantly()
		.sounds(BlockSoundGroup.WOOD)
	);
	
	public static BlockEntityType<MemoryCellBlockEntity> MEMORY_CELL_BLOCK_ENTITY;
	
	@Deprecated(forRemoval=true)
	public static BlockEntityType<ConfigurableRedstoneBlockEntity> CONFIGURABLE_REDSTONE_BLOCK_ENTITY;
	
	public static final String MEMORY_CELL_NAME = "memory_cell";
	
	public static final String MOD_ID = "redstone_components";
	
	public static final String VERSION = "0.1.0"; // updated by updateVersion script with sed :)
	
	@Override
	public void onInitialize() {
		// memory cell
		Registry.register(Registry.BLOCK, new Identifier(MOD_ID, MEMORY_CELL_NAME), MEMORY_CELL_BLOCK);
		Registry.register(
			Registry.ITEM, new Identifier(MOD_ID, MEMORY_CELL_NAME),
			new BlockItem(MEMORY_CELL_BLOCK, new FabricItemSettings().group(ItemGroup.REDSTONE))
		);
		MEMORY_CELL_BLOCK_ENTITY = Registry.register(
			Registry.BLOCK_ENTITY_TYPE,
			new Identifier(MOD_ID, MEMORY_CELL_NAME),
			FabricBlockEntityTypeBuilder.create(MemoryCellBlockEntity::new, MEMORY_CELL_BLOCK).build()
		);
		
		// configurable redstone block
		Registry.register(Registry.BLOCK, new Identifier(MOD_ID, CONFIGURABLE_REDSTONE_BLOCK_NAME), CONFIGURABLE_REDSTONE_BLOCK);
		Registry.register(
			Registry.ITEM, new Identifier(MOD_ID, CONFIGURABLE_REDSTONE_BLOCK_NAME),
			new BlockItem(CONFIGURABLE_REDSTONE_BLOCK, new FabricItemSettings().group(ItemGroup.REDSTONE))
		);
		/*
		CONFIGURABLE_REDSTONE_BLOCK_ENTITY = Registry.register(
			Registry.BLOCK_ENTITY_TYPE,
			new Identifier(MOD_ID, CONFIGURABLE_REDSTONE_BLOCK_NAME),
			FabricBlockEntityTypeBuilder.create(ConfigurableRedstoneBlockEntity::new, CONFIGURABLE_REDSTONE_BLOCK).build()
		);
		*/
	}
}
