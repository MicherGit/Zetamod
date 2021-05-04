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
    @Overwrite
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        if (this.backgroundFadeStart == 0L && this.doBackgroundFade) {
            this.backgroundFadeStart = Util.getMeasuringTimeMs();
        }

        float f = this.doBackgroundFade ? (float)(Util.getMeasuringTimeMs() - this.backgroundFadeStart) / 1000.0F : 1.0F;
        this.backgroundRenderer.render(delta, MathHelper.clamp(f, 0.0F, 1.0F));
        boolean i = true;
        int j = this.width / 2 - 137;
        boolean k = true;
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, PANORAMA_OVERLAY);
        RenderSystem.enableBlend();
        RenderSystem.blendFunc(GlStateManager.SrcFactor.SRC_ALPHA, GlStateManager.DstFactor.ONE_MINUS_SRC_ALPHA);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, this.doBackgroundFade ? (float)MathHelper.ceil(MathHelper.clamp(f, 0.0F, 1.0F)) : 1.0F);
        Screen.drawTexture(matrices, 0, 0, this.width, this.height, 0.0F, 0.0F, 16, 128, 16, 128);
        float g = this.doBackgroundFade ? MathHelper.clamp(f - 1.0F, 0.0F, 1.0F) : 1.0F;
        int l = MathHelper.ceil(g * 255.0F) << 24;
        if ((l & -67108864) != 0) {
            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            RenderSystem.setShaderTexture(0, MINECRAFT_TITLE_TEXTURE);
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, g);
            if (this.isMinceraft) {
                this.method_29343(j, 30, (integer, integer2) -> {
                    this.drawTexture(matrices, integer + 0, integer2, 0, 0, 99, 44);
                    this.drawTexture(matrices, integer + 99, integer2, 129, 0, 27, 44);
                    this.drawTexture(matrices, integer + 99 + 26, integer2, 126, 0, 3, 44);
                    this.drawTexture(matrices, integer + 99 + 26 + 3, integer2, 99, 0, 26, 44);
                    this.drawTexture(matrices, integer + 155, integer2, 0, 45, 155, 44);
                });
            } else {
                this.method_29343(j, 30, (integer, integer2) -> {
                    this.drawTexture(matrices, integer + 0, integer2, 0, 0, 155, 44);
                    this.drawTexture(matrices, integer + 155, integer2, 0, 45, 155, 44);
                });
            }

            RenderSystem.setShaderTexture(0, EDITION_TITLE_TEXTURE);
            drawTexture(matrices, j + 88, 67, 0.0F, 0.0F, 98, 14, 128, 16);
            if (this.splashText != null) {
                matrices.push();
                matrices.translate((double)(this.width / 2 + 90), 70.0D, 0.0D);
                matrices.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion(-20.0F));
                float h = 1.8F - MathHelper.abs(MathHelper.sin((float)(Util.getMeasuringTimeMs() % 1000L) / 1000.0F * 6.2831855F) * 0.1F);
                h = h * 100.0F / (float)(this.textRenderer.getWidth(this.splashText) + 32);
                matrices.scale(h, h, h);
                drawCenteredString(matrices, this.textRenderer, this.splashText, 0, -8, 16776960 | l);
                matrices.pop();
            }
            // THIS!!!! AAAAAAAAAA
            String string;
            if(!ConfigManager.getConfig().enableOneSeventeenBoolean.getValue()) {
                string = "Minecraft " + SharedConstants.getGameVersion().getName()
                        + " + "+ ZetaMod.MOD_NAME+" " + Concern.concern;
            } else {
                string = "Minecraft 1.17";
            }
            if (!Concern.stageOne && !Concern.calledTwice) {
                System.out.println("Injecting shit part 2");
                Concern.calledTwice = !Concern.calledTwice;
            } else {
                System.out.println("Injecting shit part 1"); Concern.stageOne = !Concern.stageOne;
            }
            if (this.client.isDemo()) {
                string = string + " Demo";
            } else {
                string = string + ("release".equalsIgnoreCase(this.client.getVersionType()) ? "" : "/" + this.client.getVersionType());
            }

            if (this.client.isModded()) {
                string = string + I18n.translate("menu.modded", new Object[0]);
            }

            drawStringWithShadow(matrices, this.textRenderer, string, 2, this.height - 10, 16777215 | l);
            drawStringWithShadow(matrices, this.textRenderer, "Copyright Mojang AB. Do not distribute!", this.copyrightTextX, this.height - 10, 16777215 | l);
            if (mouseX > this.copyrightTextX && mouseX < this.copyrightTextX + this.copyrightTextWidth && mouseY > this.height - 10 && mouseY < this.height) {
                fill(matrices, this.copyrightTextX, this.height - 1, this.copyrightTextX + this.copyrightTextWidth, this.height, 16777215 | l);
            }

            Iterator var12 = this.buttons.iterator();

            while(var12.hasNext()) {
                AbstractButtonWidget abstractButtonWidget = (AbstractButtonWidget)var12.next();
                abstractButtonWidget.setAlpha(g);
            }

            super.render(matrices, mouseX, mouseY, delta);
            if (this.areRealmsNotificationsEnabled() && g >= 1.0F) {
                this.realmsNotificationGui.render(matrices, mouseX, mouseY, delta);
            }

        }
    }
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
