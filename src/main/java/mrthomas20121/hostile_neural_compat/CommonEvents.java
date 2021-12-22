package mrthomas20121.hostile_neural_compat;

import mrthomas20121.hostile_neural_compat.json.CustomDataModelManager;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HostileNeuralCompat.MOD_ID)
public class CommonEvents {

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void reloads(AddReloadListenerEvent e) {
        e.addListener(CustomDataModelManager.INSTANCE);
    }
}
