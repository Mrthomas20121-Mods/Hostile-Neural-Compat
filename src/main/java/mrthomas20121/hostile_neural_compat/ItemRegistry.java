package mrthomas20121.hostile_neural_compat;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;

import javax.annotation.Nonnull;

@Mod.EventBusSubscriber(modid = HostileNeuralCompat.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ItemRegistry {

    public static DeferredRegister<Item> ITEMS = DeferredRegister.create(Item.class, HostileNeuralCompat.MOD_ID);

    public static ItemGroup group = new ItemGroup("hostile_neural_compat") {

        @Nonnull
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(sandy_prediction.get());
        }
    };

    public static final RegistryObject<Item> sandy_prediction = ITEMS.register("sandy_prediction", ItemRegistry::register);
    public static final RegistryObject<Item> twilight_prediction = ITEMS.register("twilight_prediction", ItemRegistry::register);
    public static final RegistryObject<Item> forgotten_prediction = ITEMS.register("forgotten_prediction", ItemRegistry::register);
    public static final RegistryObject<Item> ever_prediction = ITEMS.register("ever_prediction", ItemRegistry::register);

    public static Item register() {
        return new Item(new Item.Properties().tab(group));
    }
}
