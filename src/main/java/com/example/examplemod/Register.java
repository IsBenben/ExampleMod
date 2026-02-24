package com.example.examplemod;

import com.mojang.logging.LogUtils;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.bus.BusGroup;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

import static com.example.examplemod.ExampleMod.MODID;

// 注册类，统一管理模组元素的注册
public abstract class Register {
    // 获取一个slf4j日志记录器
    private static final Logger LOGGER = LogUtils.getLogger();

    // 创建Forge的延迟注册器
    // Forge会在模组加载的生命周期中自动完成注册，无需手动处理事件
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(
            ForgeRegistries.BLOCKS, // 使用方块注册表
            MODID // 方块注册的命名空间
    );
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(
            ForgeRegistries.ITEMS, // 使用物品注册表
            MODID // 物品注册的命名空间
    );

    // 预先定义要注册的方块和物品
    private static final RegistryObject<Block> OBSIDIAN_BLOCK = // 将注册的方块对象保存到OBSIDIAN_BLOCK
            BLOCKS.register(
                    "obsidian_block", // 方块注册名称的路径
                    () -> new Block(
                            BlockBehaviour.Properties.ofFullCopy(Blocks.OBSIDIAN) // 复制黑曜石的方块属性
                                    .setId(ResourceKey.create( // 设置物品的资源键，需与其注册名称匹配
                                            ForgeRegistries.BLOCKS.getRegistryKey(),
                                            Identifier.fromNamespaceAndPath(MODID, "obsidian_block")
                                    ))
                    )
            );
    private static final RegistryObject<Item> OBSIDIAN_BLOCK_ITEM =
            ITEMS.register(
                    "obsidian_block", // 物品注册名称的路径
                    () -> new BlockItem(
                            OBSIDIAN_BLOCK.get(),
                            new Item.Properties() // 物品属性
                                    .setId(ResourceKey.create( // 设置物品的资源键，需与其注册名称匹配
                                            ForgeRegistries.ITEMS.getRegistryKey(),
                                            Identifier.fromNamespaceAndPath(MODID, "obsidian_block")
                                    ))
                    )
            );
    private static final RegistryObject<Item> OBSIDIAN_INGOT =
            ITEMS.register(
                    "obsidian_ingot", // 物品注册名称的路径
                    () -> new Item(
                            new Item.Properties() // 物品属性
                                    .setId(ResourceKey.create( // 设置物品的资源键，需与其注册名称匹配
                                            ForgeRegistries.ITEMS.getRegistryKey(),
                                            Identifier.fromNamespaceAndPath(MODID, "obsidian_ingot")
                                    ))
                    )
            );

    public static void register(BusGroup busGroup) {
        // 记录相关日志（可选但推荐）
        LOGGER.debug("Registering mod blocks and items");
        // 将延迟注册器绑定到事件总线，使其相应注册事件
        BLOCKS.register(busGroup);
        ITEMS.register(busGroup);
    }
}
