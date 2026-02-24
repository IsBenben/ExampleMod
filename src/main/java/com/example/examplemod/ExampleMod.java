package com.example.examplemod;

import com.mojang.logging.LogUtils;
import net.minecraftforge.eventbus.api.bus.BusGroup;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// 主类以@Mod注解
@Mod(ExampleMod.MODID)
public class ExampleMod {
    // 定义模组ID，可以在任何地方引用
    public static final String MODID = "examplemod";
    // 获取一个slf4j日志记录器
    private static final Logger LOGGER = LogUtils.getLogger();

    // Forge加载模组时会传入模组加载上下文
    public ExampleMod(FMLJavaModLoadingContext context) {
        // 获取事件总线
        BusGroup busGroup = context.getModBusGroup();
        // 进行注册
        Register.register(busGroup);
    }
}