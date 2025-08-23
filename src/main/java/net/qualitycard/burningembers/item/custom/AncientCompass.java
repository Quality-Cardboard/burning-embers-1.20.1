package net.qualitycard.burningembers.item.custom;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class AncientCompass extends Item {
    public AncientCompass(Settings settings) {
        super(settings);
    }

    public static int selected_x = 0;
    public static int selected_y = 0;
    public static int selected_z = 0;
    public static Identifier dimension = null;

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        user.sendMessage(Text.of("X: " + selected_x + " , Y: " + selected_y + " , Z: " + selected_z + ", Dimension: " + dimension), true);
        return super.use(world, user, hand);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        PlayerEntity player = context.getPlayer();
        assert player != null;
        if (player.isSneaking()) {
            BlockPos blockPos = context.getBlockPos();
            selected_x = blockPos.getX();
            selected_y = blockPos.getY();
            selected_z = blockPos.getZ();
            dimension = player.getEntityWorld().getDimensionKey().getValue();
            player.sendMessage(Text.of("X: " + selected_x + " , Y: " + selected_y + " , Z: " + selected_z + ", Dimension: " + dimension), true);
        }
        return super.useOnBlock(context);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.literal("X: " + selected_x + " , Y: " + selected_y + " , Z: " + selected_z + ", Dimension: " + dimension));
        super.appendTooltip(stack, world, tooltip, context);
    }
}
