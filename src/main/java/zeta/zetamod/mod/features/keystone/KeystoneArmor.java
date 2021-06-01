package zeta.zetamod.mod.features.keystone;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import zeta.zetamod.mod.ZetaMod;
import zeta.zetamod.mod.features.keystone.mats.KeystoneArmorMaterial;

import static net.minecraft.item.ItemGroup.COMBAT;

public class KeystoneArmor {
    public static final ArmorMaterial CUSTOM_ARMOR_MATERIAL = new KeystoneArmorMaterial();
    public static final Item KEYSTONE_HELMET = new ArmorItem(CUSTOM_ARMOR_MATERIAL, EquipmentSlot.HEAD, new Item.Settings().group(COMBAT));
    public static final Item KEYSTONE_CHESTPLATE = new ArmorItem(CUSTOM_ARMOR_MATERIAL, EquipmentSlot.CHEST, new Item.Settings().group(COMBAT));
    public static final Item KEYSTONE_LEGGINGS = new ArmorItem(CUSTOM_ARMOR_MATERIAL, EquipmentSlot.LEGS, new Item.Settings().group(COMBAT));
    public static final Item KEYSTONE_BOOTS = new ArmorItem(CUSTOM_ARMOR_MATERIAL, EquipmentSlot.FEET, new Item.Settings().group(COMBAT));

    public static void register() {
        //Registry.register(Registry.ITEM, new Identifier(ZetaMod.MOD_ID, KEYSTONE"), KEYSTONE);
        Registry.register(Registry.ITEM, new Identifier(ZetaMod.MOD_ID, new KeystoneArmorMaterial().getName()+"_helmet"), KEYSTONE_HELMET);
        Registry.register(Registry.ITEM, new Identifier(ZetaMod.MOD_ID, new KeystoneArmorMaterial().getName()+"_chestplate"), KEYSTONE_CHESTPLATE);
        Registry.register(Registry.ITEM, new Identifier(ZetaMod.MOD_ID, new KeystoneArmorMaterial().getName()+"_leggings"), KEYSTONE_LEGGINGS);
        Registry.register(Registry.ITEM, new Identifier(ZetaMod.MOD_ID, new KeystoneArmorMaterial().getName()+"_boots"), KEYSTONE_BOOTS);
    }
}

