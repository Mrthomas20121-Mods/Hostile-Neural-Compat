package mrthomas20121.hostile_neural_compat;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(HostileNeuralCompat.MOD_ID)
public class HostileNeuralCompat {

	public static final String MOD_ID = "hostile_neural_compat";
	public static final Logger LOGGER = LogManager.getLogger();

	public HostileNeuralCompat() {
		ItemRegistry.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
	}
}
