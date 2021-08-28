package com.veinminer.css.listeners;

import com.veinminer.css.config.Configuration;
import com.veinminer.css.config.VeinMinerConfiguration;
import com.veinminer.css.models.Vector3D;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class BlockBreakListener implements Listener {

    // im not gonna make an algorithm for this
    private final List<Vector3D> adjacent = new ArrayList<>(
        Arrays.asList(
            new Vector3D(-1, -1, -1),
            new Vector3D(-1, -1, 0),
            new Vector3D(-1, -1, 1),
            new Vector3D(0, -1, -1),
            new Vector3D(0, -1, 0),
            new Vector3D(0, -1, 1),
            new Vector3D(1, -1, -1),
            new Vector3D(1, -1, 0),
            new Vector3D(1, -1, 1),
            new Vector3D(-1, 0, -1),
            new Vector3D(-1, 0, 0),
            new Vector3D(-1, 0, 1),
            new Vector3D(0, 0, -1),
//            new Vector3D(0, 0, 0),
            new Vector3D(0, 0, 1),
            new Vector3D(1, 0, -1),
            new Vector3D(1, 0, 0),
            new Vector3D(1, 0, 1),
            new Vector3D(-1, 1, -1),
            new Vector3D(-1, 1, 0),
            new Vector3D(-1, 1, 1),
            new Vector3D(0, 1, -1),
            new Vector3D(0, 1, 0),
            new Vector3D(0, 1, 1),
            new Vector3D(1, 1, -1),
            new Vector3D(1, 1, 0),
            new Vector3D(1, 1, 1)
        )
    );

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (!player.isSneaking()) return;

        Block block = event.getBlock();
        if (!isBlockWhitelisted(block)) return;
        if (!isPlayerUsingPreferredTool(block, player)) return;

        Set<Block> vein = new HashSet<>();
        findVein(vein, block);
        for (Block veinMember : vein) {
            veinMember.breakNaturally(player.getInventory().getItemInMainHand());
        }
    }

    private boolean isBlockWhitelisted(Block block) {
        VeinMinerConfiguration vmConfig = Configuration.getInstance().getVeinMinerConfiguration();
        return vmConfig.getVeinMinerWhitelist().contains(block.getType());
    }

    private boolean isPlayerUsingPreferredTool(Block block, Player player) {
        ItemStack itemInMainHand = player.getInventory().getItemInMainHand();
        return itemInMainHand.getType() != Material.AIR && block.isPreferredTool(itemInMainHand);
    }

    private void findVein(Set<Block> vein, Block currentBlock) {
        VeinMinerConfiguration vmConfig = Configuration.getInstance().getVeinMinerConfiguration();
        if (vein.size() >= vmConfig.getVeinMineLimit()) return;
        vein.add(currentBlock);
        Material veinType = currentBlock.getType();
        for (Vector3D v : adjacent) {
            Block newBlock = currentBlock.getLocation().add(
                    v.getX(),
                    v.getY(),
                    v.getZ()
            ).getBlock();
            if (!vein.contains(newBlock) && newBlock.getType() == veinType) {
                findVein(vein, newBlock);
            }
        }
    }
}
