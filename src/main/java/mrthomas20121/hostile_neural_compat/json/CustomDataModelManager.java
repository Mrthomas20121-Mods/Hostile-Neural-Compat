package mrthomas20121.hostile_neural_compat.json;

import com.google.gson.*;
import mrthomas20121.hostile_neural_compat.HostileNeuralCompat;
import net.minecraft.client.resources.JsonReloadListener;
import net.minecraft.item.ItemStack;
import net.minecraft.profiler.IProfiler;
import net.minecraft.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.ModList;
import shadows.hostilenetworks.data.DataModel;
import shadows.hostilenetworks.data.DataModelManager;
import shadows.placebo.util.json.ItemAdapter;

import javax.annotation.Nonnull;
import java.util.Map;

/**
 * I made this class to allow me to load the data model only if the mod is loaded
 */
public class CustomDataModelManager extends JsonReloadListener {

    public static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(ItemStack.class, ItemAdapter.INSTANCE)
            .registerTypeAdapter(DataModel.class, DataModel.Adapter.INSTANCE).create();

    public static CustomDataModelManager INSTANCE = new CustomDataModelManager();

    public CustomDataModelManager() {
        super(GSON, "custom_data_models");
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> pObject, @Nonnull IResourceManager pResourceManager, @Nonnull IProfiler pProfiler) {
        for(Map.Entry<ResourceLocation, JsonElement> entry: pObject.entrySet()) {
            ResourceLocation loc = entry.getKey();
            JsonElement ele = entry.getValue();
            try {
                JsonObject object = ele.getAsJsonObject();
                if (object.entrySet().isEmpty() && !object.has("mod_id")) continue; //Ignore empty files so people can delete models.
                String mod_id = object.get("mod_id").getAsString();
                if(!ModList.get().isLoaded(mod_id)) {
                    HostileNeuralCompat.LOGGER.warn("Skipping data model {}, {} is not loaded.", loc, mod_id);
                    continue;
                }
                DataModel model = GSON.fromJson(object.get("data_model"), DataModel.class);
                model.setId(loc);
                DataModelManager.INSTANCE.register(model);
                HostileNeuralCompat.LOGGER.info("Loading model {}", model.getId().toString());

            } catch (JsonParseException ex) {
                HostileNeuralCompat.LOGGER.error("Failed to load data model {}.", loc);
                ex.printStackTrace();
            }
        }
    }
}
