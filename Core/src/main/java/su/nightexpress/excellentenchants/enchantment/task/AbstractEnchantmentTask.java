package su.nightexpress.excellentenchants.enchantment.task;

import org.bukkit.entity.LivingEntity;
import su.nightexpress.excellentenchants.enchantment.task.NonNull;
import su.nexmedia.engine.api.server.AbstractTask;
import su.nightexpress.excellentenchants.ExcellentEnchants;
import su.nightexpress.excellentenchants.config.Config;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public abstract class AbstractEnchantmentTask extends AbstractTask<ExcellentEnchants> {

    public AbstractEnchantmentTask(@NonNull ExcellentEnchants plugin, long interval, boolean async) {
        super(plugin, interval, async);
    }

    @NonNull
    protected Collection<@NonNull ? extends LivingEntity> getEntities() {
        Set<LivingEntity> list = new HashSet<>(plugin.getServer().getOnlinePlayers());

        if (Config.ENCHANTMENTS_ENTITY_PASSIVE_FOR_MOBS.get()) {
            plugin.getServer().getWorlds().stream().filter(world -> !world.getPlayers().isEmpty()).forEach(world -> {
                list.addAll(world.getEntitiesByClass(LivingEntity.class));
            });
        }
        list.removeIf(e -> e.isDead() || !e.isValid());
        return list;
    }
}
