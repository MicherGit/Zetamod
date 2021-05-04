package mixins.zeta.gui.screen;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.SharedConstants;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.RotatingCubeMapRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.widget.AbstractButtonWidget;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3f;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import zeta.zetamod.mod.ZetaMod;
import zeta.zetamod.mod.Concern;
import zeta.zetamod.mod.managers.ConfigManager;

import java.util.Iterator;
import java.util.function.BiConsumer;

@Mixin(TitleScreen.class)
public class CONCERNINCARNATE extends Screen{

    @Shadow
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String field_32272 = "Demo_World";
    private static final Identifier PANORAMA_OVERLAY = new Identifier("textures/gui/title/background/panorama_overlay.png");
    private static final Identifier ACCESSIBILITY_ICON_TEXTURE = new Identifier("textures/gui/accessibility.png");
    private final boolean isMinceraft;
    @Nullable
    private String splashText;
    private ButtonWidget buttonResetDemo;
    private static final Identifier MINECRAFT_TITLE_TEXTURE = new Identifier("textures/gui/title/minecraft.png");
    private static final Identifier EDITION_TITLE_TEXTURE = new Identifier("textures/gui/title/edition.png");
    private boolean realmsNotificationsInitialized;
    private Screen realmsNotificationGui;
    private int copyrightTextWidth;
    private int copyrightTextX;
    private final RotatingCubeMapRenderer backgroundRenderer;
    private final boolean doBackgroundFade;
    private long backgroundFadeStart;

    public CONCERNINCARNATE(boolean isMinceraft, @Nullable String splashText, ButtonWidget buttonResetDemo, boolean realmsNotificationsInitialized, Screen realmsNotificationGui, int copyrightTextWidth, int copyrightTextX, RotatingCubeMapRenderer backgroundRenderer, boolean doBackgroundFade) {
        super(new TranslatableText("narrator.screen.title"));
        this.isMinceraft = isMinceraft;
        this.splashText = splashText;
        this.buttonResetDemo = buttonResetDemo;
        this.realmsNotificationsInitialized = realmsNotificationsInitialized;
        this.realmsNotificationGui = realmsNotificationGui;
        this.copyrightTextWidth = copyrightTextWidth;
        this.copyrightTextX = copyrightTextX;
        this.backgroundRenderer = backgroundRenderer;
        this.doBackgroundFade = doBackgroundFade;
    }

    /**
     * @author
     */
    //TODO: Update this
    @Shadow
    private boolean areRealmsNotificationsEnabled() {
        return this.client.options.realmsNotifications && this.realmsNotificationGui != null;
    }
    @Mixin(DrawableHelper.class)
    static
    class DrawableHelperMixin {
        @Shadow
        public void method_29343(int i, int j, BiConsumer<Integer, Integer> biConsumer) {
            RenderSystem.blendFuncSeparate(GlStateManager.SrcFactor.ZERO, GlStateManager.DstFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SrcFactor.SRC_ALPHA, GlStateManager.DstFactor.ONE_MINUS_SRC_ALPHA);
            biConsumer.accept(i + 1, j);
            biConsumer.accept(i - 1, j);
            biConsumer.accept(i, j + 1);
            biConsumer.accept(i, j - 1);
            RenderSystem.blendFunc(GlStateManager.SrcFactor.SRC_ALPHA, GlStateManager.DstFactor.ONE_MINUS_SRC_ALPHA);
            biConsumer.accept(i, j);
        }
    }
}
