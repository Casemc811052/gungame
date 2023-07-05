package net.besence.GunItem;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.util.Vector;

public class GunItem extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // プラグインが有効になったときの処理
        getServer().getPluginManager().registerEvents(this, this);
        system.out.println("plugins is enable")
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();

        // 馬鎧が右クリックされた場合の処理
        if (item != null && item.getType() == Material.DIAMOND_HORSE_ARMOR && item.getItemMeta().getDisplayName().equals(ChatColor.GREEN + "銃")
                && event.getAction().toString().contains("RIGHT")) {
            event.setCancelled(true); // 馬鎧を装備する動作をキャンセルする

            // 雪玉を発射する処理
            Vector direction = player.getLocation().getDirection();
            ProjectileSource source = player;
            Snowball snowball = player.launchProjectile(Snowball.class, direction);
            snowball.setVelocity(direction.multiply(2)); // 速度を調整する
            snowball.setShooter(source);
            snowball.setDamage(0.5); // 雪玉のダメージを設定する
        }
    }

    private ItemStack createGunItem() {
        ItemStack gun = new ItemStack(Material.DIAMOND_HORSE_ARMOR);
        ItemMeta meta = gun.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "銃");
        // その他の銃の特性を設定する

        gun.setItemMeta(meta);
        return gun;
    }
}
